package br.com.ufg.joaoguedes.torneios_api.mappers;

import br.com.ufg.joaoguedes.torneios_api.dtos.JogadorRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.JogadorResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.models.Equipe;
import br.com.ufg.joaoguedes.torneios_api.models.Jogador;
import org.springframework.stereotype.Component;

@Component
public class JogadorMapper {

    public Jogador toEntity(JogadorRequestDTO dto, Equipe equipe) {
        Jogador jogador = new Jogador();
        jogador.setNome(dto.getNome());
        jogador.setEquipe(equipe);
        return jogador;
    }

    public JogadorResponseDTO toDTO(Jogador jogador) {
        JogadorResponseDTO dto = new JogadorResponseDTO();
        dto.setId(jogador.getId());
        dto.setNome(jogador.getNome());
        return dto;
    }
}