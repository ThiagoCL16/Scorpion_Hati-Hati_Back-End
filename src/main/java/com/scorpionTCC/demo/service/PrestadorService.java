package com.scorpionTCC.demo.service;

import com.scorpionTCC.demo.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestadorService {

    @Autowired
    PrestadorRepository prestadorRepository;
}
