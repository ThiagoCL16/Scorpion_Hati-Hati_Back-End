package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.Usuario;
import com.scorpionTCC.demo.responses.LoginResponse;
import com.scorpionTCC.demo.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    UsuarioService usuarioService;

    @PostMapping("/entrar")
    public ResponseEntity<LoginResponse> entrarNaConta(@RequestBody Map<String, Object> campos)
    {
        LoginResponse loginResponse = usuarioService.logIn(campos);

        return ResponseEntity.ok(loginResponse);
    }
}
