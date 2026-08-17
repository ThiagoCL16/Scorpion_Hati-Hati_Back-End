package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "beneficios")
public class Beneficios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficios")
    private Long id;

    @Column(name = "prioridade_feed")
    private Boolean prioridadeFeed;

    @Column(name = "zero_propaganda")
    private Boolean zeroPropaganda;

}
