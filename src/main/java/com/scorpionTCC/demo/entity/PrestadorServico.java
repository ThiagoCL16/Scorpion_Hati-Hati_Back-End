package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="prestador_de_servico")
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
