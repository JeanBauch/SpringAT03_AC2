package com.example.atividade3jeanbauch180375.service;

import java.util.List;

import com.example.atividade3jeanbauch180375.entity.Livro;
import com.example.atividade3jeanbauch180375.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class LivroService {

    @Autowired
    LivroRepository rp;

    public List<Livro> getAll()
    {
        return rp.findAll();
    }

    public Livro getByID (int id)
    {
        return rp.findById(id).get();
    }

    public void postarLivro(Livro l)
    {
        rp.save(l);
    }
    
}