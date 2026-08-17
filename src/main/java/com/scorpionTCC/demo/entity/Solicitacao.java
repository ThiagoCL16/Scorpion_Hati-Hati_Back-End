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

    @Column(name = "id_contratante")
    private Long idContratante;

    @Column(name = "id_prestador_de_servico")
    private Long idPrestadorDeServico;

    @Column(name = "especificacao_solicitacao")
    private String especificacaoSolicitacao;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

}
