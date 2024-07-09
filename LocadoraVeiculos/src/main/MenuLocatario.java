package main;

import arvore.ArvoreLocatario;
import dados.ItemLocatario;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class MenuLocatario {
	static Scanner scan = new Scanner(System.in);
	static ArvoreLocatario arvoreLocatario = new ArvoreLocatario();
	static ItemLocatario[] vetorLocatarios = new ItemLocatario[10];

	public static void menuLocatario() {

		char opcao;
		do {
			opcao = opcoesLocatario();
			switch (opcao) {
			case '1':
				inserirLocatarios();
				break;
			case '2':
				editarLocatario();
				break;
			case '3':
				excluirLocatarios();
				break;
			case '4':
				exibirLocatarios();
				break;
			case '5':
				consultaCnh();
				break;
			case '6':
				idadeEspecifica();
				break;
			case '0':
				System.out.println("Voltando para o Menu Principal");
				break;
			default:
				System.out.println("Opcao invalida, tente novamente");
			}
		} while (opcao != '0');
	}

	// MENU
	private static char opcoesLocatario() {
		System.out.println("\n*****************************************");
		System.out.println("**                                     **");
		System.out.println("**             MENU LOCATARIO          **");
		System.out.println("**                                     **");
		System.out.println("**  1. Adicionar Locatario             **");
		System.out.println("**  2. Editar Locatario                **");
		System.out.println("**  3. Remover Locatario               **");
		System.out.println("**  4. Listar Todos Locatarios         **");
		System.out.println("**  5. Consultar CNH Vencidas          **");
		System.out.println("**  6. Consultar Locatarios por idade  **");
		System.out.println("**  0. Voltar                          **");
		System.out.println("**                                     **");
		System.out.println("*****************************************");
		System.out.print("Escolha uma opcao: ");
		return scan.next().charAt(0);
	}

	// CRUD
	public static void inserirLocatarios() {
	    linha();
	    try {
	        System.out.println("Digite o Nome do Cliente:");
	        scan.nextLine(); 

	        String nome = scan.nextLine().toUpperCase();

	        int idade;
	        do {
	            idade = lerInteiro("Digite a Idade do Cliente (entre 21 e 119 anos):");
	            if (idade < 21 || idade >= 120) {
	                System.out.println("Idade invalida. A idade deve ser entre 21 e 119 anos.");
	            }
	        } while (idade < 21 || idade >= 120);

	        String vencimentoCnh = "";
	        boolean formatoValido = false;
	        LocalDate dataVencimento = null;
	        LocalDate dataAtual = LocalDate.now();
	        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        do {
		        scan.nextLine(); 
	            System.out.println("Digite a Data de Vencimento da CNH (ou '0' para nao informar, formato yyyy-MM-dd):");
	            vencimentoCnh = scan.nextLine();
	            if (vencimentoCnh.equals("0")) {
	                formatoValido = true;
	            } else {
	                try {
	                    dataVencimento = LocalDate.parse(vencimentoCnh, formatar);
	                    if (dataVencimento.isAfter(dataAtual)) {
	                        formatoValido = true;
	                    } else {
	                        System.out.println("A data de vencimento da CNH deve ser posterior a data atual.");
	                    }
	                } catch (DateTimeParseException e) {
	                    System.out.println("Formato invalido. Por favor, insira no formato yyyy-MM-dd.");
	                }
	            }
	        } while (!formatoValido);

	        int chave;
	        do {
	            chave = lerInteiro("Digite o Codigo do Locatario:");
	            if (arvoreLocatario.buscarLocatario(chave) != null) {
	                System.out.println("Codigo já existente. Por favor, insira um codigo diferente.");
	            }
	        } while (arvoreLocatario.buscarLocatario(chave) != null);

	        ItemLocatario locatario = new ItemLocatario(chave, nome, idade, vencimentoCnh);
	        if (arvoreLocatario.inserir(locatario)) {
	            System.out.println("Cadastro de Locatario efetuado com sucesso.\n");
	        }
	    } catch (InputMismatchException e) {
	        System.out.println("Erro: Entrada invalida. Por favor, tente novamente.");
	        scan.nextLine(); 
	    } catch (Exception e) {
	        System.out.println("Erro: " + e.getMessage());
	    }
	}


	public static void editarLocatario() {
		linha();
		System.out.println("Digite o codigo do locatario que deseja editar:");
		int codLocatario = scan.nextInt();
		scan.nextLine();
		ItemLocatario locatario = arvoreLocatario.buscarLocatario(codLocatario);

		if (locatario == null) {
			System.out.println("Locatario nao encontrado.");
			return;
		}

		System.out.println("Digite o novo nome (ou 0 para nao alterar):");
		String nome = scan.nextLine().toUpperCase();
		if (!nome.equals("0")) {
			locatario.setNome(nome);
		}

		int idade = 0;
		boolean idadeValida = false;
		do {
			try {
				System.out.println("Digite a nova idade (ou 0 para nao alterar):");
				idade = scan.nextInt();
				scan.nextLine();

				if (idade == 0) {
					idadeValida = true;
				} else if (idade >= 21 && idade < 120) {
					locatario.setIdade(idade);
					idadeValida = true;
				} else {
					System.out.println("Idade invalida. A idade deve ser maior ou igual a 21 e menor que 120.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Erro: Entrada invalida. Por favor, digite um numero inteiro para a idade.");
				scan.nextLine();
			}
		} while (!idadeValida);

		System.out.println("Digite a nova data de vencimento da CNH (ou 0 para nao alterar):");
		String inputData = scan.nextLine();
		if (!inputData.equals("0")) {
			boolean formatoValido = false;
			LocalDate dataVencimento = null;
			LocalDate dataAtual = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			do {
				try {
					dataVencimento = LocalDate.parse(inputData, formatter);
					if (dataVencimento.isAfter(dataAtual)) {
						formatoValido = true;
					} else {
						System.out.println("A data de vencimento da CNH deve ser posterior a data atual.");
						System.out.println("Digite a nova data de vencimento da CNH (no formato yyyy-MM-dd):");
						inputData = scan.nextLine();
					}
				} catch (DateTimeParseException e) {
					System.out.println("Formato invalido. Por favor, insira no formato yyyy-MM-dd.");
					System.out.println("Digite a nova data de vencimento da CNH (no formato yyyy-MM-dd):");
					inputData = scan.nextLine();
				}
			} while (!formatoValido);

			locatario.setVencimentoCnh(inputData);
		}

		System.out.println("Locatario atualizado com sucesso.");
	}

	public static void excluirLocatarios() {
		linha();
		if (arvoreLocatario.eVazia()) {
			System.out.println("Nao existe Locatario cadastrado");
		} else {
			System.out.println("Excluir um Locatario\nDigite o codigo do Locatario:");
			int valor = scan.nextInt();
			if (arvoreLocatario.remover(valor)) {
				System.out.println("Remocao efetuada com sucesso");
			} else {
				System.out.println("Remocao nao efetuada, codigo do Locatario nao encontrado ou com veiculo locado.");
			}
		}
	}

	public static void exibirLocatarios() {
		linha();
		if (arvoreLocatario.eVazia()) {
			System.out.println("Nao ha locatarios cadastrados.");
		} else {
			vetorLocatarios = arvoreLocatario.CamPreFixado();
			String msg = String.format("%-10s %-20s %-10s %-15s%n", "ID", "Cliente", "Idade", "Vencimento CNH");
			for (int i = 0; i < arvoreLocatario.getQuantNos(); i++) {
				msg += String.format("%-10s %-20s %-10s %-15s%n", vetorLocatarios[i].getChave(),
						vetorLocatarios[i].getNome(), vetorLocatarios[i].getIdade(),
						vetorLocatarios[i].getVencimentoCnh());
			}
			System.out.println(msg);
		}
		linha();
	}

	// CONSULTAS LOCATARIOS
	public static void consultaCnh() {
		String dataConsulta = "";
		boolean formatoValido = false;
		scan.nextLine();
		while (!formatoValido) {
			System.out.println("Digite a Data de Vencimento da CNH (no formato yyyy-mm-dd):");
			try {
				dataConsulta = scan.nextLine();
				if (dataConsulta.matches("\\d{4}-\\d{2}-\\d{2}")) {
					formatoValido = true;
				} else {
					System.out.println("Formato invalido. Por favor, insira no formato yyyy-mm-dd.");
				}
			} catch (Exception e) {
				System.out.println("Erro ao ler a data. Por favor, tente novamente.");
				scan.nextLine();
			}
		}

		ItemLocatario[] locatariosVencidos = arvoreLocatario.cnhVencida(dataConsulta);
		if (locatariosVencidos == null || locatariosVencidos.length == 0 || locatariosVencidos[0] == null) {
			System.out.println("Nao ha locatarios com CNH vencida ate a data informada.");
		} else {
			linha();
			String msg = String.format("%-10s %-20s %-10s %-15s%n", "ID", "Cliente", "Idade", "Vencimento CNH");
			for (int i = 0; i < locatariosVencidos.length; i++) {
				ItemLocatario locatario = locatariosVencidos[i];
				if (locatario != null) {
					msg += String.format("%-10s %-20s %-10s %-15s%n", locatario.getChave(), locatario.getNome(),
							locatario.getIdade(), locatario.getVencimentoCnh());
				}
			}
			System.out.println(msg);
			linha();
		}
	}

	public static void idadeEspecifica() {
		linha();
		try {
			int idadeConsulta;
			do {
				System.out.println("Digite a idade que deseja consultar:");
				idadeConsulta = scan.nextInt();
				if (idadeConsulta <= 21 || idadeConsulta >= 120) {
					System.out.println("Idade invalida. A idade deve ser maior que 21 e menor que 120.");
				}
			} while (idadeConsulta <= 21 || idadeConsulta >= 120);

			ItemLocatario[] locatarios = arvoreLocatario.idade(idadeConsulta);

			if (locatarios.length == 0 || locatarios[0] == null) {
				System.out.println("Nao ha locatários com a idade informada.");
			} else {
				String msg = String.format("%-10s %-20s %-10s %-15s%n", "ID", "Cliente", "Idade", "Vencimento CNH");
				for (int i = 0; i < locatarios.length; i++) {
					ItemLocatario locatario = locatarios[i];
					if (locatario != null) {
						msg += String.format("%-10s %-20s %-10s %-15s%n", locatario.getChave(), locatario.getNome(),
								locatario.getIdade(), locatario.getVencimentoCnh());
					}
				}
				System.out.println(msg);
				linha();
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	// AUXILIAR
	private static int lerInteiro(String mensagem) {

		int numero = 0;
		boolean valido = false;
		do {
			try {
				System.out.println(mensagem);
				numero = scan.nextInt();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Erro: Entrada invalida. Por favor, digite um numero inteiro.");
				scan.nextLine();
			}
		} while (!valido);
		return numero;
	}

	private static void linha() {
		String linhaDecorativaCurta = "----------------------------------------------------------------------------------------------";
		System.out.println(linhaDecorativaCurta);
	}
}
