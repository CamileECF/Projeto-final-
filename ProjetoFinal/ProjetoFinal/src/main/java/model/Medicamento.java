package model;

public class Medicamento {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;

    public Medicamento(int id, String nome, String descricao, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    @Override
    public String toString() {
        // Formata o preço para sempre ter duas casas decimais
        String precoFormatado = String.format("R$ %.2f", this.preco);
        
        // Retorna uma string limpa e formatada
        return String.format("ID: %-3d | Nome: %-20s | Preço: %-10s | Estoque: %-4d | Descrição: %s",
                this.id,
                this.nome,
                precoFormatado,
                this.quantidadeEstoque,
                this.descricao
        );
    }
}