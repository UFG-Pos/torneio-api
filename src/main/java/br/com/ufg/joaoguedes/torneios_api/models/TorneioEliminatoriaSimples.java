package br.com.ufg.joaoguedes.torneios_api.models;

import br.com.ufg.joaoguedes.torneios_api.enums.FaseTorneio;
import br.com.ufg.joaoguedes.torneios_api.enums.StatusPartida;
import br.com.ufg.joaoguedes.torneios_api.enums.TipoTorneio;
import br.com.ufg.joaoguedes.torneios_api.repositories.EquipeRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.GrupoRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.PartidaRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "torneios_eliminatoria_simples")
@Getter
@Setter
@NoArgsConstructor
public class TorneioEliminatoriaSimples extends TorneioBase {

    @Override
    public TipoTorneio getTipo() {
        return TipoTorneio.ELIMINATORIA_SIMPLES;
    }

    @Override
    public void validarRegras() {
        if (maxParticipantes % 2 != 0) {
            throw new IllegalArgumentException("Número de participantes deve ser par em uma eliminatória simples.");
        }
    }

    @Override
    public void gerarChave(List<Equipe> equipes, PartidaRepository partidaRepository, EquipeRepository equipeRepository, GrupoRepository grupoRepository) {
        Collections.shuffle(equipes);
        for (int i = 0; i < equipes.size(); i += 2) {
            Equipe equipeA = equipes.get(i);
            Equipe equipeB = equipes.get(i + 1);

            Partida partida = new Partida();
            partida.setEquipeA(equipeA);
            partida.setEquipeB(equipeB);
            partida.setHorario(LocalDateTime.now().plusDays(1));
            partida.setStatus(StatusPartida.AGENDADA);
            partida.setFase(FaseTorneio.QUARTAS_DE_FINAL);
            partida.setTorneioBase(this);
            partidaRepository.save(partida);
        }
    }
}