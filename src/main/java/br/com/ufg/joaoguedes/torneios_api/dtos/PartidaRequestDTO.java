package br.com.ufg.joaoguedes.torneios_api.dtos;

import br.com.ufg.joaoguedes.torneios_api.enums.FaseTorneio;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PartidaRequestDTO {

    @NotNull
    private LocalDateTime horario;

    @NotNull
    private Long torneioId;

    @NotNull
    private Long equipeAId;

    @NotNull
    private Long equipeBId;

    @NotNull
    private FaseTorneio fase;
}