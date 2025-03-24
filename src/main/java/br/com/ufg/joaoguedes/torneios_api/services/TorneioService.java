package br.com.ufg.joaoguedes.torneios_api.services;

import br.com.ufg.joaoguedes.torneios_api.dtos.TorneioRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.TorneioResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioEliminatoriaSimples;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioFaseDeGrupos;
import br.com.ufg.joaoguedes.torneios_api.repositories.TorneioEliminatoriaSimplesRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.TorneioFaseDeGruposRepository;
import org.springframework.stereotype.Service;

@Service
public class TorneioService {

    private final TorneioEliminatoriaSimplesRepository eliminatoriaRepo;
    private final TorneioFaseDeGruposRepository faseDeGruposRepo;

    public TorneioService(
            TorneioEliminatoriaSimplesRepository eliminatoriaRepo,
            TorneioFaseDeGruposRepository faseDeGruposRepo
    ) {
        this.eliminatoriaRepo = eliminatoriaRepo;
        this.faseDeGruposRepo = faseDeGruposRepo;
    }

    public TorneioResponseDTO criarTorneio(TorneioRequestDTO dto) {
        TorneioBase torneio;

        switch (dto.getTipo()) {
            case ELIMINATORIA_SIMPLES -> {
                TorneioEliminatoriaSimples t = new TorneioEliminatoriaSimples();
                preencherCamposBase(t, dto);
                t.validarRegras();
                torneio = eliminatoriaRepo.save(t);
            }
            case FASE_DE_GRUPOS -> {
                TorneioFaseDeGrupos t = new TorneioFaseDeGrupos();
                preencherCamposBase(t, dto);
                t.setNumeroGrupos(dto.getNumeroGrupos());
                t.validarRegras();
                torneio = faseDeGruposRepo.save(t);
            }
            default -> throw new IllegalArgumentException("Tipo de torneio não suportado.");
        }

        return toResponseDTO(torneio);
    }

    private void preencherCamposBase(TorneioBase t, TorneioRequestDTO dto) {
        t.setNome(dto.getNome());
        t.setDataInicio(dto.getDataInicio());
        t.setDataFim(dto.getDataFim());
        t.setMaxParticipantes(dto.getMaxParticipantes());
        t.setStatus(dto.getStatus());
    }

    private TorneioResponseDTO toResponseDTO(TorneioBase t) {
        TorneioResponseDTO dto = new TorneioResponseDTO();
        dto.setId(t.getId());
        dto.setNome(t.getNome());
        dto.setTipo(t.getTipo());
        dto.setDataInicio(t.getDataInicio());
        dto.setDataFim(t.getDataFim());
        dto.setMaxParticipantes(t.getMaxParticipantes());
        dto.setStatus(t.getStatus());

        if (t instanceof TorneioFaseDeGrupos fase) {
            dto.setNumeroGrupos(fase.getNumeroGrupos());
        }

        return dto;
    }
}