
package com.scorpionTCC.demo.service;


import com.scorpionTCC.demo.repository.AgendamentoRepository;
import com.scorpionTCC.demo.entity.Agendamento;
import com.scorpionTCC.demo.enums.StatusCandidatura;
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

    private final AgendamentoRepository agendamentoRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final VagaRepository vagaRepository;
    private final PrestadorRepository prestadorRepository;


    public CandidaturaService(
            CandidaturaRepository candidaturaRepository,
            VagaRepository vagaRepository,
            PrestadorRepository prestadorRepository,
            AgendamentoRepository agendamentoRepository
    ) {
        this.candidaturaRepository = candidaturaRepository;
        this.vagaRepository = vagaRepository;
        this.prestadorRepository = prestadorRepository;
        this.agendamentoRepository = agendamentoRepository;
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

    @Transactional
    public Candidatura aceitarCandidatura(
            Long idCandidatura,
            Long idContratante
    ) {
        Candidatura candidatura = candidaturaRepository
                .findById(idCandidatura)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Candidatura não encontrada"
                ));

        if (candidatura.getStatus() != StatusCandidatura.PENDENTE) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Esta candidatura não está pendente"
            );
        }

        VagaPublica vaga = candidatura.getVaga();

        if (vaga.getIdContratante() == null
                || !vaga.getIdContratante().getId().equals(idContratante)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Este contratante não é responsável pela vaga"
            );
        }

        if (agendamentoRepository.existsByIdVaga_Id(vaga.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Esta vaga já possui um agendamento"
            );
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setIdVaga(vaga);
        agendamento.setIdContratante(vaga.getIdContratante());
        agendamento.setIdPrestadorDeServico(candidatura.getPrestador());
        agendamento.setDataHoraAgendamento(vaga.getDataHoraVaga());
        agendamento.setLocalServico(vaga.getLocalizacao());
        agendamento.setStatusAgendamento("AGENDADO");

        agendamentoRepository.save(agendamento);

        candidatura.setStatus(StatusCandidatura.ACEITA);
        return candidaturaRepository.save(candidatura);
    }



}
