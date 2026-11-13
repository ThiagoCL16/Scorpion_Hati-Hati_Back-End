
package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.entity.Candidatura;
import com.scorpionTCC.demo.entity.PrestadorServico;
import com.scorpionTCC.demo.entity.VagaPublica;
import com.scorpionTCC.demo.repository.CandidaturaRepository;
import com.scorpionTCC.demo.repository.PrestadorRepository;
import com.scorpionTCC.demo.repository.VagaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final VagaRepository vagaRepository;
    private final PrestadorRepository prestadorRepository;

    public CandidaturaService(
            CandidaturaRepository candidaturaRepository,
            VagaRepository vagaRepository,
            PrestadorRepository prestadorRepository
    ) {
        this.candidaturaRepository = candidaturaRepository;
        this.vagaRepository = vagaRepository;
        this.prestadorRepository = prestadorRepository;
    }

    // Prestador se candidata a uma vaga pública
    @Transactional
    public Candidatura candidatar(Long idVaga, Long idPrestador) {

        VagaPublica vaga = vagaRepository.findById(idVaga)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vaga não encontrada"
                ));

        PrestadorServico prestador =
                prestadorRepository.findById(idPrestador)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Prestador não encontrado"
                        ));

        boolean jaCandidatou =
                candidaturaRepository.existsByVaga_IdAndPrestador_Id(
                        idVaga,
                        idPrestador
                );

        if (jaCandidatou) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Você já se candidatou a esta vaga"
            );
        }

        Candidatura candidatura = new Candidatura();

        candidatura.setVaga(vaga);
        candidatura.setPrestador(prestador);

        return candidaturaRepository.save(candidatura);
    }

    // Consultar candidaturas de uma vaga
    @Transactional(readOnly = true)
    public List<Candidatura> listarPorVaga(Long idVaga) {
        return candidaturaRepository.findByVaga_Id(idVaga);
    }

    // Consultar candidaturas de um prestador
    @Transactional(readOnly = true)
    public List<Candidatura> listarPorPrestador(Long idPrestador) {
        return candidaturaRepository.findByPrestador_Id(idPrestador);
    }
}
