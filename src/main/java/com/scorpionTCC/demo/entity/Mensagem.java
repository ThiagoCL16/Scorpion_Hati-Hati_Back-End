
package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mensagem")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensagem")
    private Long id;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "conteudo", length = 1000)
    private String conteudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chat")
    private Chat chat;

    @PrePersist
    public void antesDeSalvar() {
        if (dataHora == null) {
            dataHora = LocalDateTime.now();
        }
    }
}
