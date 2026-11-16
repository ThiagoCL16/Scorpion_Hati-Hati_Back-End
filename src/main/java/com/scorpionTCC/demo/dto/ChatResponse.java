package com.scorpionTCC.demo.dto;

public record ChatResponse(
        Long idChat,
        Boolean statusChat,

        Long idContratante,
        String nomeContratante,

        Long idPrestador,
        String nomePrestador

) {
}
