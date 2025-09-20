package model;

public class Vacina {

    private long id;
    private String nome;
    private String fabricante;
    private int periodoValidadeEmMeses;

    public Vacina() {
    }

    public Vacina(String nome, String fabricante, int periodoValidadeEmMeses) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.periodoValidadeEmMeses = periodoValidadeEmMeses;
    }

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

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getPeriodoValidadeEmMeses() {
        return periodoValidadeEmMeses;
    }

    public void setPeriodoValidadeEmMeses(int periodoValidadeEmMeses) {
        this.periodoValidadeEmMeses = periodoValidadeEmMeses;
    }

    @Override
    public String toString() {
        return "Vacina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", fabricante='" + fabricante + '\'' +
                '}';
    }
}