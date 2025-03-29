package br.com.ufg.joaoguedes.torneios_api.repositories;

import br.com.ufg.joaoguedes.torneios_api.models.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    List<Jogador> findByEquipeId(Long equipeId);
}