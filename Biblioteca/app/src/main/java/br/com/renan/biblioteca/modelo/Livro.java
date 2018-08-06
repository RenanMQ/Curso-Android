package br.com.renan.biblioteca.modelo;

import java.io.Serializable;

public class Livro implements Serializable{
    private int id;
    private String tituloLivro;
    private String nomeAutor;
    private String editora;
    private int Ano;
    private int isbn;

    public Livro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int ano) {
        Ano = ano;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return tituloLivro.toString();
    }
}
