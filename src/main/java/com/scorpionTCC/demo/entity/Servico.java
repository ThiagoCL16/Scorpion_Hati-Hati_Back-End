package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Long id;

    @Column(name = "id_contratante")
    private Long idContratante;

    @Column(name = "especificação")
    private String especificacao;

    @Column(name = "id_prestador_de_servico")
    private Long idPrestadorDeServico;

    @Column(name = "status_servico")
    private String statusServico;

}
