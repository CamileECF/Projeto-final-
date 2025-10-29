package view;

import java.util.Scanner;

public class MainView {

    public static void main(String[] args) {
        // Criamos UM Scanner que será compartilhado por toda a aplicação
        Scanner sc = new Scanner(System.in);
        
        // Criamos as instâncias de todas as Views (telas)
        TutorView tutorView = new TutorView();
        PetView petView = new PetView();
        VacinaView vacinaView = new VacinaView();
        MedicamentoView medicamentoView = new MedicamentoView();
        // (Futuramente) AplicacaoVacinaView aplicacaoView = new AplicacaoVacinaView();

        int opcao;
        do {
            System.out.println("\n===== BEM-VINDO AO SISTEMA PETSHOP =====");
            System.out.println("=====         MENU PRINCIPAL         =====");
            System.out.println("1 - Gerenciar Tutores");
            System.out.println("2 - Gerenciar Pets");
            System.out.println("3 - Gerenciar Vacinas");
            System.out.println("4 - Gerenciar Medicamentos");
            System.out.println("5 - Gerenciar Aplicações de Vacinas");
            System.out.println("0 - Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            
            // Tratamento para evitar erro se o usuário digitar uma letra
            try {
                opcao = sc.nextInt();
            } catch (Exception e) {
                opcao = -1; // Força a opção inválida
            }
            sc.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    tutorView.menuTutores(sc);
                    break;
                case 2:
                    petView.menuPets(sc);
                    break;
                case 3:
                    vacinaView.menuVacinas(sc);
                    break;
                case 4: 
                    medicamentoView.menuMedicamentos(sc);
                    break;
                case 5:
                    System.out.println("\n>> Módulo de Aplicações (em desenvolvimento).");
                    // aplicacaoView.menuAplicacoes(sc);
                    break;
                case 0:
                    System.out.println("\nEncerrando o sistema... Até logo!");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }
}