
package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository
        extends JpaRepository<Agendamento, Long> {

    // Verifica se uma vaga pública já gerou agendamento
    boolean existsByIdVaga_Id(Long idVaga);

    // Consulta os agendamentos de um prestador
    List<Agendamento> findByIdPrestadorDeServico_Id(
            Long idPrestador
    );
}
