package br.com.ufg.joaoguedes.torneios_api.dtos;

import br.com.ufg.joaoguedes.torneios_api.enums.FaseTorneio;
import br.com.ufg.joaoguedes.torneios_api.enums.StatusPartida;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PartidaResponseDTO {

    private Long id;
    private LocalDateTime horario;
    private EquipeResponseDTO equipeA;
    private EquipeResponseDTO equipeB;
    private Integer placarEquipeA;
    private Integer placarEquipeB;
    private StatusPartida status;
    private FaseTorneio fase;
}