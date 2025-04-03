package br.com.dedecode.steps.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import br.com.dedecode.steps.repositories.ObjetivoRepository;
import br.com.dedecode.steps.models.Objetivo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;



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
    
    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("objetivo/form", Map.of("objetivo", new Objetivo()));
    }
  
    @PostMapping("/create")
    public String create(Objetivo objetivo) {
        objetivoRepository.save(objetivo);
        
        return "redirect:/";
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        var objetivo = objetivoRepository.findById(id);
        if (objetivo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ModelAndView("objetivo/form", Map.of("objetivo", objetivo.get()));
    }
    
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Objetivo objetivo){
        objetivoRepository.save(objetivo);
        return "redirect:/";
    }
        
    }
    

    
    


