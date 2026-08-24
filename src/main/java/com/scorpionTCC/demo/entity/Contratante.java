package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contratante")
public class Contratante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contratante")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
