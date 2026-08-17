package com.scorpionTCC.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="requer")
public class Requer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requer")
    private Long id;

    @Column(name = "id_agendamento")
    private Long idAgendamento;

    @Column(name = "id_prestador_de_servico")
    private Long idPrestadorDeServico;
}
