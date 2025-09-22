package model;

import java.time.LocalDate;

/**
 * @author Fabiana Souza
 */
public class ConteudoEducativo {

    private long id;
    private String titulo;
    private String corpo; 
    private String autor;
    private LocalDate dataPublicacao;

    public ConteudoEducativo() {
    }

    public ConteudoEducativo(String titulo, String corpo, String autor) {
        this.titulo = titulo;
        this.corpo = corpo;
        this.autor = autor;
        this.dataPublicacao = LocalDate.now();
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}