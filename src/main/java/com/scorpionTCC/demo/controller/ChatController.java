
package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.dto.CriarChatRequest;
import com.scorpionTCC.demo.dto.EnviarMensagemRequest;
import com.scorpionTCC.demo.dto.ChatResponse;
import com.scorpionTCC.demo.dto.MensagemResponse;

import com.scorpionTCC.demo.service.ChatService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // Criar ou localizar uma conversa
    @PostMapping
    public ChatResponse criarChat(
            @RequestBody CriarChatRequest request
    ) {
        return chatService.criarChat(request);
    }

    // Listar conversas de um usuário
    @GetMapping("/usuario/{idUsuario}")
    public List<ChatResponse> listarChats(
            @PathVariable Long idUsuario
    ) {
        return chatService.listarChats(idUsuario);
    }

    // Buscar mensagens de uma conversa
    @GetMapping("/{idChat}/mensagens")
    public List<MensagemResponse> listarMensagens(
            @PathVariable Long idChat,
            @RequestParam Long idUsuario
    ) {
        return chatService.listarMensagens(
                idChat,
                idUsuario
        );
    }

    // Enviar mensagem
    @PostMapping("/{idChat}/mensagens")
    public MensagemResponse enviarMensagem(
            @PathVariable Long idChat,
            @RequestBody EnviarMensagemRequest request
    ) {
        return chatService.enviarMensagem(
                idChat,
                request
        );
    }
}
