package br.com.ufg.joaoguedes.torneios_api.dtos;

import br.com.ufg.joaoguedes.torneios_api.enums.StatusPartida;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AtualizarPartidaDTO {

    @NotNull
    private Integer placarEquipeA;

    @NotNull
    private Integer placarEquipeB;

    @NotNull
    private StatusPartida status;

}