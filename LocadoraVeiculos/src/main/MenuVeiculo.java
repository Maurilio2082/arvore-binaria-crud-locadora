package main;

import arvore.ArvoreLocatario;
import arvore.ArvoreVeiculo;
import dados.ItemLocatario;
import dados.ItemVeiculo;

import java.util.Scanner;
import java.util.InputMismatchException;

public class MenuVeiculo {
    static Scanner scan = new Scanner(System.in);
    static ArvoreVeiculo arvoreVeiculo = new ArvoreVeiculo();
    static ArvoreLocatario arvoreLocatario = new ArvoreLocatario();
    static ItemVeiculo[] vetorVeiculos = new ItemVeiculo[10];

    public static void menuVeiculo() {
        char opcao;
        do {
            opcao = opcoesVeiculo();
            switch (opcao) {
                case '1':
                    inserirVeiculos();
                    break;
                case '2':
                    editarVeiculo();
                    break;
                case '3':
                    excluirVeiculos();
                    break;
                case '4':
                    exibirVeiculos();
                    break;
                case '5':
                    alugarVeiculo();
                    break;
                case '6':
                    devolverVeiculo();
                    break;
                case '7':
                    exibirVeiculosAlugados();
                    break;
                case '8':
                    exibirVeiculosDisponiveis();
                    break;
                case '9':
                    kmEspecifico();
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
    public static char opcoesVeiculo() {
        System.out.println("\n*****************************************");
        System.out.println("**                                     **");
        System.out.println("**             MENU VEICULO            **");
        System.out.println("**                                     **");
        System.out.println("**  1. Adicionar Veiculo               **");
        System.out.println("**  2. Editar Veiculo                  **");
        System.out.println("**  3. Remover Veiculo                 **");
        System.out.println("**  4. Listar Todos Veiculos           **");
        System.out.println("**  5. Alugar Veiculo                  **");
        System.out.println("**  6. Devolver Veiculo                **");
        System.out.println("**  7. Consultas Veiculos Alugados     **");
        System.out.println("**  8. Consultar Veiculos Disponiveis  **");
        System.out.println("**  9. Consultar Veiculos por Km       **");
        System.out.println("**  0. Voltar                          **");
        System.out.println("**                                     **");
        System.out.println("*****************************************");
        System.out.print("Escolha uma opcao: ");
        return scan.next().charAt(0);
    }

    // CRUD
    public static void inserirVeiculos() {
        linha();
        try {
            System.out.println("Digite o Modelo do Veiculo");
            scan.nextLine();
            String modelo = scan.nextLine().toUpperCase();

            System.out.println("Digite a Marca do Veiculo");
            String marca = scan.nextLine().toUpperCase();

            System.out.println("Digite a Cor do Veiculo");
            String cor = scan.nextLine().toUpperCase();

            int anoFabricacao = 0;
            boolean anoValido = false;
            do {
                System.out.println("Digite o Ano de Fabricação do Veiculo (4 digitos):");
                String entradaAno = scan.nextLine();
                if (entradaAno.matches("\\d{4}")) {
                    try {
                        anoFabricacao = Integer.parseInt(entradaAno);
                        anoValido = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada invalida. Digite um numero inteiro de 4 digitos.");
                    }
                } else {
                    System.out.println("Ano deve ter 4 digitos. Tente novamente.");
                }
            } while (!anoValido);

            int quilometragem = lerInteiro("Digite a Quilometragem do Veiculo");

            int chave;
            do {
                chave = lerInteiro("Digite o Codigo do Veiculo");
                if (arvoreVeiculo.buscarVeiculo(chave) != null) {
                    System.out.println("Erro: Codigo ja existente. Por favor, insira um codigo diferente.");
                }
            } while (arvoreVeiculo.buscarVeiculo(chave) != null);

            ItemVeiculo veiculo = new ItemVeiculo(chave, modelo, marca, cor, anoFabricacao, quilometragem);
            if (arvoreVeiculo.inserir(veiculo)) {
                System.out.println("Cadastro de Veiculo efetuado com sucesso \n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada invalida. Por favor, tente novamente.");
            scan.nextLine();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void editarVeiculo() {
        linha();
        System.out.println("Digite o codigo do veiculo que deseja editar:");
        int codVeiculo = scan.nextInt();
        scan.nextLine();

        ItemVeiculo veiculo = arvoreVeiculo.buscarVeiculo(codVeiculo);

        if (veiculo == null) {
            System.out.println("Veiculo nao encontrado.");
            return;
        }

        System.out.println("Digite o novo modelo (ou 0 para nao alterar):");
        String modelo = scan.nextLine().toUpperCase();
        if (!modelo.equals("0")) {
            veiculo.setNome(modelo);
        }

        System.out.println("Digite a nova marca (ou 0 para nao alterar):");
        String marca = scan.nextLine().toUpperCase();
        if (!marca.equals("0")) {
            veiculo.setMarca(marca);
        }

        System.out.println("Digite a nova cor (ou 0 para nao alterar):");
        String cor = scan.nextLine().toUpperCase();
        if (!cor.equals("0")) {
            veiculo.setCor(cor);
        }

        boolean anoValido = false;
        do {
            System.out.println("Digite o novo ano de fabricacao (ou 0 para nao alterar):");
            String entrada = scan.nextLine();
            if (entrada.equals("0")) {
                anoValido = true;
            } else if (entrada.matches("\\d{4}")) {
                try {
                    int anoFabricacao = Integer.parseInt(entrada);
                    veiculo.setAnoFabricacao(anoFabricacao);
                    anoValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Entrada invalida. Digite um numero inteiro de 4 digitos.");
                }
            } else {
                System.out.println("Erro: Ano deve ter 4 digitos. Tente novamente.");
            }
        } while (!anoValido);

        int quilometragem = lerInteiro("Digite a nova quilometragem (ou 0 para nao alterar):");
        if (quilometragem > 0) {
            veiculo.setQuilometragem(quilometragem);
        }
        System.out.println("Veiculo atualizado com sucesso.");
    }

    public static void excluirVeiculos() {
        linha();
        if (arvoreVeiculo.eVazia()) {
            System.out.println("Nao existe Veiculo cadastrado");
        } else {
            System.out.println("Excluir um Veiculo\nDigite o codigo do Veiculo");
            int valor = scan.nextInt();
            if (arvoreVeiculo.remover(valor)) {
                System.out.println("Remocao efetuada com sucesso");
            } else {
                System.out.println("Erro: Codigo do Veiculo nao encontrado ou veiculo esta alugado.");
            }
        }
    }

    public static void exibirVeiculos() {
        linha();
        if (arvoreVeiculo.eVazia()) {
            System.out.println("Nao existem veiculos cadastrados.");
        } else {
            vetorVeiculos = arvoreVeiculo.CamPreFixado();
            String msg = String.format("%-10s %-20s %-20s %-10s %-15s %-15s%n", "ID", "Modelo", "Marca", "Cor", "Ano",
                    "Quilometragem");
            for (int i = 0; i < arvoreVeiculo.getQuantNos(); i++) {
                msg += String.format("%-10s %-20s %-20s %-10s %-15s %-15s%n", vetorVeiculos[i].getChave(),
                        vetorVeiculos[i].getNome(), vetorVeiculos[i].getMarca(), vetorVeiculos[i].getCor(),
                        vetorVeiculos[i].getAnoFabricacao(), vetorVeiculos[i].getQuilometragem());
            }
            System.out.println(msg);
            linha();
        }
    }

    // RELACIONAMENTO
    public static void alugarVeiculo() {
        linha();
        System.out.println();
        int codLocatario = lerInteiro("Digite o codigo do locatario:");
        int codVeiculo = lerInteiro("Digite o codigo do veiculo:");

        ItemLocatario locatario = MenuLocatario.arvoreLocatario.buscarLocatario(codLocatario);
        ItemVeiculo veiculo = MenuVeiculo.arvoreVeiculo.buscarVeiculo(codVeiculo);

        if (locatario != null && veiculo != null && !veiculo.getLocado()) {
            veiculo.setLocatario(locatario);
            veiculo.setLocado(true);
            locatario.setLocado(true);
            System.out.println("Veiculo alugado com sucesso.");
        } else {
            System.out.println("Erro: Verifique se o locatario e o veiculo existem e se o veiculo nao esta locado.");

        }
    }

    public static void devolverVeiculo() {
        linha();
        int codVeiculo = lerInteiro("Digite o codigo do veiculo");
        ItemVeiculo veiculo = arvoreVeiculo.buscarVeiculo(codVeiculo);

        if (veiculo != null && veiculo.getLocado()) {
            ItemLocatario locatario = veiculo.getLocatario();
            if (locatario != null) {
                locatario.setLocado(false);
            }
            veiculo.setLocatario(null);
            veiculo.setLocado(false);

            System.out.println("Veículo devolvido com sucesso.");
        } else {
            System.out.println("Erro: Verifique se o veiculo existe e se está locado.");
        }
    }

    // CONSULTAS VEICULOS
    public static void exibirVeiculosAlugados() {
        linha();
        if (arvoreVeiculo.eVazia()) {
            System.out.println("Nao existem veiculos cadastrados.");
        } else {
            ItemVeiculo[] veiculosAlugados = arvoreVeiculo.veiculosAlugados();
            if (veiculosAlugados.length == 0) {
                System.out.println("Nao ha veiculos alugados.");
            } else {
                String msg = String.format("%-10s %-20s %-20s %-10s %-15s %-15s %-20s%n", "ID", "Modelo", "Marca",
                        "Cor", "Ano", "Quilometragem", "Locatario");

                for (int i = 0; i < veiculosAlugados.length; i++) {
                    ItemVeiculo veiculo = veiculosAlugados[i];
                    if (veiculo != null) {
                        msg += String.format("%-10s %-20s %-20s %-10s %-15s %-15s %-20s%n", veiculo.getChave(),
                                veiculo.getNome(), veiculo.getMarca(), veiculo.getCor(), veiculo.getAnoFabricacao(),
                                veiculo.getQuilometragem(), veiculo.getLocatario().getNome());
                    }
                }

                System.out.println(msg);
            }
        }
    }

    public static void exibirVeiculosDisponiveis() {
        linha();
        if (arvoreVeiculo.eVazia()) {
            System.out.println("Nao existem veiculos cadastrados.");
        } else {
            ItemVeiculo[] veiculosDisponiveis = arvoreVeiculo.veiculosDisponiveis();
            if (veiculosDisponiveis.length == 0) {
                System.out.println("Nao ha veiculos disponiveis.");
            } else {
                String msg = String.format("%-10s %-20s %-20s %-10s %-15s %-15s%n", "ID", "Modelo", "Marca", "Cor",
                        "Ano", "Quilometragem");

                for (int i = 0; i < veiculosDisponiveis.length; i++) {
                    ItemVeiculo veiculo = veiculosDisponiveis[i];
                    if (veiculo != null) {
                        msg += String.format("%-10s %-20s %-20s %-10s %-15s %-15s%n", veiculo.getChave(),
                                veiculo.getNome(), veiculo.getMarca(), veiculo.getCor(), veiculo.getAnoFabricacao(),
                                veiculo.getQuilometragem());
                    }
                }

                System.out.println(msg);
            }
        }
    }

    public static void kmEspecifico() {
        linha();
        try {

            scan.nextLine();
            int kmConsulta = lerInteiro("Digite a quilometragem inicial que procura:");

            ItemVeiculo[] veiculosKm = arvoreVeiculo.quilometragem(kmConsulta);
            if (veiculosKm.length == 0 || veiculosKm[0] == null) {
                System.out.println("Nao ha veiculos com quilometragem igual ou superior a informada.");
            } else {
                String msg = String.format("%-10s %-20s %-10s %-15s%n", "ID", "Cliente", "Idade", "Vencimento CNH");
                for (int i = 0; i < veiculosKm.length; i++) {
                    ItemVeiculo veiculo = veiculosKm[i];
                    if (veiculo != null) {
                        msg += String.format("%-10s %-20s %-20s %-10s %-15s %-15s%n", veiculo.getChave(),
                                veiculo.getNome(), veiculo.getMarca(), veiculo.getCor(), veiculo.getAnoFabricacao(),
                                veiculo.getQuilometragem());
                    }
                }
                System.out.println(msg);
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
