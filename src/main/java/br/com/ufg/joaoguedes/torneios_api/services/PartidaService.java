package br.com.ufg.joaoguedes.torneios_api.services;

import br.com.ufg.joaoguedes.torneios_api.dtos.AtualizarPartidaDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.PartidaRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.PartidaResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.enums.FaseTorneio;
import br.com.ufg.joaoguedes.torneios_api.enums.StatusPartida;
import br.com.ufg.joaoguedes.torneios_api.mappers.PartidaMapper;
import br.com.ufg.joaoguedes.torneios_api.models.Equipe;
import br.com.ufg.joaoguedes.torneios_api.models.Partida;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import br.com.ufg.joaoguedes.torneios_api.repositories.EquipeRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.GrupoRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.PartidaRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.TorneioBaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final EquipeRepository equipeRepository;
    private final TorneioBaseRepository torneioBaseRepository;
    private final GrupoRepository grupoRepository;
    private final PartidaMapper partidaMapper;


    public PartidaResponseDTO criarPartida(PartidaRequestDTO dto) {
        TorneioBase torneio = torneioBaseRepository.findById(dto.getTorneioId())
                .orElseThrow(() -> new IllegalArgumentException("Torneio não encontrado"));

        Equipe equipeA = equipeRepository.findById(dto.getEquipeAId())
                .orElseThrow(() -> new IllegalArgumentException("Equipe A não encontrada"));

        Equipe equipeB = equipeRepository.findById(dto.getEquipeBId())
                .orElseThrow(() -> new IllegalArgumentException("Equipe B não encontrada"));

        if (!equipeA.getTorneioBase().getId().equals(torneio.getId()) ||
                !equipeB.getTorneioBase().getId().equals(torneio.getId())) {
            throw new IllegalArgumentException("As equipes devem pertencer ao mesmo torneio");
        }

        Partida partida = partidaMapper.toEntity(dto, equipeA, equipeB, torneio);
        return partidaMapper.toDTO(partidaRepository.save(partida));
    }

    public List<PartidaResponseDTO> listarPorTorneio(Long torneioId) {
        return partidaRepository.findByTorneioBaseId(torneioId).stream()
                .map(partidaMapper::toDTO)
                .toList();
    }

    @Transactional
    public PartidaResponseDTO atualizarPartida(Long id, AtualizarPartidaDTO dto) {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Partida não encontrada"));

        partida.setPlacarEquipeA(dto.getPlacarEquipeA());
        partida.setPlacarEquipeB(dto.getPlacarEquipeB());
        partida.setStatus(StatusPartida.CONCLUIDA);

        partidaRepository.save(partida);

        verificarEAvancarFase(partida.getTorneioBase(), partida.getFase());

        return partidaMapper.toDTO(partidaRepository.save(partida));
    }

    private void verificarEAvancarFase(TorneioBase torneio, FaseTorneio faseAtual) {
        List<Partida> partidasDaFase = partidaRepository.findByTorneioBaseAndFase(torneio, faseAtual);

        boolean todasConcluidas = partidasDaFase.stream()
                .allMatch(p -> p.getStatus() == StatusPartida.CONCLUIDA);

        if (!todasConcluidas) return;

        List<Equipe> vencedores = partidasDaFase.stream()
                .map(this::obterVencedor)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (vencedores.size() < 2) return;

        FaseTorneio proximaFase = obterProximaFase(faseAtual);

        List<Partida> novasPartidas = new ArrayList<>();

        for (int i = 0; i < vencedores.size(); i += 2) {
            if (i + 1 < vencedores.size()) {
                Partida novaPartida = new Partida();
                novaPartida.setEquipeA(vencedores.get(i));
                novaPartida.setEquipeB(vencedores.get(i + 1));
                novaPartida.setStatus(StatusPartida.AGENDADA);
                novaPartida.setFase(proximaFase);
                novaPartida.setTorneioBase(torneio);
                novaPartida.setHorario(LocalDateTime.now().plusDays(1)); // horário padrão

                novasPartidas.add(novaPartida);
            }
        }

        partidaRepository.saveAll(novasPartidas);
    }

    private Equipe obterVencedor(Partida p) {
        if (p.getPlacarEquipeA() == null || p.getPlacarEquipeB() == null) return null;
        if (p.getPlacarEquipeA() > p.getPlacarEquipeB()) return p.getEquipeA();
        if (p.getPlacarEquipeB() > p.getPlacarEquipeA()) return p.getEquipeB();
        return null;
    }

    private FaseTorneio obterProximaFase(FaseTorneio faseAtual) {
        return switch (faseAtual) {
            case QUARTAS_DE_FINAL -> FaseTorneio.SEMIFINAL;
            case SEMIFINAL -> FaseTorneio.FINAL;
            default -> null;
        };
    }

    @Transactional
    public void gerarPartidasParaTorneio(Long torneioId) {
        TorneioBase torneio = torneioBaseRepository.findById(torneioId)
                .orElseThrow(() -> new RuntimeException("Torneio não encontrado"));

        List<Equipe> equipes = equipeRepository.findByTorneioBaseId(torneioId);

        torneio.gerarChave(equipes, partidaRepository, equipeRepository, grupoRepository);
    }
}