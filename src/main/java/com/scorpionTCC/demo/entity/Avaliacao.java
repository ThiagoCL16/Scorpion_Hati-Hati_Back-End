package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Long id;

    @Column(name = "tipo_avaliacao")
    private Boolean tipoAvaliacao;

    @Column(name = "status_avaliacao")
    private Boolean statusAvaliacao;

    @Column(name = "data_avaliacao")
    private LocalDate dataAvaliacao;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "id_contratante")
    private Long idContratante;

    @Column(name = "id_prestador_de_servico")
    private Long idPrestadorDeServico;
}
