package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Contratante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UsuarioController {

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario u){
        Usuario usuario = usuarioService.save(u);
        return ResponseEntity.ok(usuario);
    }
}
