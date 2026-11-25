
package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Agendamento;
import com.scorpionTCC.demo.repository.AgendamentoRepository;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoController(
            AgendamentoRepository agendamentoRepository
    ) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @GetMapping("/prestador/{idPrestador}")
    public List<AgendamentoResposta> listarPorPrestador(
            @PathVariable Long idPrestador
    ) {
        return agendamentoRepository
                .findByIdPrestadorDeServico_Id(idPrestador)
                .stream()
                .map(this::converter)
                .toList();
    }

    private AgendamentoResposta converter(Agendamento agendamento) {

        Long idVaga = agendamento.getIdVaga() != null
                ? agendamento.getIdVaga().getId()
                : null;

        String descricao = agendamento.getIdVaga() != null
                ? agendamento.getIdVaga().getDescricaoServicoVaga()
                : null;

        BigDecimal valor = agendamento.getIdVaga() != null
                ? agendamento.getIdVaga().getValor()
                : null;

        String duracao = agendamento.getIdVaga() != null
                ? agendamento.getIdVaga().getDuracao()
                : null;

        return new AgendamentoResposta(
                agendamento.getId(),
                agendamento.getDataHoraAgendamento(),
                agendamento.getLocalServico(),
                agendamento.getStatusAgendamento(),
                idVaga,
                descricao,
                valor,
                duracao
        );
    }

    public record AgendamentoResposta(
            Long id,
            LocalDateTime dataHora,
            String localServico,
            String status,
            Long idVaga,
            String descricao,
            BigDecimal valor,
            String duracao
    ) {}
}
