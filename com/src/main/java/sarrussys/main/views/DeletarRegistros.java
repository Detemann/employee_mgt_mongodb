package sarrussys.main.views;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.controllers.DepartamentoController;
import sarrussys.main.controllers.FuncionarioController;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static sarrussys.main.views.Menu.clearScreen;

public class DeletarRegistros {
    private Scanner sc;
    private DepartamentoController departamentoController;
    private FuncionarioController funcionarioController;


    public DeletarRegistros(ConexaoMongoDB conexao){
        this.sc = new Scanner(System.in);
        this.departamentoController = new DepartamentoController(conexao);
        this.funcionarioController = new FuncionarioController(conexao);
    }

    public void inicio(){
        clearScreen();
        try {
            int continua;
            do{
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
                System.out.println("Deseja continuar deletando?\n[ 1 ] Sim\n[ 2 ] Não");
                continua = sc.nextInt();
            }while (continua == 1);

        }catch (Exception e){
            System.out.println("[DeletarRegstros] Erro Inesperado \n"+e.getMessage());
        }
    }

    private boolean deletarDepartamento() throws Exception {
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
                if(resultado.get(i).getNomeChefe().isEmpty()){
                    nomeChefe = "sem chefe";
                }else{
                    nomeChefe = resultado.get(i).getNomeChefe();
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
                    System.out.println(">>> Departamento não localizado, insira um ID valido!");
                }
            }while (departamento == null);

            //confirmacao
            System.out.println("\n\n>>> Departamento a ser DELETADO: ");
            System.out.println("Nome: "+departamento.getNomeDepartamento());
            System.out.println("\n[ 1 ] Confirmar\n[ 2 ] Cancelar");
            int op = sc.nextInt();

            if(op == 1){
                //se o departamento está associado a algum Funcionario ele vai informar
                if(funcionarioController.verificaRelacionamentoDepartamentoxFuncionario(departamento.getNomeDepartamento())){
                    System.out.println("\n\n>>> O Departamento escolhido está associado a um Funcionário");
                    System.out.println("Deseja excluir realmente?\n[ 1 ] Sim\n[ 2 ] Cancelar");
                    int op2 = sc.nextInt();

                    if(op2 == 1){ //excluindo departamento com associacao
                        //se a remocao dos funcionarios desse departamento foi bem sucedida
                        if(funcionarioController.removeFuncionariosdoDepartamento(departamento.getNomeDepartamento())){
                            //verifica se o departamento foi deletado corretamente
                            if(departamentoController.deletarDepartamento(departamento)){
                                return true; //departamento excluido com sucesso
                            }else{
                                return false; //departamento não foi excluido por algum motivo
                            }

                        }else {
                            return false; //deu algum problema na remoção dos funcionarios da tabela departamento
                        }
                    }else{
                        System.out.println("\n>>> Operação Cancelada");
                        sair();
                        return false;
                    }
                }else { //bloco que deleta direto o departamento pois nao existe nenhum funcionario iniserido nele
                    return departamentoController.deletarDepartamento(departamento);
                }
            }else {
                System.out.println("\n>>> Operação Cancelada");
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
                if (resultado.get(i).getNomeDepartamento().isEmpty()) {
                    nomeDepartamento = "sem departamento";
                } else {
                    nomeDepartamento = resultado.get(i).getNomeDepartamento();
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
                    System.out.println(">>> Funcionario não localizado, insira um ID valido!");
                }
            }while (funcionario == null);

            //confirmacao
            System.out.println("\n\nFuncionário a ser DELETADO: ");
            System.out.println("Nome: "+funcionario.getNome());
            System.out.println("CPF: "+funcionario.getCpf());

            System.out.println("\n[ 1 ] Confirmar\n[ 2 ] Cancelar");
            int op = sc.nextInt();

            if(op == 1){
                //se o FUNCIONARIO está associado a algum DEPARTAMENTO ele vai informar
                if(funcionarioController.verificaRelacionamentFuncionarioxDepartamento(funcionario.getIdFuncionario())){
                    System.out.println("\n\n>>> O Funcionario escolhido está associado a um Departamento");
                    System.out.println("Deseja excluir realmente?\n[ 1 ] Sim\n[ 2 ] Cancelar");
                    int op2 = sc.nextInt();

                    if(op2 == 1){ //excluindo funcionario com associacao
                        //se a remocao do departamento desse funcionario foi bem sucedida
                        if(funcionarioController.removeDepartamentodoFuncionario(funcionario)){
                            //verifica se o funcionariio foi deletado corretamente
                            if(funcionarioController.deletarFuncionario(funcionario)){
                                return true; //funcionario excluido com sucesso
                            }else{
                                return false; //funcionario não foi excluido por algum motivo
                            }
                        }else {
                            return false; //deu algum problema na remoção dos funcionarios da tabela departamento
                        }
                    }else{
                        System.out.println("\n>>> Operação Cancelada");
                        sair();
                        return false;
                    }
                }else { //bloco que deleta direto o funcionario pois nao existe nenhum departamento iniserido nele
                    return funcionarioController.deletarFuncionario(funcionario);
                }
            }else {
                System.out.println("\n>>> Operação Cancelada");
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
