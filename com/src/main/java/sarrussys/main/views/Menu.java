package sarrussys.main.views;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.services.MenuServices;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private MenuServices menuServices;

    public Menu(OracleDataSource conexao){
        this.sc = new Scanner(System.in);
        this.menuServices = new MenuServices(conexao);
    }

    public void inicializacao() throws IOException {

        int quant_funcionario = 0;
        int quant_departamento = 0;
        quant_funcionario = this.menuServices.contarFuncionariosServices();
        quant_departamento = this.menuServices.contarDepartamentosService();


        System.out.println("######################################################");
        System.out.println("#         SISTEMA DE CONTROLE DE FUNCIONARIO         #");
        System.out.println("#                                                    #");
        System.out.println("#   TOTAL DE REGISTROS EXISTENTES                    #");
        System.out.println("#        1- FUNCIONARIOS: " + String.format("%-27d", quant_funcionario) + "#");
        System.out.println("#        2- DEPARTAMENTOS: " + String.format("%-26d", quant_departamento) + "#");
        System.out.println("#                                                    #");
        System.out.println("#                                                    #");
        System.out.println("#   CRIADO POR: LUCAS DETEMANN                       #");
        System.out.println("#               NATALIA TAYAR                        #");
        System.out.println("#               WANDERSON GONÇALVES                  #");
        System.out.println("#                                                    #");
        System.out.println("#                                                    #");
        System.out.println("#   DISCIPLINA: BANCO DE DADOS 2023/2                #");
        System.out.println("#   PROFESSOR: HOWARD ROATTI                         #");
        System.out.println("######################################################");
        sair();
        menuPrincipal();
    }

    public void menuPrincipal() throws IOException {
        Integer op = null;

        do {
            System.out.println("#======================= MENU PRINCIPAL =======================#");
            System.out.println("#                                                              #"
                    +"\n#                       ESCOLHA UMA OPÇÃO                      #"
                    +"\n#                                                              #"
                    +"\n#   [1] Relatórios                                             #"
                    +"\n#   [2] Inserir Registros                                      #"
                    +"\n#   [3] Remover Registros                                      #"
                    +"\n#   [4] Atualizar Registros                                    #"
                    +"\n#   [5] Sair                                                   #"
                    +"\n#                                                              #"
            );
            System.out.println("#==============================================================#\n");
            op = sc.nextInt();

            switch (op){
                case 1:
                    relatorios();
                    break;
                case 2:
                    //inserir registros
                    System.out.println("Inserir Registros");
                    break;
                case 3:
                    //remover registros
                    System.out.println("Remover registros");
                    break;
                case 4:
                    //atualizar registros
                    System.out.println("Atualizar Registros");
                    break;
                case 5:
                    System.out.println("\n\n\n FIM DO PROGRAMA!");
                    break;
                default:
                    System.out.println("Opção indisponível!\nTente Novamente");
                    break;
            }
        } while (op != 5);
    }

    public void relatorios() throws IOException {
        System.out.println("[1] Relatório 1 - Consulta o numero de funcionarios em cada Departamento\n" +
                "[2] Relatorio 2 - Consulta cada Funcionario e seu respectivo Departamento\n" +
                "[3] Relatorio 3 - Consulta cada Departamento e o nome do seu respectivo Chefe\n" +
                "[0] Sair");

        System.out.println("Informe sua opção:");
        int op = sc.nextInt();
        List<String> resultado;

        switch (op){
            case 1:
                resultado = this.menuServices.relatorio1();
                if(resultado == null){
                    System.out.println(">>> Nenhum registro encontrado!!");
                    sair();
                }else{
                    System.out.println("=========== RELATORIO 1 =========");
                    for (int i = 0; i < resultado.size(); i += 2) {
                        String departamento = resultado.get(i);
                        String quantidade = resultado.get(i + 1);
                        System.out.println("Departamento: "+ departamento+" - "+quantidade+" Funcionarios");
                    }
                    sair();
                }
                break;
            case 2:
                resultado = this.menuServices.relatorio2();
                if(resultado == null){
                    System.out.println(">>> Nenhum registro encontrado!!");
                    sair();
                }else {
                    System.out.println("========== RELATORIO 2 ==========");
                    for (int i = 0; i < resultado.size(); i += 2) {
                        String nome = resultado.get(i);
                        String departamento = resultado.get(i + 1);

                        System.out.println("Nome: " + nome);
                        if (departamento == null) {
                            System.out.println("Departamento: sem departamento");
                        } else {
                            System.out.println("Departamento: " + departamento);
                        }
                        System.out.println();
                    }
                    sair();
                }
                break;
            case 3:
                resultado = this.menuServices.relatorio3();
                if(resultado == null){
                    System.out.println(">>> Nenhum registro encontrado!!");
                    sair();
                }else {
                    System.out.println("========== RELATORIO 3 ==========");
                    for (int i = 0; i < resultado.size(); i += 2) {
                        String departamento = resultado.get(i);
                        String chefe = resultado.get(i + 1);

                        System.out.println("Departamento: " + departamento);
                        if (chefe == null) {
                            System.out.println("Chefe: sem chefe");
                        } else {
                            System.out.println("Chefe: " + chefe);
                        }
                        System.out.println();
                    }
                    sair();
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                break;
        }
    }

    public void sair() throws IOException {
        boolean sair = false;

        while(!sair) {
            System.out.println("Pressione ENTER para prosseguir");
            int input = System.in.read();

            if (input == 10) {
                sair = true;
            }
        }
    }

}
