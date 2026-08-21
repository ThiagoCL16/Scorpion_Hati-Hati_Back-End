package com.scorpionTCC.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "avaliacoes")
    private String avaliacoes;

    @Column(name = "nome_usuario")
    private String nome;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cep")
    private String cep;

    @Column(name = "tipo_usuario")
    private Boolean tipo;
}
