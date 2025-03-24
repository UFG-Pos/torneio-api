package br.com.ufg.joaoguedes.torneios_api.repositories;

import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneioBaseRepository extends JpaRepository<TorneioBase, Long> {
}