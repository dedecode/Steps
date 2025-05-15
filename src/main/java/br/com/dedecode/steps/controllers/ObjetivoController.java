package br.com.dedecode.steps.controllers;

import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import br.com.dedecode.steps.models.Objetivo;
import br.com.dedecode.steps.repositories.ObjetivoRepository;
import jakarta.validation.Valid;



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
            Map.of("objetivos", objetivoRepository.findAll(Sort.by("deadline")))
        );
    }
    
    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("objetivo/form", Map.of("objetivo", new Objetivo()));
    }
  
    @PostMapping("/create")
    public String create(@Valid Objetivo objetivo, BindingResult result) {
        if(result.hasErrors()){
            return "objetivo/form";
        }
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
    public String edit(@Valid Objetivo objetivo, BindingResult result){
        if(result.hasErrors()){
            return "objetivo/form";
        }
        objetivoRepository.save(objetivo);
        
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        var objetivo = objetivoRepository.findById(id);
        if (objetivo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ModelAndView("objetivo/delete", Map.of("objetivo", objetivo.get()));
    }

    @PostMapping("/delete/{id}")
    public String delete(Objetivo objetivo){
        objetivoRepository.delete(objetivo);;
        return "redirect:/";
    }

    @PostMapping("/finish/{id}")
    public String finish(@PathVariable Long id) {
        var optionalObjetivo = objetivoRepository.findById(id);
        if (optionalObjetivo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var objetivo = optionalObjetivo.get();
        objetivo.markAsFinished();
        objetivoRepository.save(objetivo);
        return "redirect:/";
    }
    

}
    

    
    


