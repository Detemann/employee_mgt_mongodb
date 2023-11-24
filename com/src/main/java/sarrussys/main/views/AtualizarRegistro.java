package sarrussys.main.views;

import sarrussys.main.controllers.DepartamentoController;
import sarrussys.main.controllers.FuncionarioController;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;

import java.util.List;
import java.util.Scanner;

import static sarrussys.main.views.Menu.clearScreen;

public class AtualizarRegistro {
    private DepartamentoController departamentoController;
    private FuncionarioController funcionarioController;
    private Scanner sc;

    public AtualizarRegistro (ConexaoMongoDB dataSource) {
        this.sc = new Scanner(System.in);
        this.departamentoController = new DepartamentoController(dataSource);
        this.funcionarioController = new FuncionarioController(dataSource);
    }
    public void atualizarRegistro() throws InterruptedException {
        clearScreen();
        int op;
        boolean aa = true;
        do {
            System.out.println("O que deseja alterar: ");
            System.out.println("[ 1 ] Funcionario\n[ 2 ] Departamento\n[ 0 ] Voltar ao menu");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    mostraObjeto(op);
                    AtualizarObjeto(op);
                    op = 0;
                break;
                case 2:
                    mostraObjeto(op);
                    AtualizarObjeto(op);
                    op = 0;
                break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while(op != 0);
    }

    public void AtualizarObjeto(int type) throws InterruptedException {
        System.out.println("Digite o id que deseja modificar: ");
        int num = sc.nextInt();

        boolean quebra = false;
        String auxiliar = "";
        switch (type) {
            case 1:
                Funcionario funcionario = funcionarioController.pesquisaFuncionarioID(num);
                do {
                    System.out.println("Deseja modificar qual atributo: " +
                            "\n[1] Nome\n[2]CPF\n[3]Email\n[4]Salario Bruto\n[5]Salario Liquido\n[6]Departamento\n[0]Salvar e voltar ao menu");
                    num = sc.nextInt();

                    quebra = false;
                    auxiliar = "";
                    switch (num) {
                        case 1:
                            sc.nextLine();
                            System.out.println("Digite o nome: ");
                            auxiliar = sc.nextLine();
                            if (!auxiliar.isEmpty() && auxiliar.matches("^[a-zA-Z\\s]+$")) {
                                funcionario.setNome(auxiliar);
                            } else {
                                System.out.println("\nNome inválido\n");
                            }
                            break;
                        case 2:
                            System.out.println("Informe o CPF do funcionario: ");
                            auxiliar = sc.next();
                            if (!auxiliar.matches("\\d{11}")) {
                                System.out.println("CPF Incorreto, digite novamente no seguinte formato: 000.000.000-00: ");
                            } else {
                                auxiliar.replaceAll("[.-]", "");
                                funcionario.setCpf(auxiliar);
                            }
                            break;
                        case 3:
                            System.out.println("Informe o e-mail: ");
                            auxiliar = this.sc.next();
                            if (!auxiliar.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                                System.out.println("E-mail inválido, por favor, insira um e-mail válido.");
                            } else {
                                funcionario.setEmail(auxiliar);
                            }
                            break;
                        case 4:
                            System.out.println("Informe o salario Bruto: ");
                            Double salarioBruto = this.sc.nextDouble();
                            funcionario.setSalarioBruto(salarioBruto);
                            break;
                        case 5:
                            System.out.println("Informe o salario Bruto: ");
                            Double salarioLiquido = this.sc.nextDouble();
                            funcionario.setSalarioLiquido(salarioLiquido);
                            break;
                        case 6:
                            mostraObjeto(2);
                            System.out.println("Digite ID do departamento: ");
                            num = sc.nextInt();
                            funcionario.setNomeDepartamento(departamentoController.pesquisaDepartamentoID(num).getNomeDepartamento());
                            break;
                        case 0:
                            if(funcionarioController.atualizaFuncionario(funcionario)) {
                                System.out.println("Departamento: " +
                                        "\n Nome: "+ funcionario.getNome() +
                                        "\n Cpf: "+ funcionario.getCpf() +
                                        "\n Email: "+ funcionario.getEmail() +
                                        "\n Salario bruto: "+ funcionario.getSalarioBruto() +
                                        "\n Salario Liquido "+ funcionario.getSalarioLiquido() +
                                        "\n Departamento: "+ funcionario.getNomeDepartamento());
                                System.out.println("\nOperação bem-sucedida\n");
                                Thread.sleep(1000);
                            } else {
                                System.out.println("\nErro na operação\n");
                                Thread.sleep(1000);
                            }
                            break;
                        default:
                            System.out.println("Opção inválida");
                            Thread.sleep(1000);
                            break;
                    }
                } while (num != 0);
                break;
            case 2:
                Departamento departamento = departamentoController.pesquisaDepartamentoID(num);
                do {
                    System.out.println("Deseja modificar qual atributo: \n[1] Nome\n[2]Sigla\n[3]Chefe\n[0]Salvar e voltar ao menu");
                    num = sc.nextInt();

                    quebra = false;
                    auxiliar = "";
                    switch (num) {
                        case 1:
                            sc.nextLine();
                            System.out.println("Digite o nome: ");
                            auxiliar = sc.nextLine();
                            if(!auxiliar.isEmpty() && auxiliar.matches("^[a-zA-Z\\s]+$")) {
                                departamento.setNomeDepartamento(auxiliar);
                            } else {
                                System.out.println("Nome inválido");
                            }
                            break;
                        case 2:
                            System.out.println("Digite a sigla: ");
                            auxiliar = sc.next();
                            if (!auxiliar.isEmpty() && auxiliar.matches("^[a-zA-Z]+$")) {
                                departamento.setSigla(auxiliar);
                            } else {
                                System.out.println("Sigla inválida");
                            }
                            break;
                        case 3:
                            mostraObjeto(1);
                            System.out.println("Digite ID do funcionário: ");
                            num = sc.nextInt();
                            departamento.setNomeChefe(funcionarioController.pesquisaFuncionarioID(num).getNome());
                            break;
                        case 0:
                            if(departamentoController.atualizaDepartamento(departamento)) {
                                System.out.println("Departamento: " +
                                        "\n Nome: "+ departamento.getNomeDepartamento() +
                                        "\n Sigla: "+ departamento.getSigla() +
                                        "\n Chefe: "+ departamento.getNomeChefe());
                                System.out.println("\nOperação bem-sucedida\n");
                                Thread.sleep(1000);
                            } else {
                                System.out.println("\nErro na operação\n");
                                Thread.sleep(1000);
                            }
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                } while (num != 0);
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    /**
     * *
     * @param type int
     * @description 1 é igual a Funcionario e 2 é igual a Departamento
     */
    public void mostraObjeto (int type) {
        clearScreen();
        switch (type) {
            case 1:
                List<Funcionario> funcionarios = funcionarioController.mostrarFuncionarios();
                System.out.println("=================== FUNCIONARIOS ===================");
                for (Funcionario funcionario: funcionarios) {
                    System.out.println("ID: " + funcionario.getIdFuncionario() + "\nNome: " + funcionario.getNome() +
                            "\nCPF: " + funcionario.getCpf() + "\nEMAIL: "+ funcionario.getEmail()+"\nSalario Bruto: " + funcionario.getSalarioBruto() +
                            "\nSalario Liquido: " + funcionario.getSalarioLiquido());
                    String nomeDepartamento;
                    if(!funcionario.getNomeDepartamento().isEmpty()) {
                        Departamento departamento = departamentoController.buscarDepartamentoPorNome(funcionario.getNomeDepartamento());
                        nomeDepartamento = departamento.getNomeDepartamento();
                        nomeDepartamento += "\nID Departamento: "+departamento.getIdDepartamento();
                    } else {
                        nomeDepartamento = "Sem departamento";
                    }
                    System.out.println("Departamento: "+nomeDepartamento);
                    System.out.println("-------------------");
                }
                break;
            case 2:
                List<Departamento> departamentos = departamentoController.mostraDepartamentos();
                System.out.println("=================== DEPARTAMENTOS ===================");
                for (Departamento departamento: departamentos) {
                    System.out.println("ID: " + departamento.getIdDepartamento() + "\nNome: " + departamento.getNomeDepartamento() +
                            "\nSigla: " + departamento.getSigla());
                    String nomeChefe;
                    if(!departamento.getNomeChefe().isEmpty()) {
                        nomeChefe = departamento.getNomeChefe();
                    } else {
                        nomeChefe = "Sem chefe";
                    }
                    System.out.println("Chefe do departamento: "+nomeChefe);
                    System.out.println("-------------------");
                }
                break;
        }
    }
}