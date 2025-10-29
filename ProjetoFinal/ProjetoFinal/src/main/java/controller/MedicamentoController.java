package controller;

import java.util.List;

import DAO.MedicamentoDAO;
import model.Medicamento;

public class MedicamentoController {

    private MedicamentoDAO medicamentoDAO;

    public MedicamentoController() {
        // 2. Instancia o DAO
        this.medicamentoDAO = new MedicamentoDAO();
    }

    // CREATE (Seu método era 'adicionarMedicamento')
    public void adicionarMedicamento(Medicamento medicamento) {
        // 3. Delega o trabalho para o DAO
        medicamentoDAO.criarMedicamento(medicamento);
    }

    // READ
    public void listarMedicamentos() {
        // 4. Busca do banco através do DAO
        List<Medicamento> medicamentos = medicamentoDAO.listarMedicamentos();
        
        System.out.println("\n=== LISTA DE MEDICAMENTOS (do Banco de Dados) ===");
        if (medicamentos.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado.");
        } else {
            // O seu 'toString()' no modelo já formata bem
            for (Medicamento m : medicamentos) {
                System.out.println(m);
            }
        }
    }

    // UPDATE
    public void atualizarMedicamento(Medicamento novoMedicamento) {
        // A View deve garantir que o objeto 'novoMedicamento' tenha o ID correto
        medicamentoDAO.atualizarMedicamento(novoMedicamento);
    }

    // DELETE
    public void removerMedicamento(int id) {
        medicamentoDAO.excluirMedicamento(id);
    }
    
    // Helper (para a View)
    public Medicamento buscarMedicamentoPorId(int id) {
        return medicamentoDAO.buscarMedicamentoPorId(id);
    }
}