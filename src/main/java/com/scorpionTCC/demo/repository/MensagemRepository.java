package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Mensagem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    @EntityGraph(attributePaths = {"usuario"})
    List<Mensagem> findByChat_IdOrderByDataHoraAscIdAsc(
            Long idChat
    );

    @Transactional
    void deleteByIdChat(Long idChat);
}
