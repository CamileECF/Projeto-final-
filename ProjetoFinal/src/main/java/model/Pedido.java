package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emanoel Alexandre
 */
public class Pedido {

    private long id;
    private LocalDateTime dataPedido;
    private double valorTotal;
    private String status;
    
    private Tutor tutor;
    
    private List<ItemPedido> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
        this.dataPedido = LocalDateTime.now(); 
        this.status = "Pendente";
        this.valorTotal = 0.0;
    }
    // Método para adicionar um item e já recalcular o total
    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        item.setPedido(this); 
        recalcularValorTotal();
    }
    
    public void recalcularValorTotal() {
        this.valorTotal = this.itens.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}