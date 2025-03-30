package br.com.ufg.joaoguedes.torneios_api.repositories;

import br.com.ufg.joaoguedes.torneios_api.models.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}