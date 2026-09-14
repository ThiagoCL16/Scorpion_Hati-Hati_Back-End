package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.entity.Contratante;

public class UsuarioService {

    public Usuario save(Contratante contratante){
        Contratante c = contratanteRepository.save(contratante);
        return c;
    }
}
