package br.com.ufg.joaoguedes.torneios_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "torneio_id", nullable = false)
    private TorneioBase torneioBase;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Jogador> jogadores = new ArrayList<>();
}