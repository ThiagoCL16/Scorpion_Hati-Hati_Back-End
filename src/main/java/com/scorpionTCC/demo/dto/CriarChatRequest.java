package com.scorpionTCC.demo.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record CriarChatRequest(
        Long idContratante,
        Long idPrestador
){
}
