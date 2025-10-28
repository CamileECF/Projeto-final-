package view;

import controller.VacinaController;
import model.Vacina;
import java.util.Scanner;

public class VacinaView {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VacinaController controller = new VacinaController();

        int opcao;
        do {
            System.out.println("\n===== MENU VACINA =====");
            System.out.println("1 - Cadastrar Vacina");
            System.out.println("2 - Listar Vacinas");
            System.out.println("3 - Atualizar Vacina");
            System.out.println("4 - Remover Vacina");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome da vacina: ");
                    String nome = sc.nextLine();
                    System.out.print("Fabricante: ");
                    String fabricante = sc.nextLine();
                    System.out.print("Validade (em meses): ");
                    int validade = sc.nextInt();

                    Vacina novaVacina = new Vacina();
                    novaVacina.setNome(nome);
                    novaVacina.setFabricante(fabricante);
                    novaVacina.setPeriodoValidadeEmMeses(validade);
                    controller.cadastrarVacina(novaVacina);
                    break;

                case 2:
                    controller.listarVacinas();
                    break;

                case 3:
                    controller.listarVacinas();
                    System.out.print("\nInforme o número da vacina que deseja atualizar: ");
                    int indiceAtualizar = sc.nextInt() - 1;
                    sc.nextLine(); // limpar buffer

                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo fabricante: ");
                    String novoFabricante = sc.nextLine();
                    System.out.print("Nova validade (em meses): ");
                    int novaValidade = sc.nextInt();

                    Vacina vacinaAtualizada = new Vacina();
                    vacinaAtualizada.setNome(novoNome);
                    vacinaAtualizada.setFabricante(novoFabricante);
                    vacinaAtualizada.setPeriodoValidadeEmMeses(novaValidade);
                    controller.atualizarVacina(indiceAtualizar, vacinaAtualizada);
                    break;

                case 4:
                    controller.listarVacinas();
                    System.out.print("\nInforme o número da vacina que deseja remover: ");
                    int indiceRemover = sc.nextInt() - 1;
                    controller.removerVacina(indiceRemover);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("❌ Opção inválida!");
                    break;
            }

        } while (opcao != 0);

        sc.close();
    }
}
