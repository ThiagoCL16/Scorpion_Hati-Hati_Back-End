package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Mensagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MensagemRepository
        extends JpaRepository<Mensagem, Long> {

    // Buscar mensagens de um chat em ordem cronológica
    List<Mensagem> findByChat_IdOrderByDataHoraAscIdAsc(
            Long idChat
    );

    // Excluir mensagens de um chat
    @Transactional
    void deleteByChat_Id(Long idChat);
}