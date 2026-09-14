package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="realiza")
public class Realiza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_realiza")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_solicitacao")
    private Solicitacao idSolicitacao;

    @ManyToOne
    @JoinColumn(name = "id_contratante")
    private Contratante idContratante;
}
