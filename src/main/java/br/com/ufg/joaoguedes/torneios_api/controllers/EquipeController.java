package br.com.ufg.joaoguedes.torneios_api.controllers;

import br.com.ufg.joaoguedes.torneios_api.dtos.EquipeRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.EquipeResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.services.EquipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipes")
@Tag(name = "Equipes", description = "Gerenciamento de equipes")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @PostMapping
    @Operation(summary = "Criar equipe", description = "Cria uma nova equipe associada a um torneio.")
    public ResponseEntity<EquipeResponseDTO> criarEquipe(@Valid @RequestBody EquipeRequestDTO dto) {
        return ResponseEntity.ok(equipeService.criarEquipe(dto));
    }

    @GetMapping("/torneio/{torneioId}")
    @Operation(summary = "Listar equipes por torneio", description = "Retorna as equipes vinculadas a um torneio.")
    public ResponseEntity<List<EquipeResponseDTO>> listarPorTorneio(@PathVariable Long torneioId) {
        return ResponseEntity.ok(equipeService.listarPorTorneio(torneioId));
    }
}