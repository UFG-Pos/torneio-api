package br.com.ufg.joaoguedes.torneios_api.services;

import br.com.ufg.joaoguedes.torneios_api.dtos.JogadorRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.JogadorResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.mappers.JogadorMapper;
import br.com.ufg.joaoguedes.torneios_api.models.Equipe;
import br.com.ufg.joaoguedes.torneios_api.models.Jogador;
import br.com.ufg.joaoguedes.torneios_api.repositories.EquipeRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final EquipeRepository equipeRepository;
    private final JogadorMapper jogadorMapper;

    public JogadorService(JogadorRepository jogadorRepository,
                          EquipeRepository equipeRepository,
                          JogadorMapper jogadorMapper) {
        this.jogadorRepository = jogadorRepository;
        this.equipeRepository = equipeRepository;
        this.jogadorMapper = jogadorMapper;
    }

    public JogadorResponseDTO criarJogador(JogadorRequestDTO dto) {
        Equipe equipe = equipeRepository.findById(dto.getEquipeId())
                .orElseThrow(() -> new IllegalArgumentException("Equipe não encontrada"));

        Jogador jogador = jogadorMapper.toEntity(dto, equipe);
        return jogadorMapper.toDTO(jogadorRepository.save(jogador));
    }

    public List<JogadorResponseDTO> listarPorEquipe(Long equipeId) {
        List<Jogador> jogadores = jogadorRepository.findByEquipeId(equipeId);
        return jogadores.stream()
                .map(jogadorMapper::toDTO)
                .toList();
    }
}