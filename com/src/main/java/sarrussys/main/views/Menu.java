package sarrussys.main.views;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.controllers.FuncionarioController;
import sarrussys.main.controllers.MenuController;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private MenuController menuController;
    private FuncionarioController funcionarioController;

    public Menu(OracleDataSource conexao){
        this.sc = new Scanner(System.in);
        this.menuController = new MenuController(conexao);
        this.funcionarioController = new FuncionarioController(conexao);
    }

    public void inicializacao() throws IOException {
        int quant_funcionario = 0;
        int quant_departamento = 0;
        quant_funcionario = this.menuController.contarFuncionarios();
        quant_departamento = this.menuController.contarDepartamentos();


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
            System.out.println("######################################################");
            System.out.println("#                >>MENU PRINCIPAL<<                  #"
                    +"\n#                ESCOLHA UMA OPÇÃO                   #"
                    +"\n#                                                    #"
                    +"\n#   [1] Relatórios                                   #"
                    +"\n#   [2] Inserir Registros                            #"
                    +"\n#   [3] Remover Registros                            #"
                    +"\n#   [4] Atualizar Registros                          #"
                    +"\n#   [5] Sair                                         #"
            );
            System.out.println("######################################################\n");
            op = sc.nextInt();

            switch (op){
                case 1:
                    relatorios();
                    break;
                case 2:
                    //inserir registros
                    inserirRegistros();
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
                resultado = this.menuController.relatorioDepartamentoNumFuncionariosController();
                if(resultado == null){
                    System.out.println(">>> Nenhum registro encontrado!!");
                    sair();
                }else{
                    System.out.println("\n=========== NUMERO DE FUNCIONARIOS POR DEPARTAMENTO ===========");
                    for (int i = 0; i < resultado.size(); i += 2) {
                        String departamento = resultado.get(i);
                        String quantidade = resultado.get(i + 1);
                        System.out.println("Departamento: "+ departamento+" - "+quantidade+" Funcionarios");
                    }
                    sair();
                }
                break;
            case 2:
                resultado = this.menuController.relatorioFuncionarioDepartamentoController();
                if(resultado == null){
                    System.out.println(">>> Nenhum registro encontrado!!");
                    sair();
                }else {
                    System.out.println("\n========== DEPARTAMENTO DE CADA FUNCIONARIO ==========");
                    for (int i = 0; i < resultado.size(); i += 2) {
                        String nome = resultado.get(i);
                        String departamento = resultado.get(i + 1);

                        System.out.println("Nome: " + nome);
                        if (departamento == null) {
                            System.out.println("Departamento: sem departamento");
                        } else {
                            System.out.println("Departamento: " + departamento);
                        }
                    }
                    sair();
                }
                break;
            case 3:
                resultado = this.menuController.relatorioDepartamentoChefeController();
                if(resultado == null){
                    System.out.println(">>> Nenhum registro encontrado!!");
                    sair();
                }else {
                    System.out.println("\n========== CHEFES DE DEPARTAMENTO ==========");
                    for (int i = 0; i < resultado.size(); i += 2) {
                        String departamento = resultado.get(i);
                        String chefe = resultado.get(i + 1);

                        System.out.println("Departamento: " + departamento);
                        if (chefe == null) {
                            System.out.println("Chefe: sem chefe");
                        } else {
                            System.out.println("Chefe: " + chefe);
                        }
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

    //meu METODO
    private void inserirRegistros() {

        System.out.println("========================= INSERIR REGISTROS =========================");
        System.out.println("Selecione a tabela que deseja inserir novo Registro");
        System.out.println("   [ 1 ] Funcionarios\n   [ 2 ] Departamentos");

        Integer op = -1;

        try {
            System.out.println("Informe a opção desejada: ");
            op = this.sc.nextInt();
            do{
                switch (op){
                    case 1: //inserir novo funcionario
                        Funcionario novoFuncionario = novoFuncionario();
                        System.out.println(novoFuncionario);

                        if(novoFuncionario == null){
                            System.out.println("\n\n>>> Funcionário já cadastrado!");
                        }else{

                            if(funcionarioController.cadastrarFuncionarioController(novoFuncionario)){
                                System.out.println("\n\n>>> Funcionário Inserido com Sucesso!");
                                System.out.println("Nome: "+novoFuncionario.getNome());
                                System.out.println("CPF: "+novoFuncionario.getCpf());
                            }

                        }
                        break;
                    case 2:

                        break;
                    case 0:
                        System.out.println("SAINDO...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                System.out.println("Deseja inserir mais um? \n[1] Funcionario  \n[2] Departamento \n[3] Terminar");
                op = sc.nextInt();
                if(op == 1){
                    op = 1;
                }else if(op == 2){
                    op = 2;
                } else if (op == 3) {
                    op = 0;
                }else{
                    System.out.println("Opção inválida!");
                }

            }while(op != 0);

        }catch(java.util.InputMismatchException e){
            System.out.println("Opção inválida. Por favor, insira um número válido.");
            this.sc.next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




    public Funcionario novoFuncionario() throws IOException {
        System.out.println("Informe o nome do funcionario: ");
        String nome = this.sc.next();

        System.out.println("Informe o CPF do funcionario: ");
        String cpf = this.sc.next();
        cpf = cpf.replaceAll("[.-]", "");

        // Validação do CPF (exige 11 dígitos numéricos)
        while (!cpf.matches("\\d{11}")){
            System.out.println("CPF Incorreto, digite novamente no seguinte formato: 000.000.000-00: ");
            cpf = this.sc.next();
            cpf = cpf.replaceAll("[.-]", "");
        }

        Matcher matcher;
        String email;
        // Validação do e-mail usando expressão regular
        do {
            System.out.println("Informe o e-mail: ");
            email = this.sc.next();
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(email);
            if(!matcher.matches()) {
                System.out.println("E-mail inválido, por favor, insira um e-mail válido.");
            }

        }while(!matcher.matches());

        System.out.println("Informe o salario Bruto: ");
        Double salarioBruto = this.sc.nextDouble();

        System.out.println("Informe o salario Liquido: ");
        Double salarioLiquido = this.sc.nextDouble();

        Departamento departamento = null;
        //ESCOLHER DEPARTAMENTO -- ESTA DANDO ERRO
        System.out.println("Deseja Inserir o Funcionario em um Departamento?\n[ S ] Sim\n[ N ] Nao");
        Integer departamentoID;


        char op = this.sc.next().charAt(0);
        if(op == 's'){
            List<Departamento> resultado = this.menuController.mostrarDepartamentos();
            if(resultado == null){
                System.out.println(">>> Nenhum registro encontrado!!");
                sair();
            }else {
                System.out.println("=================== DEPARTAMENTOS ===================");
                System.out.println(resultado.size());

                for (int i = 0; i < resultado.size(); i ++) {
                    Integer idDepartamento = resultado.get(i).getIdDepartamento();
                    String nomeDepartamento = resultado.get(i).getNomeDepartamento();
                    String siglaDepartamento = resultado.get(i).getSigla();
                    String nomeChefe;
                    if(resultado.get(i).getChefeDepartamento() == null){
                        nomeChefe = "sem chefe";
                    }else{
                        nomeChefe = resultado.get(i).getChefeDepartamento().getNome();
                    }

                    System.out.println("" +
                            "ID: " + idDepartamento + "\nNome: " + nomeDepartamento +
                            "\nSigla: " + siglaDepartamento + "\nChefe: "+ nomeChefe);
                }
            }

            do{
                System.out.println("\nInforme o ID do departamento: ");
                departamentoID = this.sc.nextInt();
                departamento = this.menuController.pesquisaDepartamentoID(departamentoID);

                if(departamento == null){
                    System.out.println("Departamento não localizado, insira um ID valido!");
                }
            }while (departamento == null);

        }else if(op == 'n'){
            System.out.println("");
        }

        Funcionario funcionarioNovo = new Funcionario(nome, cpf, email, salarioBruto, salarioLiquido, departamento);

        //se o funcionario existir
        if(this.funcionarioController.funcionarioExisteController(funcionarioNovo.getCpf())){
            return null;
        }

        return funcionarioNovo;
    }

    public void sair() throws IOException {
        boolean sair = false;

        while(!sair) {
            System.out.println("\n>> Pressione ENTER para prosseguir");
            int input = System.in.read();

            if (input == 10) {
                sair = true;
            }
        }
    }




}
