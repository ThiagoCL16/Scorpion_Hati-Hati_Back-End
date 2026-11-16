package com.scorpionTCC.demo.dto;

import java.time.LocalDateTime;

public record MensagemResponse(
        Long idMensagem,
        Long idChat,

        Long idUsuario,
        String nomeUsuario,

        LocalDateTime dataHora,
        String conteudo

) {
}
