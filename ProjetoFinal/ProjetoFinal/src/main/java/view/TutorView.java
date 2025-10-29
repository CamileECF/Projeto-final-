package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.TutorController;
import model.Tutor;

public class TutorView {

    private TutorController controller;

    public TutorView() {
        this.controller = new TutorController();
    }

    public void menuTutores(Scanner sc) {
        int opcao = -1;

        do {
            System.out.println("\n===== GERENCIAMENTO DE TUTORES =====");
            System.out.println("1 - Cadastrar Novo Tutor");
            System.out.println("2 - Listar Todos os Tutores");
            System.out.println("3 - Atualizar Tutor (por ID)");
            System.out.println("4 - Remover Tutor (por ID)");
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
                    System.out.println("\nOpção inválida! Tente novamente.");
                    break;
            }

        } while (opcao != 0);
    }

    private void cadastrar(Scanner sc) {
        try {
            System.out.println("\n--- Cadastro de Novo Tutor ---");
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("CPF (apenas números): ");
            String cpf = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            System.out.print("Senha: ");
            String senha = sc.nextLine();

            // SOLUÇÃO: Usar o construtor completo do Tutor
            String dataCadastro = java.time.LocalDate.now().toString();
            Tutor novoTutor = new Tutor(0, senha, dataCadastro, nome, cpf, email, telefone);

            // Envia para o Controller
            controller.cadastrarTutor(novoTutor);

        } catch (Exception e) {
            System.out.println("Erro durante o cadastro: " + e.getMessage());
        }
    }

    private void listar() {
        controller.listarTutores();
    }

    private void atualizar(Scanner sc) {
        try {
            System.out.println("\n--- Atualização de Tutor ---");
            listar();
            System.out.print("\nInforme o ID do tutor que deseja atualizar: ");
            int idAtualizar = sc.nextInt();
            sc.nextLine();

            // Busca o tutor existente
            Tutor tutorExistente = controller.buscarTutorPorId(idAtualizar);
            if (tutorExistente == null) {
                System.out.println("Tutor não encontrado!");
                return;
            }

            System.out.println("\nDados atuais do Tutor:");
            System.out.println("Nome: " + tutorExistente.getNome());
            System.out.println("CPF: " + tutorExistente.getCpf());
            System.out.println("Email: " + tutorExistente.getEmail());
            System.out.println("Telefone: " + tutorExistente.getTelefone());

            System.out.print("\nNovo nome: ");
            String novoNome = sc.nextLine();
            System.out.print("Novo CPF: ");
            String novoCpf = sc.nextLine();
            System.out.print("Novo email: ");
            String novoEmail = sc.nextLine();
            System.out.print("Novo telefone: ");
            String novoTelefone = sc.nextLine();
            System.out.print("Nova senha: ");
            String novaSenha = sc.nextLine();

            // SOLUÇÃO: Usar o construtor completo para atualização
            Tutor tutorAtualizado = new Tutor(
                idAtualizar, 
                novaSenha, 
                tutorExistente.getData_cadastro(), 
                novoNome, 
                novoCpf, 
                novoEmail, 
                novoTelefone
            );

            // Envia para o Controller
            controller.atualizarTutor(tutorAtualizado);

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: O ID deve ser um número.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Erro durante a atualização: " + e.getMessage());
        }
    }

    private void remover(Scanner sc) {
        try {
            System.out.println("\n--- Remoção de Tutor ---");
            listar();
            System.out.print("\nInforme o ID do tutor que deseja remover: ");
            int idRemover = sc.nextInt();
            sc.nextLine();

            System.out.print("Tem certeza que deseja remover este tutor? (S/N): ");
            String confirmacao = sc.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                controller.removerTutor(idRemover);
            } else {
                System.out.println("Remoção cancelada.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: O ID deve ser um número.");
            sc.nextLine();
        }
    }
}