package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat")
    private Long id;

    @Column(name = "status_chat")
    private Boolean statusChat;

    @Column(name = "id_prestador_de_servico")
    private Long idPrestadorDeServico;

    @Column(name = "id_contratante")
    private Long idContratante;
}
