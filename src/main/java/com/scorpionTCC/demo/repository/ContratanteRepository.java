package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Contratante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContratanteRepository extends JpaRepository<Contratante, Long> {
    //Criar método aqui no Repository
    //Resolver problema: Salvar infos de usuario em contratante

}
