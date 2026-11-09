package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByIdChatOrderByDataHoraAsc(
            Long idChat
    );

    void deleteByIdChat(Long idChat);
}
