package br.com.ufg.joaoguedes.torneios_api.services;

import br.com.ufg.joaoguedes.torneios_api.dtos.EquipeRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.EquipeResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.mappers.EquipeMapper;
import br.com.ufg.joaoguedes.torneios_api.models.Equipe;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import br.com.ufg.joaoguedes.torneios_api.repositories.EquipeRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.TorneioBaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final TorneioBaseRepository torneioBaseRepository;
    private final EquipeMapper equipeMapper;

    public EquipeService(EquipeRepository equipeRepository,
                         TorneioBaseRepository torneioBaseRepository,
                         EquipeMapper equipeMapper) {
        this.equipeRepository = equipeRepository;
        this.torneioBaseRepository = torneioBaseRepository;
        this.equipeMapper = equipeMapper;
    }

    public EquipeResponseDTO criarEquipe(EquipeRequestDTO dto) {
        TorneioBase torneio = torneioBaseRepository.findById(dto.getTorneioId())
                .orElseThrow(() -> new IllegalArgumentException("Torneio não encontrado"));

        Equipe equipe = equipeMapper.toEntity(dto, torneio);
        return equipeMapper.toDTO(equipeRepository.save(equipe));
    }

    public List<EquipeResponseDTO> listarPorTorneio(Long torneioId) {
        return equipeRepository.findByTorneioBaseId(torneioId).stream()
                .map(equipeMapper::toDTO)
                .toList();
    }
}