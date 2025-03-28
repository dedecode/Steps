package br.com.dedecode.steps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dedecode.steps.models.Objetivo;

public interface ObjetivoRepository extends JpaRepository< Objetivo , Long> {
    
}
