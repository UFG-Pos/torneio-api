package br.com.ufg.joaoguedes.torneios_api.models;

import br.com.ufg.joaoguedes.torneios_api.enums.StatusTorneio;
import br.com.ufg.joaoguedes.torneios_api.enums.TipoTorneio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "torneio_base")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public abstract class TorneioBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String nome;

    @Column(name = "data_inicio", nullable = false)
    protected LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    protected LocalDate dataFim;

    @Column(name = "max_participantes", nullable = false)
    protected Integer maxParticipantes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected StatusTorneio status;

    public abstract TipoTorneio getTipo();

    public abstract void validarRegras();
}