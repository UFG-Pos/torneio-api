package br.com.ufg.joaoguedes.torneios_api.mappers;

import br.com.ufg.joaoguedes.torneios_api.dtos.TorneioRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.TorneioResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioBase;
import br.com.ufg.joaoguedes.torneios_api.models.TorneioFaseDeGrupos;
import org.springframework.stereotype.Component;

@Component
public class TorneioMapper {

    public void preencherCamposBase(TorneioBase entity, TorneioRequestDTO dto) {
        entity.setNome(dto.getNome());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFim(dto.getDataFim());
        entity.setMaxParticipantes(dto.getMaxParticipantes());
        entity.setStatus(dto.getStatus());
    }
    
    public TorneioResponseDTO toDTO(TorneioBase t) {
        TorneioResponseDTO.TorneioResponseDTOBuilder builder = TorneioResponseDTO.builder()
                .id(t.getId())
                .nome(t.getNome())
                .tipo(t.getTipo())
                .dataInicio(t.getDataInicio())
                .dataFim(t.getDataFim())
                .maxParticipantes(t.getMaxParticipantes())
                .status(t.getStatus());

        if (t instanceof TorneioFaseDeGrupos fase) {
            builder.numeroGrupos(fase.getNumeroGrupos());
        }

        return builder.build();
    }
}