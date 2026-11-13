
package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidaturaRepository
        extends JpaRepository<Candidatura, Long> {

    // Consultar candidaturas de uma vaga pública
    List<Candidatura> findByVaga_Id(Long idVaga);

    // Consultar candidaturas de um prestador
    List<Candidatura> findByPrestador_Id(Long idPrestador);

    // Verificar se o prestador já se candidatou
    boolean existsByVaga_IdAndPrestador_Id(
            Long idVaga,
            Long idPrestador
    );

    // Consultar uma candidatura específica
    Optional<Candidatura> findByVaga_IdAndPrestador_Id(
            Long idVaga,
            Long idPrestador
    );
}
