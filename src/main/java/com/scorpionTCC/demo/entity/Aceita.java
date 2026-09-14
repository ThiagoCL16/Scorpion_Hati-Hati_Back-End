package com.scorpionTCC.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "aceita")
public class Aceita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aceita")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_solicitacao")
    private Solicitacao idSolicitacao;

    @ManyToOne
    @JoinColumn(name = "id_prestador")
    private PrestadorServico idPrestadorDeServico;
}
