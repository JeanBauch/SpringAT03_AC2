package com.example.atividade3jeanbauch180375.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.atividade3jeanbauch180375.entity.Livro;
import com.example.atividade3jeanbauch180375.service.AutorService;
import com.example.atividade3jeanbauch180375.service.EditoraService;
import com.example.atividade3jeanbauch180375.service.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LivroController {
    
    @Autowired
    LivroService livroService;

    @Autowired
    EditoraController editoraController;

    @Autowired
    AutorService autorService;

    @GetMapping("/livro")
    public ModelAndView getAll()
    {
        ModelAndView mv = new ModelAndView("livroView");
        List<Livro> livros = livroService.getAll();
        mv.addObject("livro", new Livro());
        mv.addObject("autores", autorService.getAll());
        mv.addObject("editoras", editoraController.getAll());
        mv.addObject("livros", livros);
        return mv;
    }

    @GetMapping("/livro/{id}")
    public ModelAndView getByID(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("livroDetails");
        Livro livro = livroService.getByID(id);
        mv.addObject("livro", livro);
        return mv;
    }

    @PostMapping("/livro")
    public String registerLivro(@Valid @ModelAttribute Livro livro, RedirectAttributes redirectAttributes)
    {
        boolean resp = livroService.postarLivro(livro);
        if(!resp)
            redirectAttributes.addFlashAttribute("erro","A quantidade de pág. não pode ser negativa, e deve ser maior que 0!");
        return "redirect:/livro";
    }
}