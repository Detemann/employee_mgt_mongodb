package sarrussys.main.views;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.controllers.DepartamentoController;
import sarrussys.main.controllers.FuncionarioController;
import sarrussys.main.controllers.MenuController;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DeletarRegistros {
    private Scanner sc;
    private DepartamentoController departamentoController;
    private FuncionarioController funcionarioController;


    public DeletarRegistros(OracleDataSource conexao){
        this.sc = new Scanner(System.in);
        this.departamentoController = new DepartamentoController(conexao);
        this.funcionarioController = new FuncionarioController(conexao);
    }

    public void inicio(){
        try {
            System.out.println("O que deseja Deletar: ");
            System.out.println("[ 1 ] Funcionario\n[ 2 ] Departamento");
            int op = sc.nextInt();
            if(op == 1){
                if(deletarFuncionario()){
                    System.out.println(">>> Funcionario DELETADO!");
                    sair();
                }
            }else if(op == 2){
                if(deletarDepartamento()){
                    System.out.println(">>> Departamento DELETADO!");
                    sair();
                }
            }

        }catch (Exception e){
            System.out.println("[DeletarRegstros] Erro Inesperado \n"+e.getMessage());
        }
    }

    private boolean deletarDepartamento() throws IOException {
        List<Departamento> resultado = this.departamentoController.mostraDepartamentos();

        if (resultado == null) {
            System.out.println(">>> Nenhum departamento cadastrado!!");
            sair();
            return false;
        } else {
            //mostrar os departamentos cadastrados
            System.out.println("=================== DEPARTAMENTOS ===================");

            for (int i = 0; i < resultado.size(); i ++) {
                Integer idDepartamento = resultado.get(i).getIdDepartamento();
                String nomeDepartamento = resultado.get(i).getNomeDepartamento();
                String nomeChefe;
                if(resultado.get(i).getChefeDepartamento() == null){
                    nomeChefe = "sem chefe";
                }else{
                    nomeChefe = resultado.get(i).getChefeDepartamento().getNome();
                }

                System.out.println("" +
                        "ID: " + idDepartamento +
                        "\nNome: " + nomeDepartamento+
                        "\nChefe: "+ nomeChefe);
                System.out.println("-------------------");
            }

            //escolha do DEPARTAMENTO para deletar
            Departamento departamento;

            do{
                System.out.println("Informe o ID do Departamento para DELETAR: ");
                Integer id = sc.nextInt();
                departamento = this.departamentoController.pesquisaDepartamentoID(id);

                if(departamento == null){
                    System.out.println("Departamento não localizado, insira um ID valido!");
                }
            }while (departamento == null);

            //confirmacao
            System.out.println("\n\nDepartamento a ser DELETADO: ");
            System.out.println("Nome: "+departamento.getNomeDepartamento());
            System.out.println("\n[ 1 ] Confirmar\n[ 2 ] Cancelar");
            int op = sc.nextInt();

            if(op == 1){
                if(departamentoController.deletarDepartamento(departamento)){
                    return true;
                }else {
                    return false;
                }
            }else {
                System.out.println("\nOperação Cancelada");
                sair();
                return false;
            }
        }
    //
    }

    private boolean deletarFuncionario() throws IOException {
        List<Funcionario> resultado = this.funcionarioController.mostrarFuncionarios();

        if (resultado == null) {
            System.out.println(">>> Nenhum funcionario cadastrado!!");
            sair();
            return false;
        } else {
            //mostrar os funcionarios cadastrados
            System.out.println("=================== FUNCIONARIOS ===================");
            for (int i = 0; i < resultado.size(); i++) {

                Integer idFuncionario = resultado.get(i).getIdFuncionario();
                String nomeFuncionario = resultado.get(i).getNome();
                String cpf = resultado.get(i).getCpf();

                String nomeDepartamento;
                if (resultado.get(i).getDepartamento() == null) {
                    nomeDepartamento = "sem departamento";
                } else {
                    nomeDepartamento = resultado.get(i).getDepartamento().getNomeDepartamento();
                }

                System.out.println("" +
                        "ID: " + idFuncionario + "\nNome: " + nomeFuncionario +
                        "\nCPF: " + cpf + "\nDepartamento: " + nomeDepartamento);
                System.out.println("-------------------");
            }

            //escolha do funcionario para deletar
            Funcionario funcionario;
            do{
                System.out.println("Informe o ID do Funcionario para DELETAR: ");
                Integer id = sc.nextInt();
                funcionario = this.funcionarioController.pesquisaFuncionarioID(id);

                if(funcionario == null){
                    System.out.println("Funcionario não localizado, insira um ID valido!");
                }
            }while (funcionario == null);

            //confirmacao
            System.out.println("\n\nFuncionário a ser DELETADO: ");
            System.out.println("Nome: "+funcionario.getNome());
            System.out.println("CPF: "+funcionario.getCpf());
            System.out.println("\n[ 1 ] Confirmar\n[ 2 ] Cancelar");
            int op = sc.nextInt();

            if(op == 1){
                if(funcionarioController.deletarFuncionario(funcionario)){
                    return true;
                }else {
                    return false;
                }
            }else {
                System.out.println("\nOperação Cancelada");
                sair();
                return false;
            }
        }
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
