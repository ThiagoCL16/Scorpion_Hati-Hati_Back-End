package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contratante")
public class Contratante extends Usuario{
    @Id
    @Column(name = "id_contratante")
    private Long id;
}
