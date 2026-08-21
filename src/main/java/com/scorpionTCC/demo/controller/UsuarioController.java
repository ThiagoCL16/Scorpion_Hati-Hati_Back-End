package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Usuario;
import com.scorpionTCC.demo.repository.ContratanteRepository;
import com.scorpionTCC.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario){
        if(usuario.getTipo() == true) { // Contratante
            ContratanteRepository.save(usuario);
        }
    }
}
