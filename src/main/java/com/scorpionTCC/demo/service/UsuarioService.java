package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.PrestadorServico;
import com.scorpionTCC.demo.entity.Usuario;
import com.scorpionTCC.demo.repository.ContratanteRepository;
import com.scorpionTCC.demo.repository.PrestadorRepository;
import com.scorpionTCC.demo.repository.UsuarioRepository;
import com.scorpionTCC.demo.responses.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
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

    public LoginResponse logIn(Map<String, Object> campos)
    {
        String nomeUsuario = campos.getOrDefault("nomeUsuario", "").toString();
        String senha = campos.getOrDefault("senha", "").toString();
        Usuario usuario = null;

        if(nomeUsuario.isEmpty() || senha.isEmpty())
            return new LoginResponse("Campo vazio", null);

        Optional<Usuario> u = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(u.isPresent()) {
            usuario = u.get();
            if (usuario.getSenha().equals(senha))
                return new LoginResponse("Senha correta!", usuario);
            else
                return new LoginResponse("Senha incorreta!", null);
        }
        else
            return  new LoginResponse("Usuário não encontrado!", null);
    }
}
