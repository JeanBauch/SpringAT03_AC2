package com.example.atividade3jeanbauch180375.service;

import java.util.List;

import com.example.atividade3jeanbauch180375.entity.Editora;
import com.example.atividade3jeanbauch180375.repository.EditoraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

    public boolean delete(Editora e)
    {
        if(e.getLivros().size()>0)
            return false;
        rp.delete(e);
        return true;
    }
}