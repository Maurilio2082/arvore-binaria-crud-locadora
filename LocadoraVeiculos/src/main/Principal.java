package main;

import dados.*;

import java.util.Scanner;

public class Principal {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		MenuLocatario.arvoreLocatario.inserir(new ItemLocatario(29, "JOAO", 25, "2025-05-20"));
		MenuLocatario.arvoreLocatario.inserir(new ItemLocatario(45, "MARIA", 30, "2023-11-15"));
		MenuLocatario.arvoreLocatario.inserir(new ItemLocatario(98, "PEDRO", 60, "2024-01-10"));
		MenuLocatario.arvoreLocatario.inserir(new ItemLocatario(47, "ANA", 22, "2026-07-30"));
		MenuLocatario.arvoreLocatario.inserir(new ItemLocatario(31, "CARLOS", 60, "2022-09-05"));


		MenuVeiculo.arvoreVeiculo.inserir(new ItemVeiculo(84, "CIVIC", "HONDA", "PRETO", 2020, 15000));
		MenuVeiculo.arvoreVeiculo.inserir(new ItemVeiculo(154, "COROLLA", "TOYOTA", "BRANCO", 2019, 20000));
		MenuVeiculo.arvoreVeiculo.inserir(new ItemVeiculo(187, "ONIX", "CHERVROLET", "VERMELHO", 2021, 10000));
		MenuVeiculo.arvoreVeiculo.inserir(new ItemVeiculo(264, "FIESTA", "FORD", "AZUL", 2018, 25000));
		MenuVeiculo.arvoreVeiculo.inserir(new ItemVeiculo(65, "GOL", "VOLKSWAGEN", "CINZA", 2017, 30000));

		char opcao;
		do {
			opcao = menuPrincipal();
			switch (opcao) {
				case '1':
					MenuLocatario.menuLocatario();
					break;
				case '2':
					MenuVeiculo.menuVeiculo();
					break;
				case '0':
					System.out.println("Encerrando o Programa.");
					break;
				default:
					System.out.println("Opcao invalida, tente novamente.");
			}
		} while (opcao != '0');
	}

	public static char menuPrincipal() {

		System.out.println("\n*****************************************");
		System.out.println("**           FLEXAUTO LOCADORA         **");
		System.out.println("*****************************************");
		System.out.println("**   Criado por: Emmanuel Barcelos,    **");
		System.out.println("**   Jonathan Prado e Maurilio Marques **");
		System.out.println("**   Turma: 3SC1                       **");
		System.out.println("*****************************************");
		System.out.println("**                                     **");
		System.out.println("**            MENU PRINCIPAL           **");
		System.out.println("**                                     **");
		System.out.println("**  1. Menu Locatario                  **");
		System.out.println("**  2. Menu Veiculo                    **");
		System.out.println("**  0. Sair                            **");
		System.out.println("**                                     **");
		System.out.println("*****************************************");
		System.out.print("Escolha uma opcao: ");
		return scan.next().charAt(0);
	}
}
