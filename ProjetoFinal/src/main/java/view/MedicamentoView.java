package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MedicamentoController;
import model.Medicamento;

public class MedicamentoView {

    private MedicamentoController controller;

    public MedicamentoView() {
        this.controller = new MedicamentoController();
    }

    /**
     * Menu principal de Medicamentos, chamado pelo MainView
     */
    public void menuMedicamentos(Scanner sc) {
        int opcao = -1;

        do {
            System.out.println("\n===== GERENCIAMENTO DE MEDICAMENTOS =====");
            System.out.println("1 - Cadastrar Novo Medicamento");
            System.out.println("2 - Listar Todos os Medicamentos");
            System.out.println("3 - Atualizar Medicamento (por ID)");
            System.out.println("4 - Remover Medicamento (por ID)");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
                opcao = -1;
            } finally {
                sc.nextLine(); // Limpa o buffer
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
     * Método privado para o caso 1: Cadastrar Medicamento
     */
    private void cadastrar(Scanner sc) {
        try {
            System.out.println("\n--- Cadastro de Novo Medicamento ---");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            
            System.out.print("Descrição: ");
            String descricao = sc.nextLine();
            
            System.out.print("Preço (ex: 29.99): ");
            double preco = sc.nextDouble();
            
            System.out.print("Quantidade em Estoque: ");
            int estoque = sc.nextInt();
            sc.nextLine(); // Limpa o buffer
            
            // 1. Cria o objeto Model (ID 0, pois o banco vai gerar)
            Medicamento novoMed = new Medicamento(0, nome, descricao, preco, estoque);

            // 2. Envia para o Controller
            controller.adicionarMedicamento(novoMed);

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: Preço e Estoque devem ser números.");
            sc.nextLine(); // Limpa o buffer em caso de erro
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar medicamento: " + e.getMessage());
        }
    }

    /**
     * Método privado para o caso 2: Listar Medicamentos
     */
    private void listar() {
        controller.listarMedicamentos();
    }

    /**
     * Método privado para o caso 3: Atualizar Medicamento
     */
    private void atualizar(Scanner sc) {
        try {
            System.out.println("\n--- Atualização de Medicamento ---");
            listar(); // Mostra a lista para o usuário
            System.out.print("\nInforme o ID do medicamento que deseja atualizar: ");
            int idAtualizar = sc.nextInt();
            sc.nextLine(); // Limpa o buffer
            
            // Busca o medicamento atual para mostrar os dados antigos
            Medicamento medAntigo = controller.buscarMedicamentoPorId(idAtualizar);
            if (medAntigo == null) {
                System.out.println("Medicamento não encontrado.");
                return;
            }

            System.out.print("Novo Nome (Atual: " + medAntigo.getNome() + "): ");
            String nome = sc.nextLine();
            if (nome.isEmpty()) nome = medAntigo.getNome();

            System.out.print("Nova Descrição (Atual: " + medAntigo.getDescricao() + "): ");
            String descricao = sc.nextLine();
            if (descricao.isEmpty()) descricao = medAntigo.getDescricao();
            
            System.out.print("Novo Preço (Atual: " + medAntigo.getPreco() + "): ");
            String precoStr = sc.nextLine();
            double preco = precoStr.isEmpty() ? medAntigo.getPreco() : Double.parseDouble(precoStr);

            System.out.print("Nova Qtd. Estoque (Atual: " + medAntigo.getQuantidadeEstoque() + "): ");
            String estoqueStr = sc.nextLine();
            int estoque = estoqueStr.isEmpty() ? medAntigo.getQuantidadeEstoque() : Integer.parseInt(estoqueStr);

            // 1. Cria o objeto Model com TODOS os dados
            Medicamento medAtualizado = new Medicamento(idAtualizar, nome, descricao, preco, estoque);

            // 2. Envia para o Controller
            controller.atualizarMedicamento(medAtualizado);

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Erro de entrada: ID, Preço e Estoque devem ser números.");
            sc.nextLine(); 
        } catch (Exception e) {
            System.out.println("Erro ao atualizar medicamento: " + e.getMessage());
        }
    }

    /**
     * Método privado para o caso 4: Remover Medicamento
     */
    private void remover(Scanner sc) {
        try {
            System.out.println("\n--- Remoção de Medicamento ---");
            listar(); // Mostra a lista
            System.out.print("\nInforme o ID do medicamento que deseja remover: ");
            int idRemover = sc.nextInt();
            sc.nextLine(); // Limpa o buffer

            System.out.print("Tem certeza que deseja remover? (S/N): ");
            String confirmacao = sc.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                controller.removerMedicamento(idRemover);
            } else {
                System.out.println("Remoção cancelada.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: O ID deve ser um número.");
            sc.nextLine(); 
        }
    }
}