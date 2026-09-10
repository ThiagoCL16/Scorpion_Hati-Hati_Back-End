package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contratante")
@PrimaryKeyJoinColumn(name = "id_contratante")
public class Contratante extends Usuario{ }
