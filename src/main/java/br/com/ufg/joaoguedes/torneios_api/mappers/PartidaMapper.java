package br.com.ufg.joaoguedes.torneios_api.mappers;

import br.com.ufg.joaoguedes.torneios_api.dtos.EquipeResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.PartidaRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.PartidaResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.enums.StatusPartida;
import br.com.ufg.joaoguedes.torneios_api.models.Equipe;
import br.com.ufg.joaoguedes.torneios_api.models.Partida;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import org.springframework.stereotype.Component;

@Component
public class PartidaMapper {

    public Partida toEntity(PartidaRequestDTO dto, Equipe equipeA, Equipe equipeB, TorneioBase torneio) {
        Partida partida = new Partida();
        partida.setHorario(dto.getHorario());
        partida.setEquipeA(equipeA);
        partida.setEquipeB(equipeB);
        partida.setTorneioBase(torneio);
        partida.setFase(dto.getFase());
        partida.setStatus(StatusPartida.AGENDADA);
        return partida;
    }

    public PartidaResponseDTO toDTO(Partida partida) {
        return PartidaResponseDTO.builder()
                .id(partida.getId())
                .horario(partida.getHorario())
                .equipeA(EquipeResponseDTO.builder()
                        .nome(partida.getEquipeA().getNome())
                        .id(partida.getEquipeA().getId())
                        .build())
                .equipeB(EquipeResponseDTO.builder()
                        .nome(partida.getEquipeB().getNome())
                        .id(partida.getEquipeB().getId())
                        .build())
                .placarEquipeA(partida.getPlacarEquipeA())
                .placarEquipeB(partida.getPlacarEquipeB())
                .status(partida.getStatus())
                .fase(partida.getFase())
                .build();
    }
}