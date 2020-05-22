package com.example.atividade3jeanbauch180375.service;

import java.util.List;

import com.example.atividade3jeanbauch180375.entity.Autor;
import com.example.atividade3jeanbauch180375.repository.AutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

    public boolean postarAutor(Autor a)
    {
        if(a.getIdade()>0)
        {
            rp.save(a);
            return true;
        }
        return false;
    }

    public boolean delete(Autor a)
    {
        if(a.getLivros().size()>0)
            return false;
        rp.delete(a);
        return true;
    }
}