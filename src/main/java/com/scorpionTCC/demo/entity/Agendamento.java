package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long id;

    @Column(name = "status_agendamento")
    private String statusAgendamento;

    @ManyToOne
    @JoinColumn(name = "id_contratante")
    private Contratante idContratante;

    @ManyToOne
    @JoinColumn(name = "id_prestador")
    private PrestadorServico idPrestadorDeServico;

    @Column(name = "data_hora_agendamento")
    private LocalDateTime dataHoraAgendamento;

    @Column(name = "local_servico")
    private String localServico;

    @ManyToOne
    @JoinColumn(name = "id_solicitacao")
    private Solicitacao idSolicitacao;

}
