package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.VacinaController;
import model.Vacina;

public class VacinaView {

    // O Controller é instanciado aqui, ele que fará a ponte com o DAO
    private VacinaController controller;

    public VacinaView() {
        this.controller = new VacinaController();
    }

    /**
     * Este é o método principal da tela, chamado pelo MainView.
     * Ele gerencia o menu de vacinas e recebe o Scanner global.
     */
    public void menuVacinas(Scanner sc) {
        int opcao = -1;

        do {
            System.out.println("\n===== GERENCIAMENTO DE VACINAS =====");
            System.out.println("1 - Cadastrar Nova Vacina");
            System.out.println("2 - Listar Todas as Vacinas");
            System.out.println("3 - Atualizar Vacina (por ID)");
            System.out.println("4 - Remover Vacina (por ID)");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
                opcao = -1; // Reseta a opção para inválida
            } finally {
                sc.nextLine(); // Limpa o buffer do scanner (essencial!)
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
                    System.out.println("\nOpção inválida! Tente novamente.");
                    break;
            }

        } while (opcao != 0);
    }

    /**
     * Método privado para o caso 1: Cadastrar Vacina
     */
    private void cadastrar(Scanner sc) {
        try {
            System.out.println("\n--- Cadastro de Nova Vacina ---");
            System.out.print("Nome da vacina: ");
            String nome = sc.nextLine();

            System.out.print("Fabricante: ");
            String fabricante = sc.nextLine();

            System.out.print("Validade (em meses): ");
            int validade = sc.nextInt();
            sc.nextLine(); // Limpa o buffer

            // 1. Cria o objeto Model
            Vacina novaVacina = new Vacina();
            novaVacina.setNome(nome);
            novaVacina.setFabricante(fabricante);
            novaVacina.setPeriodoValidadeEmMeses(validade);

            // 2. Envia para o Controller (que chama o DAO)
            controller.cadastrarVacina(novaVacina);

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: A validade deve ser um número.");
            sc.nextLine(); // Limpa o buffer em caso de erro
        }
    }

    /**
     * Método privado para o caso 2: Listar Vacinas
     */
    private void listar() {
        // O Controller chama o DAO, busca no banco e já imprime a lista formatada.
        controller.listarVacinas();
    }

    /**
     * Método privado para o caso 3: Atualizar Vacina
     */
    private void atualizar(Scanner sc) {
        try {
            System.out.println("\n--- Atualização de Vacina ---");
            // Primeiro, mostramos a lista para o usuário saber qual ID usar
            listar();
            System.out.print("\nInforme o ID da vacina que deseja atualizar: ");
            long idAtualizar = sc.nextLong();
            sc.nextLine(); // Limpa o buffer

            // (O ideal aqui seria buscar a vacina por ID para ver se ela existe)
            // (Vamos pular essa etapa por simplicidade)

            System.out.print("Novo nome: ");
            String novoNome = sc.nextLine();
            System.out.print("Novo fabricante: ");
            String novoFabricante = sc.nextLine();
            System.out.print("Nova validade (em meses): ");
            int novaValidade = sc.nextInt();
            sc.nextLine(); // Limpa o buffer

            // 1. Cria o objeto Model com TODOS os dados (incluindo o ID)
            Vacina vacinaAtualizada = new Vacina();
            vacinaAtualizada.setId(idAtualizar);
            vacinaAtualizada.setNome(novoNome);
            vacinaAtualizada.setFabricante(novoFabricante);
            vacinaAtualizada.setPeriodoValidadeEmMeses(novaValidade);

            // 2. Envia para o Controller (que chama o DAO)
            controller.atualizarVacina(vacinaAtualizada);

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: O ID e a validade devem ser números.");
            sc.nextLine(); // Limpa o buffer em caso de erro
        }
    }

    /**
     * Método privado para o caso 4: Remover Vacina
     */
    private void remover(Scanner sc) {
        try {
            System.out.println("\n--- Remoção de Vacina ---");
            // Mostramos a lista para o usuário saber qual ID usar
            listar();
            System.out.print("\nInforme o ID da vacina que deseja remover: ");
            long idRemover = sc.nextLong();
            sc.nextLine(); // Limpa o buffer

            // Envia o ID para o Controller (que chama o DAO)
            controller.removerVacina(idRemover);

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: O ID deve ser um número.");
            sc.nextLine(); // Limpa o buffer em caso de erro
        }
    }
}