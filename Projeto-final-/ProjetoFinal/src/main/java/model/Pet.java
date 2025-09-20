package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
  informações básicas e o relacionamento com seu Tutor.
 * @author Erick Ferreira
 */
public class Pet {

    private long id;
    private String nome;
    private String especie; 
    private String raca;
    private LocalDate dataNascimento;
    private String sexo;

    // Relacionamento: Cada Pet pertence a um Tutor.
    private Tutor tutor;

    //Relacionamento: Um pet pode ter várias aplicações de vacinas.
    private List<AplicacaoVacina> aplicacoesVacinas;

    public Pet() {
        this.aplicacoesVacinas = new ArrayList<>();
    }
    public Pet(String nome, String especie, String raca, LocalDate dataNascimento, String sexo, Tutor tutor) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.tutor = tutor;
        this.aplicacoesVacinas = new ArrayList<>();
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<AplicacaoVacina> getAplicacoesVacinas() {
        return aplicacoesVacinas;
    }

    public void setAplicacoesVacinas(List<AplicacaoVacina> aplicacoesVacinas) {
        this.aplicacoesVacinas = aplicacoesVacinas;
    }
}