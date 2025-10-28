package controller;

import java.util.ArrayList;

import model.Vacina;

public class VacinaController {

    private ArrayList<Vacina> listaVacinas = new ArrayList<>();

    // CREATE
    public void cadastrarVacina(Vacina vacina) {
        listaVacinas.add(vacina);
        System.out.println("\n✅ Vacina cadastrada com sucesso!");
    }

    // READ
    public void listarVacinas() {
        System.out.println("\n=== LISTA DE VACINAS ===");
        if (listaVacinas.isEmpty()) {
            System.out.println("Nenhuma vacina cadastrada.");
        } else {
            for (int i = 0; i < listaVacinas.size(); i++) {
                Vacina v = listaVacinas.get(i);
                System.out.println((i + 1) + " - " + v.getNome() + " | Fabricante: " + v.getFabricante()
                        + " | Validade: " + v.getPeriodoValidadeEmMeses() + " meses");
            }
        }
    }

    // UPDATE
    public void atualizarVacina(int index, Vacina vacinaAtualizada) {
        if (index >= 0 && index < listaVacinas.size()) {
            listaVacinas.set(index, vacinaAtualizada);
            System.out.println("\n✏️ Vacina atualizada com sucesso!");
        } else {
            System.out.println("\n❌ Vacina não encontrada.");
        }
    }

    // DELETE
    public void removerVacina(int index) {
        if (index >= 0 && index < listaVacinas.size()) {
            listaVacinas.remove(index);
            System.out.println("\n🗑️ Vacina removida com sucesso!");
        } else {
            System.out.println("\n❌ Vacina não encontrada.");
        }
    }

    // Getter da lista (caso precise acessar da View)
    public ArrayList<Vacina> getListaVacinas() {
        return listaVacinas;
    }
}
