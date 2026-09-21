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
import java.util.Optional;

@Service
public class PrestadorService {

    @Autowired
    PrestadorRepository prestadorRepository;

}
