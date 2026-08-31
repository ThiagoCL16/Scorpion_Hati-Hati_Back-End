package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.repository.ContratanteRepository;
import com.scorpionTCC.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contratantes")
public class ContratanteController{

    @Autowired
    private ContratanteRepository contratanteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody Contratante c){
        usuarioRepository.save(c.getUsuario());
        contratanteRepository.save(c);
    }
}
