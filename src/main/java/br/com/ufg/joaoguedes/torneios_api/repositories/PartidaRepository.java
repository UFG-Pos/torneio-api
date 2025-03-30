package br.com.ufg.joaoguedes.torneios_api.repositories;

import br.com.ufg.joaoguedes.torneios_api.enums.FaseTorneio;
import br.com.ufg.joaoguedes.torneios_api.models.Partida;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByTorneioBaseId(Long torneioId);

    List<Partida> findByTorneioBaseAndFase(TorneioBase torneio, FaseTorneio faseAtual);
}