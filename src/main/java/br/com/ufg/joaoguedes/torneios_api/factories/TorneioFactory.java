package br.com.ufg.joaoguedes.torneios_api.factories;

import br.com.ufg.joaoguedes.torneios_api.dtos.TorneioRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioEliminatoriaSimples;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioFaseDeGrupos;
import org.springframework.stereotype.Component;

@Component
public class TorneioFactory {

    public TorneioBase criarTorneio(TorneioRequestDTO dto) {
        return switch (dto.getTipo()) {
            case ELIMINATORIA_SIMPLES -> new TorneioEliminatoriaSimples();
            case FASE_DE_GRUPOS -> {
                var t = new TorneioFaseDeGrupos();
                t.setNumeroGrupos(dto.getNumeroGrupos());
                yield t;
            }
        };
    }
}