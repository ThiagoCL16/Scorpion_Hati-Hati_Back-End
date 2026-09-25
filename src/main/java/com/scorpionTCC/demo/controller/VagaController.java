package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Vaga;
import com.scorpionTCC.demo.repository.VagaRepository;
import com.scorpionTCC.demo.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {
    @Autowired
    VagaService vagaService;

    @GetMapping("/GetAll")
    public ResponseEntity<List<Vaga>> buscarVagas(){
        // TODO Implementar função de filtros
        List<Vaga> vagas = vagaService.GetVagas();
        if (vagas == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(vagas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> buscarVagaPorId(@PathVariable Long id)
    {
        Vaga vaga = vagaService.GetVagaById(id);

        if(vaga == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(vaga);
    }

    @PostMapping("/criar")
    public ResponseEntity<Vaga> criarVaga(@RequestBody Vaga vaga)
    {
        // TODO Ver se está funcionando corretamente

        Vaga v = vagaService.save(vaga, vaga.getIdContratante().getId());
        return ResponseEntity.ok(vaga);
    }

}
