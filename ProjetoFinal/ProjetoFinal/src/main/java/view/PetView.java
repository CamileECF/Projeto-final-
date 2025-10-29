package view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.PetController;
import controller.TutorController;
import model.Pet;
import model.Tutor;

public class PetView {

    private PetController controller;
    private TutorController tutorController;

    public PetView() {
        this.controller = new PetController();
        this.tutorController = new TutorController();
    }

    /**
     * Método principal da tela, chamado pelo MainView.
     * Gerencia o menu de pets e recebe o Scanner global.
     */
    public void menuPets(Scanner sc) {
        int opcao = -1;

        do {
            System.out.println("\n===== GERENCIAMENTO DE PETS =====");
            System.out.println("1 - Cadastrar Novo Pet");
            System.out.println("2 - Listar Todos os Pets");
            System.out.println("3 - Atualizar Pet (por ID)");
            System.out.println("4 - Remover Pet (por ID)");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
                opcao = -1;
            } finally {
                sc.nextLine();
            }

            switch (opcao) {
                case 1:
                    cadastrar(sc);
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    atualizar(sc);
                    break;
                case 4:
                    remover(sc);
                    break;
                case 0:
                    System.out.println("\nRetornando ao Menu Principal...");
                    break;
                default:
                    System.out.println("\nOpção inválida! Trente novamente.");
                    break;
            }

        } while (opcao != 0);
    }

    /**
     * Método privado para o caso 1: Cadastrar Pet
     */
    private void cadastrar(Scanner sc) {
        try {
            System.out.println("\n--- Cadastro de Novo Pet ---");
            
            // Primeiro mostra os tutores disponíveis
            System.out.println("\nTutores disponíveis:");
            tutorController.listarTutores();
            
            System.out.print("ID do Tutor: ");
            int idTutor = sc.nextInt();
            sc.nextLine();

            Tutor tutor = tutorController.buscarTutorPorId(idTutor);
            if (tutor == null) {
                System.out.println("Tutor não encontrado!");
                return;
            }

            System.out.print("Nome do pet: ");
            String nome = sc.nextLine();

            System.out.print("Espécie: ");
            String especie = sc.nextLine();

            System.out.print("Raça: ");
            String raca = sc.nextLine();

            LocalDate dataNascimento = null;
            while (dataNascimento == null) {
                System.out.print("Data de Nascimento (AAAA-MM-DD): ");
                String dataStr = sc.nextLine();
                try {
                    dataNascimento = LocalDate.parse(dataStr);
                } catch (DateTimeParseException e) {
                    System.out.println("Data inválida! Use o formato AAAA-MM-DD.");
                }
            }

            System.out.print("Sexo (Macho/Fêmea): ");
            String sexo = sc.nextLine();

            // Cria o objeto Model
            Pet novoPet = new Pet();
            novoPet.setNome(nome);
            novoPet.setEspecie(especie);
            novoPet.setRaca(raca);
            novoPet.setDataNascimento(dataNascimento);
            novoPet.setSexo(sexo);
            novoPet.setTutor(tutor);

            // Envia para o Controller
            controller.cadastrarPet(novoPet);

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: O ID do tutor deve ser um número.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Erro durante o cadastro: " + e.getMessage());
        }
    }

    /**
     * Método privado para o caso 2: Listar Pets
     */
    private void listar() {
        controller.listarPets();
    }

    /**
     * Método privado para o caso 3: Atualizar Pet
     */
    private void atualizar(Scanner sc) {
        try {
            System.out.println("\n--- Atualização de Pet ---");
            listar();
            System.out.print("\nInforme o ID do pet que deseja atualizar: ");
            long idAtualizar = sc.nextLong();
            sc.nextLine();

            // Busca o pet existente
            Pet petExistente = controller.buscarPetPorId(idAtualizar);
            if (petExistente == null) {
                System.out.println("Pet não encontrado!");
                return;
            }

            System.out.println("\nDados atuais do Pet:");
            System.out.println("Nome: " + petExistente.getNome());
            System.out.println("Espécie: " + petExistente.getEspecie());
            System.out.println("Raça: " + petExistente.getRaca());
            System.out.println("Data Nascimento: " + petExistente.getDataNascimento());
            System.out.println("Sexo: " + petExistente.getSexo());
            System.out.println("Tutor: " + petExistente.getTutor().getNome());

            System.out.print("\nNovo nome: ");
            String novoNome = sc.nextLine();
            System.out.print("Nova espécie: ");
            String novaEspecie = sc.nextLine();
            System.out.print("Nova raça: ");
            String novaRaca = sc.nextLine();

            LocalDate novaDataNascimento = null;
            System.out.print("Nova data de nascimento (AAAA-MM-DD) [Enter para manter]: ");
            String dataStr = sc.nextLine();
            if (!dataStr.isEmpty()) {
                try {
                    novaDataNascimento = LocalDate.parse(dataStr);
                } catch (DateTimeParseException e) {
                    System.out.println("Data inválida! Mantendo data atual.");
                    novaDataNascimento = petExistente.getDataNascimento();
                }
            } else {
                novaDataNascimento = petExistente.getDataNascimento();
            }

            System.out.print("Novo sexo: ");
            String novoSexo = sc.nextLine();

            // Cria o objeto Model com todos os dados
            Pet petAtualizado = new Pet();
            petAtualizado.setId(idAtualizar);
            petAtualizado.setNome(novoNome);
            petAtualizado.setEspecie(novaEspecie);
            petAtualizado.setRaca(novaRaca);
            petAtualizado.setDataNascimento(novaDataNascimento);
            petAtualizado.setSexo(novoSexo);
            petAtualizado.setTutor(petExistente.getTutor());

            // Envia para o Controller
            controller.atualizarPet(petAtualizado);

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: O ID deve ser um número.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Erro durante a atualização: " + e.getMessage());
        }
    }

    /**
     * Método privado para o caso 4: Remover Pet
     */
    private void remover(Scanner sc) {
        try {
            System.out.println("\n--- Remoção de Pet ---");
            listar();
            System.out.print("\nInforme o ID do pet que deseja remover: ");
            long idRemover = sc.nextLong();
            sc.nextLine();

            // Confirmação
            System.out.print("Tem certeza que deseja remover este pet? (S/N): ");
            String confirmacao = sc.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                controller.removerPet(idRemover);
            } else {
                System.out.println("Remoção cancelada.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: O ID deve ser um número.");
            sc.nextLine();
        }
    }
}