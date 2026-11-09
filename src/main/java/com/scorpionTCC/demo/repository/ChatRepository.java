package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByIdContratanteOrIdPrestador(
            Long idContrante,
            Long idPrestador
    );
    Optional<Chat> findByIdPrestadorOrIdContratante(
            Long idContrante,
            Long idPrestador
    );

    Optional<Chat> findbyIdChatAndIdContranteOrIdPrestador(
      Long idChat1,
      Long idContrante,
      Long idChat2,
      Long idPrestado
    );





}
