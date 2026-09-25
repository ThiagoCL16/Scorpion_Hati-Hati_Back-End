package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.repository.ContratanteRepository;
import com.scorpionTCC.demo.repository.PrestadorRepository;
import com.scorpionTCC.demo.repository.VagaRepository;
import com.scorpionTCC.demo.entity.Vaga;
import com.scorpionTCC.demo.entity.PrestadorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PrestadorService {

    @Autowired
    PrestadorRepository prestadorRepository;

    public PrestadorServico save(PrestadorServico prestador){
        PrestadorServico p = prestadorRepository.save(prestador);
        return p;
    }

    public Optional<PrestadorServico> findById(Long id){
        return prestadorRepository.findById(id);
    }

    public List<PrestadorServico> getPrestadores()
    {
        List<PrestadorServico> prestadores = prestadorRepository.findAll();
        return prestadores;
    }

    public PrestadorServico update(Long id, Map<String, Object> campos){
        Optional<PrestadorServico> optionalPrestador = prestadorRepository.findById(id);
        PrestadorServico prestador;
        if(optionalPrestador.isPresent())
            prestador = optionalPrestador.get();
        else
            return null;

        campos.forEach((nomeCampo, valorCampo) -> {
            if (nomeCampo.equals("cidade"))
                prestador.setCidade((String) valorCampo);
            else if (nomeCampo.equals("avaliacoes")) {
                // Se o valor for qualquer tipo de número, converte com segurança para Double
                if (valorCampo instanceof Number) {
                    prestador.setAvaliacoes(((Number) valorCampo).doubleValue());
                }
            } else if (nomeCampo.equals("nome"))
                prestador.setNome((String) valorCampo);
            else if (nomeCampo.equals("estado"))
                prestador.setEstado((String) valorCampo);
            else if (nomeCampo.equals("cep"))
                prestador.setCep((String) valorCampo);
            else if (nomeCampo.equals("anexos"))
                prestador.setAnexos((String) valorCampo);
            else if (nomeCampo.equals("descricao"))
                prestador.setDescricao((String) valorCampo);
            else if (nomeCampo.equals("tipoAssinatura"))
                prestador.setTipoAssinatura((Boolean) valorCampo);

        });

        return prestadorRepository.save(prestador);
    }

    public PrestadorServico deleteById(Long id){
        Optional<PrestadorServico> optionalPrestador = prestadorRepository.findById(id);
        PrestadorServico prestador;

        if(!optionalPrestador.isPresent())
            return null;
        else
            prestador = optionalPrestador.get();
        prestadorRepository.delete(prestador);
        return prestador;
    }
}
