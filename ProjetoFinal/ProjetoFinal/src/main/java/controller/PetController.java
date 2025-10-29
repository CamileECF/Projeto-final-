package controller;

import java.util.List;

import DAO.PetDAO;
import model.Pet;

public class PetController {

    private PetDAO petDAO;

    public PetController() {
        this.petDAO = new PetDAO();
    }

    // CREATE
    public void cadastrarPet(Pet pet) {
        petDAO.criarPet(pet);
    }

    // READ - Buscar por ID
    public Pet buscarPetPorId(long idPet) {
        return petDAO.buscarPetPorId(idPet);
    }

    // READ - Listar todos
    public void listarPets() {
        List<Pet> listaPets = petDAO.listarPets();
        
        System.out.println("\n=== LISTA DE PETS (do Banco de Dados) ===");
        if (listaPets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
        } else {
            for (Pet p : listaPets) {
                System.out.println("ID: " + p.getId() + " - " + p.getNome() + 
                                   " | Espécie: " + p.getEspecie() +
                                   " | Raça: " + p.getRaca() +
                                   " | Tutor: " + p.getTutor().getNome());
            }
        }
    }

    // UPDATE
    public void atualizarPet(Pet petAtualizado) {
        petDAO.atualizarPet(petAtualizado);
    }

    // DELETE
    public void removerPet(long idPet) {
        petDAO.excluirPet(idPet);
    }
}