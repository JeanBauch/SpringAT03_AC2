package com.example.atividade3jeanbauch180375.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;


@Entity
public class Livro implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String edicao;
    private int qtdPag;
    private String categoria;

    @ManyToMany
    @JoinTable(
        name="AutorLivro",
        uniqueConstraints = @UniqueConstraint(columnNames = {"LivroID","AutorID"}),
        joinColumns = @JoinColumn(
            name="LivroID",
            referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name="AutorID",
            referencedColumnName = "id"
            
        )
    )
    private List<Autor> autores;

    @ManyToOne
    @JoinColumn(
        name="EditoraLivro"
    )
    private Editora editora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public int getQtdPag() {
        return qtdPag;
    }

    public void setQtdPag(int qtdPag) {
        this.qtdPag = qtdPag;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Livro [categoria=" + categoria + ", edicao=" + edicao + ", id=" + id + ", nome=" + nome + ", qtdPag="
                + qtdPag + "]";
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }
}