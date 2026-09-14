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

    @ManyToOne
    @JoinColumn(name = "id_contratante")
    private Contratante idContratante;

    @Column(name = "especificação")
    private String especificacao;

    @ManyToOne
    @JoinColumn(name = "id_prestador")
    private PrestadorServico idPrestadorDeServico;

    @Column(name = "status_servico")
    private String statusServico;

}
