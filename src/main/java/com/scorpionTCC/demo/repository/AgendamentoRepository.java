package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository
        extends JpaRepository<Agendamento, Long> {

    boolean existsByIdVaga_Id(Long idVaga);

}
