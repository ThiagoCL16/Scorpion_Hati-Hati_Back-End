
package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Folga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FolgaRepository extends JpaRepository<Folga, Long> {

    List<Folga> findByIdPrestadorOrderByDataFolgaAsc(
            Long idPrestador
    );

    boolean existsByIdPrestadorAndDataFolga(
            Long idPrestador,
            LocalDate dataFolga
    );
}
