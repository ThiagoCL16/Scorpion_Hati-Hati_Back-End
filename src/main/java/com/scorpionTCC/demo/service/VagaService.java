package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.entity.Vaga;
import com.scorpionTCC.demo.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {
    @Autowired
    VagaRepository vagaRepository;

    public List<Vaga> GetVagas(){
        List<Vaga> vagas = vagaRepository.findAll();
        return vagas;
    }

    public Vaga GetVagaById(Long id){
        Optional<Vaga> vaga = vagaRepository.findById(id);

        if(vaga.isPresent())
            return vaga.get();
        else
            return null;
    }
}
