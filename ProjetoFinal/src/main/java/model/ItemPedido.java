package model;

/**
 * @author Emanoel Alexandre
 */
public class ItemPedido {

    private long id;
    private int quantidade;
    private double precoUnitario; 
    private double subtotal;
    
    private Pedido pedido;
    private Medicamento medicamento;

    public ItemPedido() {
    }

    public ItemPedido(int quantidade, Medicamento medicamento) {
        this.quantidade = quantidade;
        this.medicamento = medicamento;
        if (medicamento != null) {
            this.precoUnitario = medicamento.getPreco();
        }
        calcularSubtotal();
    }
    
    public void calcularSubtotal() {
        this.subtotal = this.quantidade * this.precoUnitario;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calcularSubtotal(); // Recalcula se a quantidade mudar
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        calcularSubtotal(); // Recalcula se o preço mudar
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}