
package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.entity.Folga;
import com.scorpionTCC.demo.repository.FolgaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class FolgaService {

    private final FolgaRepository folgaRepository;
    private final JdbcTemplate jdbcTemplate;

    public FolgaService(
            FolgaRepository folgaRepository,
            JdbcTemplate jdbcTemplate
    ) {
        this.folgaRepository = folgaRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Folga> listarPorPrestador(Long idPrestador) {
        return folgaRepository
                .findByIdPrestadorOrderByDataFolgaAsc(idPrestador);
    }

    private Folga buscarFolgaDoPrestador(
            Long idFolga,
            Long idPrestador
    ) {
        Folga folga = folgaRepository.findById(idFolga)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Folga não encontrada"
                ));

        if (!folga.getIdPrestador().equals(idPrestador)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Esta folga pertence a outro prestador"
            );
        }

        return folga;
    }

    private void verificarConflito(
            Long idPrestador,
            LocalDate data,
            Long idFolgaAtual
    ) {
        boolean folgaExistente = folgaRepository
                .existsByIdPrestadorAndDataFolga(
                        idPrestador,
                        data
                );

        if (folgaExistente) {
            // Na edição, permite manter a própria data.
            if (idFolgaAtual == null) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Já existe uma folga nesta data"
                );
            }

            Folga atual = folgaRepository.findById(idFolgaAtual)
                    .orElseThrow();

            if (!atual.getDataFolga().equals(data)) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Já existe outra folga nesta data"
                );
            }
        }

        Integer quantidade = jdbcTemplate.queryForObject(
                """
                SELECT COUNT(*)
                FROM agendamento
                WHERE id_prestador = ?
                  AND CAST(data_hora_agendamento AS DATE) = ?
                  AND status_agendamento = 'AGENDADO'
                """,
                Integer.class,
                idPrestador,
                Date.valueOf(data)
        );

        if (quantidade != null && quantidade > 0) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Existe um agendamento confirmado nesta data"
            );
        }
    }

    @Transactional
    public Folga cadastrar(
            Long idPrestador,
            Folga dados
    ) {
        if (dados.getDataFolga() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Informe a data da folga"
            );
        }

        verificarConflito(
                idPrestador,
                dados.getDataFolga(),
                null
        );

        Folga folga = new Folga();
        folga.setIdPrestador(idPrestador);
        folga.setDataFolga(dados.getDataFolga());
        folga.setMotivo(dados.getMotivo());

        return folgaRepository.save(folga);
    }

    @Transactional
    public Folga editar(
            Long idFolga,
            Long idPrestador,
            Folga dados
    ) {
        Folga folga = buscarFolgaDoPrestador(
                idFolga,
                idPrestador
        );

        if (dados.getDataFolga() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Informe a data da folga"
            );
        }

        verificarConflito(
                idPrestador,
                dados.getDataFolga(),
                idFolga
        );

        folga.setDataFolga(dados.getDataFolga());
        folga.setMotivo(dados.getMotivo());

        return folgaRepository.save(folga);
    }

    @Transactional
    public void excluir(
            Long idFolga,
            Long idPrestador
    ) {
        Folga folga = buscarFolgaDoPrestador(
                idFolga,
                idPrestador
        );

        folgaRepository.delete(folga);
    }
}
