package br.com.ufg.joaoguedes.torneios_api.models;

import br.com.ufg.joaoguedes.torneios_api.enums.StatusTorneio;
import br.com.ufg.joaoguedes.torneios_api.enums.TipoTorneio;
import br.com.ufg.joaoguedes.torneios_api.repositories.EquipeRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.GrupoRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.PartidaRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "torneio_base")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
@Getter
@Setter
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

    @OneToMany(mappedBy = "torneioBase", cascade = CascadeType.ALL)
    private List<Equipe> equipes = new ArrayList<>();

    @OneToMany(mappedBy = "torneioBase", cascade = CascadeType.ALL)
    private List<Partida> partidas = new ArrayList<>();

    public abstract TipoTorneio getTipo();

    public abstract void validarRegras();

    public abstract void gerarChave(List<Equipe> equipes, PartidaRepository partidaRepository, EquipeRepository equipeRepository,
                                    GrupoRepository grupoRepository);
}