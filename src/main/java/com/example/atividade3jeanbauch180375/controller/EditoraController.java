package com.example.atividade3jeanbauch180375.controller;

import java.util.List;

import com.example.atividade3jeanbauch180375.entity.Editora;
import com.example.atividade3jeanbauch180375.service.EditoraService;
import com.example.atividade3jeanbauch180375.service.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EditoraController {
    
    @Autowired
    EditoraService editoraService;

    @Autowired
    LivroService livroService;

    @GetMapping("/editora")
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("editoraView");
        List<Editora> editoras = editoraService.getAll();
        mv.addObject("editoras", editoras);
        return mv;
    }

    @GetMapping("/editora/{id}")
    public ModelAndView getByID(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("editoraDetails");
        Editora editora = editoraService.getByID(id);
       
        mv.addObject("editoras", editora);
        return mv;

    }

    @PostMapping("/editora")
    public String resgisterLivro(@ModelAttribute Editora editora) {
        editoraService.postarEditora(editora);
        return "redirect:/editora";
    }
    
}