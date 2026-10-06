package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "vaga")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")
    private Long id;

    @Column(name = "tipo_vaga")
    private Boolean tipoVaga;

    @Column(name = "descrição_serviço_vaga")
    private String descricaoServicoVaga;

    @Column(name = "data_hora_vaga")
    private LocalDateTime dataHoraVaga;

    @Column(name = "prestador_requerido")
    private String prestadorRequerido;

    @Column(name = "duracao")
    private String duracao;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "pessoa_cuidada")
    private String pessoaCuidada;

    @Column(name = "idade_pessoa_cuidada")
    private int idadePessoaCuidada;

    @Column(name = "nome_contratante")
    private String nomeContratante;

    @ManyToOne
    @JoinColumn(name = "id_contratante")
    private Contratante idContratante;
}
