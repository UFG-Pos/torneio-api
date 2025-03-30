package br.com.ufg.joaoguedes.torneios_api.models;

import br.com.ufg.joaoguedes.torneios_api.enums.FaseTorneio;
import br.com.ufg.joaoguedes.torneios_api.enums.StatusPartida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "partidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime horario;

    @ManyToOne
    @JoinColumn(name = "torneio_id")
    private TorneioBase torneioBase;

    @ManyToOne
    @JoinColumn(name = "equipe_a_id")
    private Equipe equipeA;

    @ManyToOne
    @JoinColumn(name = "equipe_b_id")
    private Equipe equipeB;

    @Column(name = "placar_equipe_a")
    private Integer placarEquipeA;

    @Column(name = "placar_equipe_b")
    private Integer placarEquipeB;

    @Enumerated(EnumType.STRING)
    private StatusPartida status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FaseTorneio fase;
    
}