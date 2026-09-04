package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.Usuario;
import com.scorpionTCC.demo.repository.ContratanteRepository;
import com.scorpionTCC.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/contratantes")
public class ContratanteController{

    @Autowired
    private ContratanteRepository contratanteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public Vector buscarContratantePorId(@PathVariable Long id){
        Contratante con = contratanteRepository.getById(id);
        Usuario usuarioCon = usuarioRepository.getById(con.getId());

        Vector a = new Vector();
        a.add(usuarioCon);
        return a;
    }

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody Contratante c){
        contratanteRepository.save(c);
    }
}
