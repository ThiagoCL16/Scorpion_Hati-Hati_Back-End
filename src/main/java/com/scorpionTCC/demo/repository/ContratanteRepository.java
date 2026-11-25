package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Contratante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ContratanteRepository extends JpaRepository<Contratante, Long> {
    //@Query(value = "SELECT u FROM contratante c, usuario u WHERE c.nome=:1")
    //Optional<Contratante> findByNomeUsuario(String nomeUsuario);
}
