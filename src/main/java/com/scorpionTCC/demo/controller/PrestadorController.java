package com.scorpionTCC.demo.controller;

import com.scorpionTCC.demo.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {
    @Autowired
    private PrestadorRepository prestadorRepository;
}
