package sarrussys.main.services;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.services.database.DatabaseServices;


import java.sql.ResultSet;
import java.sql.SQLException;;
import java.util.ArrayList;
import java.util.List;

public class RelatorioServices {
    private DatabaseServices servicosBanco;

    //construtor
    public RelatorioServices(OracleDataSource conexao){
        this.servicosBanco = new DatabaseServices(conexao);
    }

    //relatorio que retorna cada departamento e o numero de funcionarios respectavamente
    public List<String> relatorio1Service(){
        List<String> resultado = new ArrayList<>();
        try {
            ResultSet consulta = this.servicosBanco.fazerConsulta("SELECT DEPARTAMENTO.NOME AS Nome_Departamento, COUNT(FUNCIONARIO.ID_FUNCIONARIO) AS Numero_Funcionarios\n" +
                    "FROM DEPARTAMENTO\n" +
                    "LEFT JOIN FUNCIONARIO ON DEPARTAMENTO.ID_DEPARTAMENTO = FUNCIONARIO.ID_DEPARTAMENTO\n" +
                    "GROUP BY DEPARTAMENTO.NOME");
            while(consulta.next()) {
                resultado.add(consulta.getString("NOME_DEPARTAMENTO"));
                resultado.add(consulta.getString("NUMERO_FUNCIONARIOS"));
            }
            if(!resultado.isEmpty()){ //se estiver cheia retorna a lista se não, retorna null
                return resultado;
            }else{
                //Nenhum registro encontrado!
                return null;
            }
        }catch (SQLException e) {
            System.out.println("[MenuService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    //relatorio que retorna os funcionarios e seu respectivo departamento
    public List<String> relatorio2Service(){
        List<String> resultado = new ArrayList<>();

        try {
            ResultSet consulta = this.servicosBanco.fazerConsulta("SELECT FUNCIONARIO.NOME AS Nome_Funcionario, DEPARTAMENTO.NOME AS Nome_Departamento\n" +
                    "FROM FUNCIONARIO\n" +
                    "LEFT JOIN DEPARTAMENTO ON FUNCIONARIO.ID_DEPARTAMENTO = DEPARTAMENTO.ID_DEPARTAMENTO");
            while(consulta.next()) {
                resultado.add(consulta.getString("NOME_FUNCIONARIO"));
                resultado.add(consulta.getString("NOME_DEPARTAMENTO"));
            }
            if(!resultado.isEmpty()){//se estiver cheia retorna a lista se não, retorna null
                return resultado;
            }else{
                //Nenhum registro encontrado!
                return null;
            }
        }catch (SQLException e) {
            System.out.println("[MenuService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    public List<String> relatorio3Service(){
        List<String> resultado = new ArrayList<>();

        try {
            ResultSet consulta = this.servicosBanco.fazerConsulta("SELECT DEPARTAMENTO.NOME AS Nome_Departamento, FUNCIONARIO.NOME AS Nome_Chefe\n" +
                    "FROM DEPARTAMENTO\n" +
                    "LEFT JOIN FUNCIONARIO ON DEPARTAMENTO.ID_CHEFE = FUNCIONARIO.ID_FUNCIONARIO");
            while(consulta.next()) {
                resultado.add(consulta.getString("NOME_DEPARTAMENTO"));
                resultado.add(consulta.getString("NOME_CHEFE"));
            }
            if(!resultado.isEmpty()){//se estiver cheia retorna a lista se não, retorna null
                return resultado;
            }else{
                //Nenhum registro encontrado!
                return null;
            }
        }catch (SQLException e) {
            System.out.println("[MenuService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    //metodo que faz consulta sql e retorna a quantidade de departamentos cadastrados no banco
    public int contarDepartamentosService(){
        int totalDepartamentos = 0;

        try {//o ponto e virgula no final do script select count estava dando erro na consulta
            ResultSet consulta = this.servicosBanco.fazerConsulta("SELECT COUNT(1) total_departamento FROM DEPARTAMENTO");
            if (consulta.next()) {
                totalDepartamentos = consulta.getInt("total_departamento");
            }
        } catch (SQLException e) {
            System.out.println("[MenuService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return totalDepartamentos;
    }

    //metodo que faz consulta sql e retorna a quantidade de funcionarios cadastrados no banco
    public int contarFuncionariosServices(){
        int totalFuncionarios = 0;
        try {
            ResultSet consulta = this.servicosBanco.fazerConsulta("SELECT COUNT(1) total_funcionario FROM FUNCIONARIO");
            if (consulta.next()) {
                totalFuncionarios = consulta.getInt("total_funcionario");
            }
        } catch (SQLException e) {
            System.out.println("[MenuService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return totalFuncionarios;
    }
}
