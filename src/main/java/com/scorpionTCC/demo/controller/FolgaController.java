
package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Folga;
import com.scorpionTCC.demo.service.FolgaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folgas")
@CrossOrigin(origins = "http://localhost:5173")
public class FolgaController {

    private final FolgaService folgaService;

    public FolgaController(FolgaService folgaService) {
        this.folgaService = folgaService;
    }

    @GetMapping("/prestador/{idPrestador}")
    public ResponseEntity<List<Folga>> listar(
            @PathVariable Long idPrestador
    ) {
        return ResponseEntity.ok(
                folgaService.listarPorPrestador(idPrestador)
        );
    }

    @PostMapping("/prestador/{idPrestador}")
    public ResponseEntity<Folga> cadastrar(
            @PathVariable Long idPrestador,
            @RequestBody Folga dados
    ) {
        Folga folga = folgaService.cadastrar(
                idPrestador,
                dados
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(folga);
    }

    @PutMapping("/{idFolga}/prestador/{idPrestador}")
    public ResponseEntity<Folga> editar(
            @PathVariable Long idFolga,
            @PathVariable Long idPrestador,
            @RequestBody Folga dados
    ) {
        return ResponseEntity.ok(
                folgaService.editar(
                        idFolga,
                        idPrestador,
                        dados
                )
        );
    }

    @DeleteMapping("/{idFolga}/prestador/{idPrestador}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long idFolga,
            @PathVariable Long idPrestador
    ) {
        folgaService.excluir(idFolga, idPrestador);

        return ResponseEntity.noContent().build();
    }
}
