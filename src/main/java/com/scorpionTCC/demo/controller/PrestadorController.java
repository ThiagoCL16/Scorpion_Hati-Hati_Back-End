package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.entity.Contratante;
import com.scorpionTCC.demo.entity.Vaga;
import com.scorpionTCC.demo.repository.PrestadorRepository;
import com.scorpionTCC.demo.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {
    @Autowired
    private PrestadorService prestadorService;

}
