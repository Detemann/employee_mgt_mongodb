package sarrussys.main.services;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.model.Funcionario;
import sarrussys.main.services.database.DatabaseServices;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioService {
    private DatabaseServices databaseServices;

    public FuncionarioService(OracleDataSource conexao){
        this.databaseServices = new DatabaseServices(conexao);
    }



    public boolean cadastrarFuncionarioService(Funcionario novoFuncionario){
        try {
            String sql ="INSERT INTO FUNCIONARIO (NOME, CPF, EMAIL, SALARIO_BRUTO, SALARIO_LIQUIDO, ID_DEPARTAMENTO)\n" +
                    "VALUES" +
                    " ('"+novoFuncionario.getNome()+"', '"+novoFuncionario.getCpf()+"'," +
                    " '"+novoFuncionario.getEmail()+"', "+novoFuncionario.getSalarioBruto()+", "+novoFuncionario.getSalarioLiquido()+"" +
                    ", "+novoFuncionario.getDepartamento().getIdDepartamento()+")";

            return this.databaseServices.cadastrarFuncionario(sql);
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
            System.out.println("[MenuService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        if(quantidadeExistente != 0){
            return true;
        }
       return false;

    }
}
