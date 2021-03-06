package com.example.atividade3jeanbauch180375.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.atividade3jeanbauch180375.entity.Autor;
import com.example.atividade3jeanbauch180375.entity.Livro;
import com.example.atividade3jeanbauch180375.service.AutorService;
import com.example.atividade3jeanbauch180375.service.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/autor/{id}")
    public ModelAndView getByID(@PathVariable(name = "id") Integer id) 
    {
        ModelAndView mv = new ModelAndView("autorDetails");
        Autor autor = serviceAutor.getByID(id);
        mv.addObject("autor", autor);
        List<Livro> nAssociado = livroService.getAll();
        
        nAssociado.removeAll(autor.getLivros());
        mv.addObject("livros", nAssociado);
        return mv;
    }

    @GetMapping("/autor/edit/{id}")
    public ModelAndView getEditByID(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("editAutor");
        Autor autor = serviceAutor.getByID(id);
        mv.addObject("autor", autor);
        return mv;
    }

    @GetMapping("/autor/delete/{id}")
    public String deleteEditora(@PathVariable int id) {
        Autor a = serviceAutor.getByID(id);
        serviceAutor.delete(a);
        return "redirect:/autor";
    }

    @GetMapping("/autor/deleteLivro/{id}/{idl}")
    public String deleteALivro(@PathVariable int id, @PathVariable int idl) {
        Autor a = serviceAutor.getByID(id);
        Livro l = livroService.getByID(idl);

        a.getLivros().remove(l);
        serviceAutor.postarAutor(a);
        return "redirect:/autor/edit/" + id;
    }

    @PostMapping("/autor/associar")
    public String associarAutor(@ModelAttribute Livro livro, @RequestParam Integer idAutor) {
        Autor autor = serviceAutor.getByID(idAutor);
        livro = livroService.getByID(livro.getId());

        autor.getLivros().add(livro);
        serviceAutor.postarAutor(autor);
        return "redirect:/autor/"+idAutor;
    }

    @PostMapping("/autor")
    public String registerAutor(@Valid @ModelAttribute Autor autor, RedirectAttributes redirectAttributes)
    {
        boolean resp = serviceAutor.postarAutor(autor);
        if(!resp)
            redirectAttributes.addFlashAttribute("erro","A idade não pode ser negativa, e deve ser maior que 0!");
        
        return "redirect:/autor";
    }

    @PostMapping("/autor/edit/{id}")
    public String editarAutor(@Valid @ModelAttribute Autor autor, @PathVariable int id, RedirectAttributes redirectAttributes)
    {
        Autor autorr = serviceAutor.getByID(id);
        autor.setLivros(autorr.getLivros());
        boolean resp = serviceAutor.postarAutor(autor);
        if(!resp)   
            redirectAttributes.addFlashAttribute("erro", "A idade não pode ser negativa e deve ser maior que 0!");
        else
            redirectAttributes.addFlashAttribute("sucesso", "Os dados foram alterados com sucesso!");
    
        return "redirect:/autor/"+id;
    }

}