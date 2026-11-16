package com.scorpionTCC.demo.dto;

public record EnviarMensagemRequest(
        Long idUsuario,
        String conteudo
) {
}
