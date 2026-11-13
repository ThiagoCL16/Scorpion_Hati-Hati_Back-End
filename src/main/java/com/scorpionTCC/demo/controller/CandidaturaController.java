
package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Candidatura;
import com.scorpionTCC.demo.service.CandidaturaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturaController {

    private final CandidaturaService candidaturaService;

    public CandidaturaController(
            CandidaturaService candidaturaService
    ) {
        this.candidaturaService = candidaturaService;
    }

    // Prestador envia candidatura
    @PostMapping("/vaga/{idVaga}/prestador/{idPrestador}")
    public ResponseEntity<Candidatura> candidatar(
            @PathVariable Long idVaga,
            @PathVariable Long idPrestador
    ) {
        Candidatura candidatura =
                candidaturaService.candidatar(idVaga, idPrestador);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(candidatura);
    }

    // Contratante consulta candidaturas de uma vaga
    @GetMapping("/vaga/{idVaga}")
    public ResponseEntity<List<Candidatura>> listarPorVaga(
            @PathVariable Long idVaga
    ) {
        return ResponseEntity.ok(
                candidaturaService.listarPorVaga(idVaga)
        );
    }

    // Consulta candidaturas do prestador
    @GetMapping("/prestador/{idPrestador}")
    public ResponseEntity<List<Candidatura>> listarPorPrestador(
            @PathVariable Long idPrestador
    ) {
        return ResponseEntity.ok(
                candidaturaService.listarPorPrestador(idPrestador)
        );
    }
}
