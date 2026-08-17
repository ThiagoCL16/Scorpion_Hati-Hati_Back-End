package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "premium")
public class Premium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_premium")
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_beneficios")
    private Long idBeneficios;

    @Column(name = "tipo_premium")
    private Boolean tipoPremium;

    @Column(name = "status_premium")
    private String statusPremium;

    @Column(name = "renovacao_automatica")
    private Boolean renovacaoAutomatica;

    @Column(name = "metodo_pagamento")
    private String metodoPagamento;


}
