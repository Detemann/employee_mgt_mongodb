package sarrussys.main.services;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;
import sarrussys.main.services.database.DatabaseServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepartamentoService {
    private DatabaseServices databaseServices;

    private RelatorioServices relatorioServices;

    public DepartamentoService(OracleDataSource conexao){
        this.databaseServices = new DatabaseServices(conexao);
        this.relatorioServices = new RelatorioServices(conexao);
    }

    //iMPLEMENTAR INSERIR DEPARTAMENTO


    public List<Departamento> mostraDepartamentos(){
        List<Departamento> resultado = new ArrayList<>();
        Departamento departamento;

        try {
            String sql = "SELECT * FROM DEPARTAMENTO";
            ResultSet consulta = this.databaseServices.fazerConsulta(sql);
            while (consulta.next()){
                int id = consulta.getInt("ID_DEPARTAMENTO");
                String nome = consulta.getString("NOME");
                String sigla = consulta.getString("SIGLA");
                Integer idChfe = consulta.getInt("ID_CHEFE");

                if(idChfe != null){
                    Funcionario chefe = this.relatorioServices.pesquisaFuncionarioIdRelatorioService(idChfe);
                    departamento = new Departamento(id, nome, sigla, chefe);
                    resultado.add(departamento);
                }else{
                    // Se não há ID de chefe, crie um funcionário vazio
                    Funcionario chefe = new Funcionario(null,null,null,null,null,null);
                    departamento = new Departamento(id, nome, sigla, chefe);
                    resultado.add(departamento);
                }
            }
            // Verifica se a lista de departamentos não está vazia e a retorna
            if(!resultado.isEmpty()){
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


    public Departamento pesquisaDepartamentoID(Integer id){
        Departamento departamento = new Departamento();

        try{
            String sql = "SELECT *\n" +
                    "FROM DEPARTAMENTO\n" +
                    "WHERE ID_DEPARTAMENTO = "+id;

            ResultSet consulta = this.databaseServices.fazerConsulta(sql);

            if (consulta.next()){
                Integer idDepartamento = consulta.getInt("ID_DEPARTAMENTO");
                String nome = consulta.getString("NOME");
                String sigla = consulta.getString("SIGLA");
                Integer idChfe = consulta.getInt("ID_CHEFE");
                departamento.setIdDepartamento(idDepartamento);
                departamento.setNomeDepartamento(nome);
                departamento.setSigla(sigla);

                // Pesquisa o funcionário correspondente ao chefe do departamento
                Funcionario chefeDepartamento = this.relatorioServices.pesquisaFuncionarioIdRelatorioService(idChfe);
                departamento.setChefeDepartamento(chefeDepartamento);
                return departamento;
            }

        }catch (SQLException e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return null;
    }


}
