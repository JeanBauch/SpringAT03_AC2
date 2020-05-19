package com.example.atividade3jeanbauch180375.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.atividade3jeanbauch180375.entity.Autor;
import com.example.atividade3jeanbauch180375.service.AutorService;
import com.example.atividade3jeanbauch180375.service.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AutorController {
    
    @Autowired
    AutorService serviceAutor;

    @Autowired
    LivroService livroService;

    @GetMapping("/autor")
    public ModelAndView getAll()
    {
        ModelAndView mv = new ModelAndView("autorView");
        List<Autor> autores = serviceAutor.getAll();
        mv.addObject("autores", autores);
        return mv;
    }

    @PostMapping("/autor")
    public String registerAutor(@Valid @ModelAttribute Autor autor, RedirectAttributes redirectAttributes)
    {
        boolean resp = serviceAutor.postarAutor(autor);
        if(!resp)
            redirectAttributes.addFlashAttribute("erro","A idade não pode ser negativa, e deve ser maior que 0!");
        
        return "redirect:/autor";
    }
}