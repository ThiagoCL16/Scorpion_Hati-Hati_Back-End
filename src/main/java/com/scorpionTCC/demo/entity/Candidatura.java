
package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import com.scorpionTCC.demo.enums.StatusCandidatura;

@Getter
@Setter
@Entity
@Table(
        name = "candidatura",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"id_vaga", "id_prestador"}
        )
)
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidatura")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_vaga", nullable = false)
    private VagaPublica vaga;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_prestador", nullable = false)
    private PrestadorServico prestador;

    @Column(name = "data_candidatura")
    private LocalDateTime dataCandidatura;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_candidatura", nullable = false)
    private StatusCandidatura status;

    @PrePersist
    public void antesDeSalvar() {
        if (dataCandidatura == null) {
            dataCandidatura = LocalDateTime.now();
        }

        if (status == null) {
            status = StatusCandidatura.PENDENTE;
        }
    }
}

