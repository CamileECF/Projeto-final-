package model;

import java.time.LocalDate;

public class AplicacaoVacina {

    private long id;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
    private String observacoes;

    private Vacina vacina;

    private Pet pet;

    // Construtor padrão
    public AplicacaoVacina() {
    }

    public AplicacaoVacina(LocalDate dataAplicacao, Vacina vacina, Pet pet) {
        this.dataAplicacao = dataAplicacao;
        this.vacina = vacina;
        this.pet = pet;

        if (vacina != null && vacina.getPeriodoValidadeEmMeses() > 0) {
            this.dataProximaDose = dataAplicacao.plusMonths(vacina.getPeriodoValidadeEmMeses());
        }
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public LocalDate getDataProximaDose() {
        return dataProximaDose;
    }

    public void setDataProximaDose(LocalDate dataProximaDose) {
        this.dataProximaDose = dataProximaDose;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {

        String nomeVacina = (vacina != null) ? vacina.getNome() : "N/A";
        String nomePet = (pet != null) ? pet.getNome() : "N/A";
        return "AplicacaoVacina{" +
                "id=" + id +
                ", dataAplicacao=" + dataAplicacao +
                ", vacina=" + nomeVacina +
                ", pet=" + nomePet +
                '}';
    }
}