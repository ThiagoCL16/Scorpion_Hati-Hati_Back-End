package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.repository.ContratanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ContratanteService {
    @Autowired
    ContratanteRepository contratanteRepository;

    // UsuarioRepository usuarioRepository;

    public Contratante save(Contratante contratante){
        Contratante c = contratanteRepository.save(contratante);
        return c;
    }

    public Optional<Contratante> findById(Long id){
        return contratanteRepository.findById(id);
    }

    public Contratante update(Long id, Map<String, Object> campos){
        Optional<Contratante> optionalContratante = contratanteRepository.findById(id);
        Contratante contratante;
        if(optionalContratante.isPresent())
            contratante = optionalContratante.get();
        else
            return null;

        campos.forEach((nomeCampo, valorCampo) -> {
            if(nomeCampo.equals("cidade"))
                contratante.setCidade((String) valorCampo);
            else if (nomeCampo.equals("avaliacoes"))
                contratante.setAvaliacoes((Double) valorCampo);
            else if (nomeCampo.equals("nome"))
                contratante.setNome((String) valorCampo);
            else if (nomeCampo.equals("estado"))
                contratante.setEstado((String) valorCampo);
            else if (nomeCampo.equals("cep"))
                contratante.setCep((String) valorCampo);
        });

        return contratanteRepository.save(contratante);
    }

    public Contratante deleteById(Long id){
        Optional<Contratante> optionalContratante = contratanteRepository.findById(id);
        Contratante contratante;

        if(!optionalContratante.isPresent())
            return null;
        else
            contratante = optionalContratante.get();
        contratanteRepository.delete(contratante);
        return contratante;
    }
}
