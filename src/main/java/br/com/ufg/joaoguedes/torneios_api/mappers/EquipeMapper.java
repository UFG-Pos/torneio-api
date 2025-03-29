package br.com.ufg.joaoguedes.torneios_api.mappers;

import br.com.ufg.joaoguedes.torneios_api.dtos.EquipeRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.EquipeResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.models.Equipe;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import org.springframework.stereotype.Component;

@Component
public class EquipeMapper {

    public Equipe toEntity(EquipeRequestDTO dto, TorneioBase torneio) {
        Equipe equipe = new Equipe();
        equipe.setNome(dto.getNome());
        equipe.setTorneioBase(torneio);
        return equipe;
    }

    public EquipeResponseDTO toDTO(Equipe equipe) {
        EquipeResponseDTO dto = new EquipeResponseDTO();
        dto.setId(equipe.getId());
        dto.setNome(equipe.getNome());
        return dto;
    }
}