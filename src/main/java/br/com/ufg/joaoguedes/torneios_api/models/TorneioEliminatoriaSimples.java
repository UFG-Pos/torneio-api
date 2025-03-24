package br.com.ufg.joaoguedes.torneios_api.models;

import br.com.ufg.joaoguedes.torneios_api.enums.TipoTorneio;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}