package br.com.ufg.joaoguedes.torneios_api.repositories;

import br.com.ufg.joaoguedes.torneios_api.models.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
}