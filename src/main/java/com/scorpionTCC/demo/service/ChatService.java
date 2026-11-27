
package com.scorpionTCC.demo.service;

// DTOs
import com.scorpionTCC.demo.dto.CriarChatRequest;
import com.scorpionTCC.demo.dto.EnviarMensagemRequest;
import com.scorpionTCC.demo.dto.ChatResponse;
import com.scorpionTCC.demo.dto.MensagemResponse;

// Entidades
import com.scorpionTCC.demo.entity.Chat;
import com.scorpionTCC.demo.entity.Mensagem;
import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.PrestadorServico;
import com.scorpionTCC.demo.entity.Usuario;

// Repositories
import com.scorpionTCC.demo.repository.ChatRepository;
import com.scorpionTCC.demo.repository.MensagemRepository;
import com.scorpionTCC.demo.repository.ContratanteRepository;
import com.scorpionTCC.demo.repository.PrestadorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final MensagemRepository mensagemRepository;
    private final ContratanteRepository contratanteRepository;
    private final PrestadorRepository prestadorRepository;

    // Construtor
    public ChatService(
            ChatRepository chatRepository,
            MensagemRepository mensagemRepository,
            ContratanteRepository contratanteRepository,
            PrestadorRepository prestadorRepository
    ) {
        this.chatRepository = chatRepository;
        this.mensagemRepository = mensagemRepository;
        this.contratanteRepository = contratanteRepository;
        this.prestadorRepository = prestadorRepository;
    }


    // ==========================================
    // 1. VALIDAÇÕES
    // ==========================================

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

        if (request.conteudo().trim().length() > 1000) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A mensagem ultrapassou o limite de 1000 caracteres."
            );
        }
    }


    // Validar identificadores
    private void validarId(Long id) {

        if (id == null || id <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Identificador inválido."
            );
        }
    }


    // ==========================================
    // 2. CRIAR OU BUSCAR CONVERSA
    // ==========================================

    @Transactional
    public ChatResponse criarChat(CriarChatRequest request) {

        validarCriarChat(request);

        // Buscar contratante
        Contratante contratante = contratanteRepository
                .findById(request.idContratante())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Contratante não encontrado."
                ));

        // Buscar prestador
        PrestadorServico prestador = prestadorRepository
                .findById(request.idPrestador())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prestador não encontrado."
                ));

        // Procurar conversa existente
        Chat chat = chatRepository
                .findByContratante_IdAndPrestadorServico_Id(
                        contratante.getId(),
                        prestador.getId()
                )
                .orElseGet(() -> {

                    Chat novoChat = new Chat();

                    novoChat.setContratante(contratante);
                    novoChat.setPrestadorServico(prestador);
                    novoChat.setStatusChat(true);

                    return chatRepository.save(novoChat);
                });

        return converterChat(chat);
    }


    // ==========================================
    // 3. LISTAR CONVERSAS DE UM USUÁRIO
    // ==========================================

    @Transactional(readOnly = true)
    public List<ChatResponse> listarChats(Long idUsuario) {

        validarId(idUsuario);

        return chatRepository
                .findByContratante_IdOrPrestadorServico_IdOrderByIdDesc(
                        idUsuario,
                        idUsuario
                )
                .stream()
                .map(this::converterChat)
                .toList();
    }


    // ==========================================
    // 4. LISTAR MENSAGENS DE UMA CONVERSA
    // ==========================================

    @Transactional(readOnly = true)
    public List<MensagemResponse> listarMensagens(
            Long idChat,
            Long idUsuario
    ) {

        validarId(idChat);
        validarId(idUsuario);

        // Verificar se o usuário participa do chat
        validarParticipante(idChat, idUsuario);

        // Buscar mensagens em ordem cronológica
        return mensagemRepository
                .findByChat_IdOrderByDataHoraAscIdAsc(idChat)
                .stream()
                .map(this::converterMensagem)
                .toList();
    }


    // ==========================================
    // 5. ENVIAR MENSAGEM
    // ==========================================

    @Transactional
    public MensagemResponse enviarMensagem(
            Long idChat,
            EnviarMensagemRequest request
    ) {

        validarId(idChat);
        validarEnviarMensagem(request);

        // Verificar participação do usuário
        Chat chat = validarParticipante(
                idChat,
                request.idUsuario()
        );

        // Verificar se o chat está encerrado
        if (Boolean.FALSE.equals(chat.getStatusChat())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Esta conversa está encerrada."
            );
        }

        // Identificar quem enviou a mensagem
        Usuario remetente;

        if (Objects.equals(
                chat.getContratante().getId(),
                request.idUsuario()
        )) {

            remetente = chat.getContratante();

        } else {

            remetente = chat.getPrestadorServico();
        }

        // Criar mensagem
        Mensagem mensagem = new Mensagem();

        mensagem.setChat(chat);
        mensagem.setUsuario(remetente);
        mensagem.setConteudo(request.conteudo().trim());

        // Salvar mensagem no banco
        Mensagem mensagemSalva =
                mensagemRepository.save(mensagem);

        return converterMensagem(mensagemSalva);
    }


    // ==========================================
    // 6. VALIDAR PARTICIPANTE DA CONVERSA
    // ==========================================

    private Chat validarParticipante(
            Long idChat,
            Long idUsuario
    ) {

        // Buscar conversa
        Chat chat = chatRepository.findById(idChat)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Conversa não encontrada."
                ));

        // Verificar contratante
        boolean ehContratante =
                chat.getContratante() != null &&
                        Objects.equals(
                                chat.getContratante().getId(),
                                idUsuario
                        );

        // Verificar prestador
        boolean ehPrestador =
                chat.getPrestadorServico() != null &&
                        Objects.equals(
                                chat.getPrestadorServico().getId(),
                                idUsuario
                        );

        if (!ehContratante && !ehPrestador) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não participa desta conversa."
            );
        }

        return chat;
    }


    // ==========================================
    // 7. CONVERTER CHAT PARA DTO
    // ==========================================

    private ChatResponse converterChat(Chat chat) {

        return new ChatResponse(
                chat.getId(),
                chat.getStatusChat(),

                chat.getContratante().getId(),
                chat.getContratante().getNome(),

                chat.getPrestadorServico().getId(),
                chat.getPrestadorServico().getNome()
        );
    }


    // ==========================================
    // 8. CONVERTER MENSAGEM PARA DTO
    // ==========================================

    private MensagemResponse converterMensagem(
            Mensagem mensagem
    ) {

        return new MensagemResponse(
                mensagem.getId(),
                mensagem.getChat().getId(),

                mensagem.getUsuario().getId(),
                mensagem.getUsuario().getNome(),

                mensagem.getDataHora(),
                mensagem.getConteudo()
        );
    }

}
