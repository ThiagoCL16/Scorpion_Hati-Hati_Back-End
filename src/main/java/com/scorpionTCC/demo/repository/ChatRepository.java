package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Chat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @EntityGraph(attributePaths = {
            "contratante",
            "prestadorServico"
    })

    List<Chat> findByContratante_IdOrPrestadorServico_IdOrderByIdDesc(
            Long idContratante,
            Long idPrestador
    );
    Optional<Chat> findByContratante_IdAndPrestadorServico_Id(
            Long idContratante,
            Long idPrestador
    );

    @Query("""
    SELECT c FROM Chat c
    WHERE c.id = :idChat
    AND (
        c.contratante.id = :idUsuario
        OR c.prestadorServico.id = :idUsuario
        )
    """)

    Optional<Chat> buscarChatDoUsuario(
        @Param("idChat") Long idChat,
        @Param("idUsuario") Long idUsuario
    );





}
