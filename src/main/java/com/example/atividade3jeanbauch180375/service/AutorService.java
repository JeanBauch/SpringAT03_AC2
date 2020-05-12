package com.example.atividade3jeanbauch180375.service;

import java.util.List;

import com.example.atividade3jeanbauch180375.entity.Autor;
import com.example.atividade3jeanbauch180375.repository.AutorRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class AutorService {
    
    @Autowired
    AutorRepository rp;

    public List<Autor> getAll()
    {
        return rp.findAll();
    }

    public Autor getByID(int id)
    {
        return rp.findById(id).get();
    }

    public void postarAutor(Autor a)
    {
        rp.save(a);
    }
}