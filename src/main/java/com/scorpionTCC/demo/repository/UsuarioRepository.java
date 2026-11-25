package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT u FROM Usuario u WHERE u.nome=?1")
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}
