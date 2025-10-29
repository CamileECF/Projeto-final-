package controller;

import java.util.List;

import DAO.TutorDAO;
import model.Tutor;

public class TutorController {

    private TutorDAO tutorDAO;

    public TutorController() {
        this.tutorDAO = new TutorDAO();
    }

    // CREATE
    public void cadastrarTutor(Tutor tutor) {
        tutorDAO.criarTutor(tutor);
    }

    // READ - Buscar por ID
    public Tutor buscarTutorPorId(int idTutor) {
        return tutorDAO.buscarTutorPorId(idTutor);
    }

    // READ - Listar todos
    public void listarTutores() {
        List<Tutor> listaTutores = tutorDAO.listarTutores();
        
        System.out.println("\n=== LISTA DE TUTORES (do Banco de Dados) ===");
        if (listaTutores.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
        } else {
            for (Tutor t : listaTutores) {
                System.out.println("ID: " + t.getIdUsuario() + " - " + t.getNome() + 
                                   " | CPF: " + t.getCpf() +
                                   " | Email: " + t.getEmail() +
                                   " | Telefone: " + t.getTelefone());
            }
        }
    }

    // UPDATE
    public void atualizarTutor(Tutor tutorAtualizado) {
        tutorDAO.atualizarTutor(tutorAtualizado);
    }

    // DELETE
    public void removerTutor(int idTutor) {
        tutorDAO.excluirTutor(idTutor);
    }
}