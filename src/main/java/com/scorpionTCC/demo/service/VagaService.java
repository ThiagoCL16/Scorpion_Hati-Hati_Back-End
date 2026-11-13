package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.VagaPublica;
import com.scorpionTCC.demo.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {
    @Autowired
    VagaRepository vagaRepository;

    @Autowired
    ContratanteService contratanteService;

    public List<VagaPublica> GetVagas(){
        List<VagaPublica> vagas = vagaRepository.findAll();
        return vagas;
    }

    public VagaPublica GetVagaById(Long id){
        Optional<VagaPublica> vaga = vagaRepository.findById(id);

        if(vaga.isPresent())
            return vaga.get();
        else
            return null;
    }

    public VagaPublica save(VagaPublica vaga, Long idContratante)
    {
        Optional<Contratante> c =  contratanteService.findById(idContratante);
        if(c.isPresent())
            vaga.setIdContratante(c.get());
        //else
        //    return null;
        VagaPublica v = vagaRepository.save(vaga);
        return v;
    }

    public boolean deleteById(Long id) {
        if (!vagaRepository.existsById(id))
            return false;

        vagaRepository.deleteById(id);
        return true;
    }
}
