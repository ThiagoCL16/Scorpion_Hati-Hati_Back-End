package com.scorpionTCC.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "calendario")
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calendario")
    private Long id;

    @Column(name = "id_agendamento")
    private Long idAgendamento;
}
