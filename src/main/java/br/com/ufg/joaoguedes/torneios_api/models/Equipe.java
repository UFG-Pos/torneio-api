package br.com.ufg.joaoguedes.torneios_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "equipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "torneio_id")
    private TorneioBase torneioBase;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Jogador> jogadores = new ArrayList<>();
}