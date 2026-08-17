package com.scorpionTCC.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PrestadorServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestador_de_servico")
    private Long id;

    @Column(name = "anexos")
    private String anexos;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo_assinatura")
    private Boolean tipoAssinatura;

    @Column(name = "id_usuario")
    private Long idUsuario;
}
