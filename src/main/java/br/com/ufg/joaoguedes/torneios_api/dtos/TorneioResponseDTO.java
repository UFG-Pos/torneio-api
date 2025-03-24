package br.com.ufg.joaoguedes.torneios_api.dtos;

import br.com.ufg.joaoguedes.torneios_api.enums.StatusTorneio;
import br.com.ufg.joaoguedes.torneios_api.enums.TipoTorneio;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TorneioResponseDTO {
    private Long id;
    private String nome;
    private TipoTorneio tipo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer maxParticipantes;
    private StatusTorneio status;
    private Integer numeroGrupos;
}