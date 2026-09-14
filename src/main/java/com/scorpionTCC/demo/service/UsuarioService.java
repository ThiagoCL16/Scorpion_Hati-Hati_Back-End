package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.PrestadorServico;
import com.scorpionTCC.demo.entity.Usuario;
import com.scorpionTCC.demo.repository.ContratanteRepository;
import com.scorpionTCC.demo.repository.PrestadorRepository;
import com.scorpionTCC.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ContratanteRepository contratanteRepository;

    @Autowired
    PrestadorRepository prestadorRepository;

    /* TODO Ver se a gente mantém esse método na parte do usuário
    public Usuario save(Usuario usuario){
        Usuario u = usuarioRepository.save(usuario);
        boolean tipoUsuario = u.getTipo();
        if(tipoUsuario == true) // True é contratante
        {
            Contratante c = new Contratante();
            c.setId(u.getId());
            contratanteRepository.save(c);
        }
        else
        {
            PrestadorServico p = new PrestadorServico();
            p.setId(u.getId());
            prestadorRepository.save(p);
        }
        return u;
    }
     */
}
