package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.dto.ChatResponse;
import com.scorpionTCC.demo.dto.CriarChatRequest;
import com.scorpionTCC.demo.dto.EnviarMensagemRequest;
import com.scorpionTCC.demo.dto.MensagemResponse;
import com.scorpionTCC.demo.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @Autowired
    private ChatResponse criarChat;
    // Criar um novo chat
    @PostMapping
    public ChatResponse criarChat(@RequestBody CriarChatRequest request) {
        return chatService.criarChat(request);
    }

    // Listar chats de um usuário
    @GetMapping("/usuario/{idUsuario}")
    public List<ChatResponse> listarChats(
            @PathVariable Long idUsuario) {

        return chatService.listarChats(idUsuario);
    }

    // Listar mensagens de um chat
    @GetMapping("/{idChat}/mensagens")
    public List<MensagemResponse> listarMensagens(
            @PathVariable Long idChat,
            @RequestParam Long idUsuario) {

        return chatService.listarMensagens(idChat, idUsuario);
    }

    // Enviar uma mensagem
    @PostMapping("/{idChat}/mensagens")
    public MensagemResponse enviarMensagem(
            @PathVariable Long idChat,
            @RequestBody EnviarMensagemRequest request) {

        return chatService.enviarMensagem(idChat, request);
    }
}