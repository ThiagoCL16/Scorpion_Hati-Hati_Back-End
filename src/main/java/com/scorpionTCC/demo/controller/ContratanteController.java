package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.Usuario;
import com.scorpionTCC.demo.repository.ContratanteRepository;
import com.scorpionTCC.demo.repository.UsuarioRepository;
import com.scorpionTCC.demo.service.ContratanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/contratantes")
public class ContratanteController{
    @Autowired
    private ContratanteService contratanteService;

    @GetMapping("/{id}")
    public ResponseEntity<Contratante> buscarContratantePorId(@PathVariable Long id){
        Optional<Contratante> contratante = contratanteService.findById(id);
        if(contratante.isPresent())
            return ResponseEntity.ok(contratante.get());
        else
            return ResponseEntity.notFound().build();
    }



    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Contratante> atualizarPerfil(@RequestBody Map<String, Object> campos, @PathVariable Long id) {
        Contratante contratante = contratanteService.update(id, campos);

        if(contratante == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(contratante);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Contratante> deletarPerfil(@PathVariable Long id) {
        Contratante contratante = contratanteService.deleteById(id);
        if (contratante == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(contratante);
    }



}
