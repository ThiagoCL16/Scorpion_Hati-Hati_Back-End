package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Vaga;
import com.scorpionTCC.demo.repository.VagaRepository;
import com.scorpionTCC.demo.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {
    @Autowired
    VagaService vagaService;

    @GetMapping("/vagas/GetAll")
    public ResponseEntity<List<Vaga>> buscarVagas(){
        List<Vaga> vagas = vagaService.GetVagas();
        if (vagas == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(vagas);
    }

    @GetMapping("/vagas/{id}")
    public ResponseEntity<Vaga> buscarVagaPorId(@PathVariable Long id)
    {
        Vaga vaga = vagaService.GetVagaById(id);

        if(vaga == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(vaga);
    }

}
