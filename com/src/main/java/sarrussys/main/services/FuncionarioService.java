package sarrussys.main.services;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;
import sarrussys.main.services.database.DatabaseServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {
    private DatabaseServices databaseServices;
    private RelatorioServices relatorioServices;

    public FuncionarioService(OracleDataSource conexao){
        this.databaseServices = new DatabaseServices(conexao);
        this.relatorioServices = new RelatorioServices(conexao);
    }



    public boolean cadastrarFuncionarioService(Funcionario novoFuncionario){
        try {
            String nome = novoFuncionario.getNome();
            String cpf = novoFuncionario.getCpf();
            String email = novoFuncionario.getEmail();
            double salarioBruto = novoFuncionario.getSalarioBruto();
            double salarioLiquido = novoFuncionario.getSalarioLiquido();
            Integer departamentoID = null;
            Departamento departamento = novoFuncionario.getDepartamento();
            if(departamento != null){
                departamentoID = departamento.getIdDepartamento();
            }

            String sql = "INSERT INTO FUNCIONARIO (NOME, CPF, EMAIL, SALARIO_BRUTO, SALARIO_LIQUIDO, ID_DEPARTAMENTO)\n" +
                    "VALUES" +
                    " ('"+nome+"', '"+cpf+"', '"+email+"', "+salarioBruto+", "+salarioLiquido+", "+departamentoID+")";
            int resultado = this.databaseServices.fazerUpdate(sql);

            if(resultado == 0){
                return false;
            }else {
                return true;
            }
        }catch (Exception e) {
            System.out.println("[MenuService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }

    public boolean funcionaroExisteService(String cpf) {
        int quantidadeExistente = 0;
        try{
            String sql = "SELECT COUNT(*) AS CONTAGEM\n" +
                    "FROM FUNCIONARIO\n" +
                    "WHERE CPF = '"+cpf+"'";
            ResultSet consulta = this.databaseServices.fazerConsulta(sql);
            if(consulta.next()){
                quantidadeExistente = consulta.getInt("CONTAGEM");
            }
        }catch (SQLException e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        if(quantidadeExistente != 0){
            return true;
        }
       return false;
    }

    public Funcionario pesquisaFuncionarioID(Integer id){
        Funcionario funcionario = new Funcionario();

        try{
            String sql = "SELECT *\n" +
                    "FROM FUNCIONARIO\n" +
                    "WHERE ID_FUNCIONARIO = "+id;;

            ResultSet consultaFunc = this.databaseServices.fazerConsulta(sql);

            if (consultaFunc.next()){
                Integer idFuncionario = consultaFunc.getInt("ID_FUNCIONARIO");
                String nome = consultaFunc.getString("NOME");
                String cpf = consultaFunc.getString("CPF");
                String email = consultaFunc.getString("EMAIL");
                double salarioBruto = consultaFunc.getDouble("SALARIO_BRUTO");
                double salarioLiquido = consultaFunc.getDouble("SALARIO_LIQUIDO");
                Integer idDepartamento = consultaFunc.getInt("ID_DEPARTAMENTO");

                funcionario.setIdFuncionario(idFuncionario);
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setEmail(email);
                funcionario.setSalarioBruto(salarioBruto);
                funcionario.setSalarioLiquido(salarioLiquido);
                Departamento departamento = this.relatorioServices.pesquisaDepartamentoIdRelatorioService(idDepartamento);
                funcionario.setDepartamento(departamento);
                return funcionario;
            }

        }catch (SQLException e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return null;
    }

    public List<Funcionario> mostrarFuncionarios(){
        List<Funcionario> resultado = new ArrayList<>();

        try {
            String sql = "SELECT * FROM FUNCIONARIO";
            ResultSet consulta = this.databaseServices.fazerConsulta(sql);
            Funcionario funcionario;

            while (consulta.next()){
                Integer id = consulta.getInt("ID_FUNCIONARIO");
                String nome = consulta.getString("NOME");
                String cpf = consulta.getString("CPF");
                String email = consulta.getString("EMAIL");
                double salarioBruto = consulta.getDouble("SALARIO_BRUTO");
                double salarioLiquido = consulta.getDouble("SALARIO_LIQUIDO");
                Integer idDepartamento = consulta.getInt("ID_DEPARTAMENTO");

                Departamento departamento = this.relatorioServices.pesquisaDepartamentoIdRelatorioService(idDepartamento);
                funcionario = new Funcionario(id, nome, cpf, email, salarioBruto, salarioLiquido, departamento);
                resultado.add(funcionario);
            }

            if(!resultado.isEmpty()){//se estiver cheia retorna a lista se não, retorna null
                return resultado;
            }else{
                //Nenhum registro encontrado!
                return null;
            }
        }catch (SQLException e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }


}
