package br.com.ufg.joaoguedes.torneios_api.services;

import br.com.ufg.joaoguedes.torneios_api.dtos.TorneioRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.TorneioResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.factories.TorneioFactory;
import br.com.ufg.joaoguedes.torneios_api.mappers.TorneioMapper;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioEliminatoriaSimples;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioFaseDeGrupos;
import br.com.ufg.joaoguedes.torneios_api.repositories.TorneioEliminatoriaSimplesRepository;
import br.com.ufg.joaoguedes.torneios_api.repositories.TorneioFaseDeGruposRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TorneioService {

    private final TorneioEliminatoriaSimplesRepository eliminatoriaRepo;
    private final TorneioFaseDeGruposRepository faseDeGruposRepo;
    private final TorneioFactory factory;
    private final TorneioMapper mapper;

    public TorneioService(
            TorneioEliminatoriaSimplesRepository eliminatoriaRepo,
            TorneioFaseDeGruposRepository faseDeGruposRepo,
            TorneioFactory factory,
            TorneioMapper mapper
    ) {
        this.eliminatoriaRepo = eliminatoriaRepo;
        this.faseDeGruposRepo = faseDeGruposRepo;
        this.factory = factory;
        this.mapper = mapper;
    }

    public TorneioResponseDTO criarTorneio(TorneioRequestDTO dto) {
        TorneioBase entity = factory.criarTorneio(dto);
        mapper.preencherCamposBase(entity, dto);
        entity.validarRegras();

        TorneioBase salvo;

        if (entity instanceof TorneioFaseDeGrupos) {
            salvo = faseDeGruposRepo.save((TorneioFaseDeGrupos) entity);
        } else {
            salvo = eliminatoriaRepo.save((TorneioEliminatoriaSimples) entity);
        }

        return mapper.toDTO(salvo);
    }

    public List<TorneioResponseDTO> listarTorneios() {
        List<TorneioBase> todos = new ArrayList<>();
        todos.addAll(faseDeGruposRepo.findAll());
        todos.addAll(eliminatoriaRepo.findAll());

        return todos.stream()
                .map(mapper::toDTO)
                .toList();
    }

    public TorneioResponseDTO buscarTorneioPorId(Long id) {
        TorneioBase torneio = Stream.of(
                        eliminatoriaRepo.findById(id),
                        faseDeGruposRepo.findById(id)
                ).filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Torneio não encontrado"));

        return mapper.toDTO(torneio);
    }
}