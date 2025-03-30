package br.com.ufg.joaoguedes.torneios_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipeResponseDTO {
    private Long id;
    private String nome;
    private String grupo;
    private List<JogadorResponseDTO> jogadores;
}