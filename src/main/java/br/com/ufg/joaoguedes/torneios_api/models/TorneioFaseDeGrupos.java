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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "torneios_fase_grupos")
@Getter
@Setter
@NoArgsConstructor
public class TorneioFaseDeGrupos extends TorneioBase {

    private Integer numeroGrupos;

    @Override
    public TipoTorneio getTipo() {
        return TipoTorneio.FASE_DE_GRUPOS;
    }

    @Override
    public void validarRegras() {
        if (numeroGrupos == null || numeroGrupos < 2) {
            throw new IllegalArgumentException("É necessário ao menos 2 grupos.");
        }
    }

    public void gerarChave(
            List<Equipe> equipes,
            PartidaRepository partidaRepository,
            EquipeRepository equipeRepository,
            GrupoRepository grupoRepository
    ) {
        validarRegras();

        Collections.shuffle(equipes);

        List<Grupo> grupos = new ArrayList<>();
        for (int i = 0; i < numeroGrupos; i++) {
            Grupo grupo = new Grupo();
            grupo.setNome("Grupo " + (char) ('A' + i));
            grupo.setTorneio(this);
            grupos.add(grupo);
        }

        grupos = grupoRepository.saveAll(grupos);

        for (int i = 0; i < equipes.size(); i++) {
            Equipe equipe = equipes.get(i);
            Grupo grupo = grupos.get(i % numeroGrupos);
            equipe.setGrupo(grupo);
            grupo.getEquipes().add(equipe);
        }

        equipeRepository.saveAll(equipes);

        for (Grupo grupo : grupos) {
            List<Equipe> equipesDoGrupo = grupo.getEquipes();
            for (int i = 0; i < equipesDoGrupo.size(); i++) {
                for (int j = i + 1; j < equipesDoGrupo.size(); j++) {
                    Partida partida = new Partida();
                    partida.setEquipeA(equipesDoGrupo.get(i));
                    partida.setEquipeB(equipesDoGrupo.get(j));
                    partida.setFase(FaseTorneio.GRUPOS);
                    partida.setHorario(LocalDateTime.now().plusDays(1)); // data genérica
                    partida.setStatus(StatusPartida.AGENDADA);
                    partida.setTorneioBase(this);
                    partidaRepository.save(partida);
                }
            }
        }
    }
}