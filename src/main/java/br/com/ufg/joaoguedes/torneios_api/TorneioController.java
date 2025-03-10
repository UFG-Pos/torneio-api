package br.com.ufg.joaoguedes.torneios_api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/torneios")
@Tag(name = "Torneios", description = "Gerenciamento de torneios de e-sports")
public class TorneioController {

    @GetMapping
    @Operation(summary = "Listar torneios", description = "Retorna todos os torneios cadastrados.")
    public String listarTorneios() {
        return "Lista de torneios";
    }

    @PostMapping
    @Operation(summary = "Criar torneio", description = "Cria um novo torneio na plataforma.")
    public String criarTorneio() {
        return "Torneio criado!";
    }
}