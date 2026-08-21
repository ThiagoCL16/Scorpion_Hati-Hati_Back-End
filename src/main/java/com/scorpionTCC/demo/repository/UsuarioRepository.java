package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public ResponseEntity cadastrarUsuario(Usuario usuario);

}
