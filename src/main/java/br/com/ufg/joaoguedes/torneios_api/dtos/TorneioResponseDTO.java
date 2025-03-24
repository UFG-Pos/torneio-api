package br.com.ufg.joaoguedes.torneios_api.dtos;

import br.com.ufg.joaoguedes.torneios_api.enums.StatusTorneio;
import br.com.ufg.joaoguedes.torneios_api.enums.TipoTorneio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TorneioResponseDTO {

    private Long id;
    private String nome;
    private TipoTorneio tipo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer maxParticipantes;
    private StatusTorneio status;

    // usado apenas em torneios de fase de grupos
    private Integer numeroGrupos;
}