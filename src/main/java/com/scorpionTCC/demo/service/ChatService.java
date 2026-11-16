package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.dto.CriarChatRequest;
import com.scorpionTCC.demo.dto.EnviarMensagemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import java.util.Objects;
@Service
public class ChatService {


    // Validar dados para criação de conversa
    private void validarCriarChat(CriarChatRequest request) {

        if (request == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Dados da conversa não informados."
            );
        }

        if (request.idContratante() == null ||
                request.idContratante() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "ID do contratante inválido."
            );
        }

        if (request.idPrestador() == null ||
                request.idPrestador() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "ID do prestador inválido."
            );
        }

        if (Objects.equals(
                request.idContratante(),
                request.idPrestador()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Os participantes devem ser diferentes."
            );
        }
        validarCriarChat(request);
    }

    // Validar dados para envio de mensagem
    private void validarEnviarMensagem(
            EnviarMensagemRequest request
    ) {

        if (request == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Dados da mensagem não informados."
            );
        }

        if (request.idUsuario() == null ||
                request.idUsuario() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "ID do usuário inválido."
            );
        }

        if (request.conteudo() == null ||
                request.conteudo().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A mensagem não pode estar vazia."
            );
        }

        if (request.conteudo().length() > 1000) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A mensagem ultrapassou o limite de 1000 caracteres."
            );
        }
    }

}
