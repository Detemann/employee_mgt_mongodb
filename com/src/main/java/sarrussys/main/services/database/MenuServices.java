package sarrussys.main.services.database;

import oracle.jdbc.pool.OracleDataSource;


import java.sql.ResultSet;
import java.sql.SQLException;;
import java.util.ArrayList;
import java.util.List;

public class MenuServices {
    private DatabaseServices servicosBanco;

    public MenuServices(OracleDataSource conexao){
        this.servicosBanco = new DatabaseServices(conexao);
    }

    //relatorio que retorna cada departamento e o numero de funcionarios respectavamente
    public List<String> relatorio1() throws SQLException{
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

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    //relatorio que retorna os funcionarios e o departamento de cada um
    public List<String> relatorio2() throws SQLException{
        List<String> resultado = new ArrayList<>();

        try {
            ResultSet consulta = this.servicosBanco.fazerConsulta("SELECT FUNCIONARIO.NOME AS Nome_Funcionario, DEPARTAMENTO.NOME AS Nome_Departamento\n" +
                    "FROM FUNCIONARIO\n" +
                    "LEFT JOIN DEPARTAMENTO ON FUNCIONARIO.ID_DEPARTAMENTO = DEPARTAMENTO.ID_DEPARTAMENTO");
            while(consulta.next()) {
                //resultado.add("Nome: ");
                resultado.add(consulta.getString("NOME_FUNCIONARIO"));
                //resultado.add("Departamento: ");
                resultado.add(consulta.getString("NOME_DEPARTAMENTO"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }


    public int contarDepartamentosService() throws SQLException {
        int totalDepartamentos = 0;

        try {//o ponto e virgula no final do script select count estava dando erro na consulta
            ResultSet consulta = this.servicosBanco.fazerConsulta("SELECT COUNT(1) total_departamento FROM DEPARTAMENTO");

            if (consulta.next()) {
                totalDepartamentos = consulta.getInt("total_departamento");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalDepartamentos;
    }

    public int contarFuncionariosServices() throws SQLException {
        int totalFuncionarios = 0;
        try {
            ResultSet consulta = this.servicosBanco.fazerConsulta("SELECT COUNT(1) total_funcionario FROM FUNCIONARIO");

            if (consulta.next()) {
                totalFuncionarios = consulta.getInt("total_funcionario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalFuncionarios;
    }
}
