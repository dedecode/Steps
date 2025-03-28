package br.com.dedecode.steps.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dedecode.steps.repositories.ObjetivoRepository;

@Controller
public class ObjetivoController {
    
    private final ObjetivoRepository objetivoRepository;

    public ObjetivoController(ObjetivoRepository objetivoRepository){
        this.objetivoRepository = objetivoRepository;
    }

    @GetMapping("/")
    public ModelAndView list(){
        return new ModelAndView(
            "objetivo/list",
            Map.of("objetivos", objetivoRepository.findAll())
        );

    }
}
