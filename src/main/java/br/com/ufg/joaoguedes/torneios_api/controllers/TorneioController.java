package br.com.ufg.joaoguedes.torneios_api.controllers;

import br.com.ufg.joaoguedes.torneios_api.dtos.TorneioRequestDTO;
import br.com.ufg.joaoguedes.torneios_api.dtos.TorneioResponseDTO;
import br.com.ufg.joaoguedes.torneios_api.services.PartidaService;
import br.com.ufg.joaoguedes.torneios_api.services.TorneioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torneios")
@Tag(name = "Torneios", description = "Gerenciamento de torneios de e-sports")
@AllArgsConstructor
public class TorneioController {

    private final TorneioService torneioService;
    private final PartidaService partidaService;


    @PostMapping
    @Operation(summary = "Criar torneio", description = "Cria um novo torneio na plataforma.")
    public ResponseEntity<TorneioResponseDTO> criarTorneio(@Valid @RequestBody TorneioRequestDTO requestDTO) {
        TorneioResponseDTO response = torneioService.criarTorneio(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar torneios", description = "Retorna todos os torneios cadastrados.")
    public ResponseEntity<List<TorneioResponseDTO>> listarTorneios() {
        List<TorneioResponseDTO> torneios = torneioService.listarTorneios();
        return ResponseEntity.ok(torneios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar torneio por ID", description = "Retorna os detalhes de um torneio específico.")
    public ResponseEntity<TorneioResponseDTO> buscarTorneioPorId(@PathVariable Long id) {
        TorneioResponseDTO torneio = torneioService.buscarTorneioPorId(id);
        return ResponseEntity.ok(torneio);
    }

    @PostMapping("/{id}/gerar-partidas")
    @Operation(summary = "Gerar partidas do torneio", description = "Gera as partidas automaticamente com base nas equipes cadastradas.")
    public ResponseEntity<Void> gerarPartidas(@PathVariable Long id) {
        partidaService.gerarPartidasParaTorneio(id);
        return ResponseEntity.ok().build();
    }
}