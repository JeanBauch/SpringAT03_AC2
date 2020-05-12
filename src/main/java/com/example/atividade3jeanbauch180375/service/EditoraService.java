package com.example.atividade3jeanbauch180375.service;

import java.util.List;

import com.example.atividade3jeanbauch180375.entity.Editora;
import com.example.atividade3jeanbauch180375.repository.EditoraRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class EditoraService {
    
    @Autowired
    EditoraRepository rp;

    public List<Editora> getAll()
    {
        return rp.findAll();
    }

    public Editora getByID (int id)
    {
        return rp.findById(id).get();
    }

    public void postarEditora(Editora e)
    {
        rp.save(e);
    }
}