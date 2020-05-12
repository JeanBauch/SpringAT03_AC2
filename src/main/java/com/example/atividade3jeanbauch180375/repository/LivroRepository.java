package com.example.atividade3jeanbauch180375.repository;

import com.example.atividade3jeanbauch180375.entity.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository <Livro,Integer>{
    
}