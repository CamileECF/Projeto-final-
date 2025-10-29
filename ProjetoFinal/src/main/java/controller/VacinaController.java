package controller;

import java.util.List; // 1. Importar o DAO

import DAO.VacinaDAO;
import model.Vacina; // 2. Usar a interface List

public class VacinaController {

    // MUDANÇA: O Controller não tem mais uma 'ArrayList'.
    // Ele tem uma instância do DAO.
    private VacinaDAO vacinaDAO;

    public VacinaController() {
        // O Controller agora é responsável por criar seu próprio DAO.
        this.vacinaDAO = new VacinaDAO();
    }

    // CREATE
    public void cadastrarVacina(Vacina vacina) {
        // Delega o trabalho para o DAO, que salvará no banco.
        vacinaDAO.criarVacina(vacina); 
        // A mensagem de sucesso já é impressa pelo DAO.
    }

    // READ
    public void listarVacinas() {
        // Busca a lista do BANCO DE DADOS através do DAO
        List<Vacina> listaVacinas = vacinaDAO.listarVacinas(); 
        
        System.out.println("\n=== LISTA DE VACINAS (do Banco de Dados) ===");
        if (listaVacinas.isEmpty()) {
            System.out.println("Nenhuma vacina cadastrada.");
        } else {
            // Itera sobre a lista vinda do banco
            for (Vacina v : listaVacinas) {
                // Imprime os dados formatados, incluindo o ID
                System.out.println("ID: " + v.getId() + " - " + v.getNome() + 
                                   " | Fabricante: " + v.getFabricante() +
                                   " | Validade: " + v.getPeriodoValidadeEmMeses() + " meses");
            }
        }
    }

    // UPDATE
    // MUDANÇA: Recebe o objeto Vacina completo, que já deve conter o ID.
    public void atualizarVacina(Vacina vacinaAtualizada) {
        // A View é responsável por colocar o ID no objeto antes de passá-lo para cá
        vacinaDAO.atualizarVacina(vacinaAtualizada);
    }

    // DELETE
    // MUDANÇA: Recebe o ID (long), não o índice (int)
    public void removerVacina(long id) {
        vacinaDAO.excluirVacina(id);
    }
}