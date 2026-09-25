package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.PrestadorServico;
import com.scorpionTCC.demo.entity.Vaga;
import com.scorpionTCC.demo.repository.PrestadorRepository;
import com.scorpionTCC.demo.service.ContratanteService;
import com.scorpionTCC.demo.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {
    @Autowired
    private PrestadorService prestadorService;

    @GetMapping("/{id}")
    public ResponseEntity<PrestadorServico> buscarPrestadorPorId(@PathVariable Long id){
        Optional<PrestadorServico> prestador = prestadorService.findById(id);
        if(prestador.isPresent())
            return ResponseEntity.ok(prestador.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PrestadorServico> cadastrar(@RequestBody PrestadorServico p){
        PrestadorServico prestador = prestadorService.save(p);
        return ResponseEntity.ok(prestador);
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<PrestadorServico> atualizarPerfil(@RequestBody Map<String, Object> campos, @PathVariable Long id) {
        PrestadorServico prestador = prestadorService.update(id, campos);

        if(prestador == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(prestador);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<PrestadorServico> deletarPerfil(@PathVariable Long id){
        PrestadorServico prestador = prestadorService.deleteById(id);
        if(prestador == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(prestador);
    }
}
