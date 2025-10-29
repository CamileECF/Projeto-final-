package model;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDateTime dataPedido;
    private List<Medicamento> itens;
    private double total;

    public Pedido(int id, List<Medicamento> itens) {
        this.id = id;
        this.itens = itens;
        this.dataPedido = LocalDateTime.now();
        calcularTotal();
    }

    private void calcularTotal() {
        total = 0;
        for (Medicamento m : itens) {
            total += m.getPreco();
        }
    }

    // Getters
    public int getId() { return id; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public List<Medicamento> getItens() { return itens; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                ", itens=" + itens +
                '}';
    }
}