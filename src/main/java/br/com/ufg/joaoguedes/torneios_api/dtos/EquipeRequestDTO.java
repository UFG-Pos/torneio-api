package br.com.ufg.joaoguedes.torneios_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EquipeRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Long torneioId;
}