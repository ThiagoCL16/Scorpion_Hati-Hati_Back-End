package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.VagaPublica;
import com.scorpionTCC.demo.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
@CrossOrigin("*")
public class VagaController {
    @Autowired
    VagaService vagaService;

    @GetMapping("/GetAll")
    public ResponseEntity<List<VagaPublica>> buscarVagas(){
        // TODO Implementar função de filtros
        List<VagaPublica> vagas = vagaService.GetVagas();
        if (vagas == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(vagas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaPublica> buscarVagaPorId(@PathVariable Long id)
    {
        VagaPublica vaga = vagaService.GetVagaById(id);

        if(vaga == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(vaga);
    }

    @PostMapping("/criar")
    public ResponseEntity<VagaPublica> criarVaga(@RequestBody VagaPublica vaga)
    {
        // TODO Ver se está funcionando corretamente

        VagaPublica v = vagaService.save(vaga, vaga.getIdContratante().getId());
        return ResponseEntity.ok(vaga);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        if (vagaService.deleteById(id))
            return ResponseEntity.noContent().build(); // 204
        else
            return ResponseEntity.notFound().build();  // 404
    }

}
