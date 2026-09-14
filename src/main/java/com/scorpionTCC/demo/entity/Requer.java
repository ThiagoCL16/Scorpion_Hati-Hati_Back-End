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

    @ManyToOne
    @JoinColumn(name = "id_agendamento")
    private Agendamento idAgendamento;

    @ManyToOne
    @JoinColumn(name = "id_prestador")
    private PrestadorServico idPrestadorDeServico;
}
