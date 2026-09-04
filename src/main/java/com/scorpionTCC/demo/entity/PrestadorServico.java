package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="prestador_de_servico")
public class PrestadorServico extends Usuario {
    @Id
    @Column(name = "id_prestador")
    private Long id;

    @Column(name = "anexos")
    private String anexos;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo_assinatura")
    private Boolean tipoAssinatura;

}
