package model;

import java.time.LocalDateTime;

/**
 * @author Fabiana Souza
 */
public class ConsumoConteudo {

    private long id;
    private LocalDateTime dataConsumo;
    private Tutor tutor;
    private ConteudoEducativo conteudo;

    public ConsumoConteudo() {
    }

    public ConsumoConteudo(Tutor tutor, ConteudoEducativo conteudo) {
        this.tutor = tutor;
        this.conteudo = conteudo;
        this.dataConsumo = LocalDateTime.now();
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(LocalDateTime dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public ConteudoEducativo getConteudo() {
        return conteudo;
    }

    public void setConteudo(ConteudoEducativo conteudo) {
        this.conteudo = conteudo;
    }
}