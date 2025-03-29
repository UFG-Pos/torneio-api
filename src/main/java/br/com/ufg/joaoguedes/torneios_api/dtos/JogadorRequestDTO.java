package br.com.ufg.joaoguedes.torneios_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JogadorRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Long equipeId;
}