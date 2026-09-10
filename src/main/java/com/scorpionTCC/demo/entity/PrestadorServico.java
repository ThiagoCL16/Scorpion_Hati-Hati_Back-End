package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="prestador_de_servico")
@PrimaryKeyJoinColumn(name = "id_prestador")
public class PrestadorServico extends Usuario {
    @Column(name = "anexos")
    private String anexos;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo_assinatura")
    private Boolean tipoAssinatura;
}
