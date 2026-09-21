package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.Vaga;
import com.scorpionTCC.demo.repository.PrestadorRepository;
import com.scorpionTCC.demo.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {
    @Autowired
    private PrestadorService prestadorService;

    @GetMapping("/vagas/GetAll")
    public ResponseEntity<List<Vaga>> buscarVagas(){
        List<Vaga> vagas = prestadorService.GetVagas();
        if (vagas == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(vagas);
    }

    @GetMapping("/vagas/{id}")
    public ResponseEntity<Vaga> buscarVagaPorId(@PathVariable Long id)
    {
        Vaga vaga = prestadorService.GetVagaById(id);

        if(vaga == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(vaga);
    }
}
