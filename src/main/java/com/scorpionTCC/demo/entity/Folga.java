
package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "folga")
public class Folga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_folga")
    private Long id;

    @Column(name = "id_prestador", nullable = false)
    private Long idPrestador;

    @Column(name = "data_folga", nullable = false)
    private LocalDate dataFolga;

    @Column(name = "motivo", length = 255)
    private String motivo;
}
