package br.com.ufg.joaoguedes.torneios_api.models;

import br.com.ufg.joaoguedes.torneios_api.enums.TipoTorneio;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}