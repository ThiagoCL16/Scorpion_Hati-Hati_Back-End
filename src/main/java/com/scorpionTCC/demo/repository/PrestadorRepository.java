package com.scorpionTCC.demo.repository;

import com.scorpionTCC.demo.entity.PrestadorServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestadorRepository extends JpaRepository<PrestadorServico, Long> {
}
