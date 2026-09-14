package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="solicitacao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_contratante")
    private Contratante idContratante;

    @ManyToOne
    @JoinColumn(name = "id_prestador")
    private PrestadorServico idPrestadorDeServico;

    @Column(name = "especificacao_solicitacao")
    private String especificacaoSolicitacao;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

}
