package br.com.ufg.joaoguedes.torneios_api.controllers;

import br.com.ufg.joaoguedes.torneios_api.dtos.AtualizarPartidaDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.PartidaRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.PartidaResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.services.PartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/partidas")
@Tag(name = "Partidas", description = "Gerenciamento de partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping
    @Operation(summary = "Criar partida", description = "Cria uma nova partida entre duas equipes de um torneio.")
    public ResponseEntity<PartidaResponseDTO> criarPartida(@Valid @RequestBody PartidaRequestDTO dto) {
        return ResponseEntity.ok(partidaService.criarPartida(dto));
    }

    @GetMapping("/torneio/{torneioId}")
    @Operation(summary = "Listar partidas por torneio", description = "Retorna todas as partidas de um torneio.")
    public ResponseEntity<List<PartidaResponseDTO>> listarPorTorneio(@PathVariable Long torneioId) {
        return ResponseEntity.ok(partidaService.listarPorTorneio(torneioId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar partida", description = "Atualiza o placar e o status de uma partida.")
    public ResponseEntity<PartidaResponseDTO> atualizarPartida(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarPartidaDTO dto
    ) {
        return ResponseEntity.ok(partidaService.atualizarPartida(id, dto));
    }
}