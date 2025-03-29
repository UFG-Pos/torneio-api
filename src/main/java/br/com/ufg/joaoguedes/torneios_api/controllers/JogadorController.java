package br.com.ufg.joaoguedes.torneios_api.controllers;

import br.com.ufg.joaoguedes.torneios_api.dtos.JogadorRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.JogadorResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.services.JogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
@Tag(name = "Jogadores", description = "Gerenciamento de jogadores")
public class JogadorController {

    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping
    @Operation(summary = "Criar jogador", description = "Cria um novo jogador vinculado a uma equipe.")
    public ResponseEntity<JogadorResponseDTO> criarJogador(@Valid @RequestBody JogadorRequestDTO dto) {
        return ResponseEntity.ok(jogadorService.criarJogador(dto));
    }

    @GetMapping("/equipe/{equipeId}")
    @Operation(summary = "Listar jogadores por equipe", description = "Retorna os jogadores vinculados a uma equipe.")
    public ResponseEntity<List<JogadorResponseDTO>> listarPorEquipe(@PathVariable Long equipeId) {
        return ResponseEntity.ok(jogadorService.listarPorEquipe(equipeId));
    }
}