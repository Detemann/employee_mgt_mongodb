package sarrussys.main.services;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;
import sarrussys.main.repository.DepartamentoRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepartamentoService {
    private DepartamentoRepository databaseServices;
    private RelatorioServices relatorioServices;

    public DepartamentoService(ConexaoMongoDB conexao){
        this.databaseServices = new DepartamentoRepository(conexao);
        this.relatorioServices = new RelatorioServices(conexao);
    }

    public boolean cadastrarDepartamento(Departamento novoDepartamento){
        try{
            String nome = novoDepartamento.getNomeDepartamento();
            String sigla = novoDepartamento.getSigla();
            Integer chefeID = null;
            Funcionario funcionarioChefe = novoDepartamento.getChefeDepartamento();
            if(funcionarioChefe != null){
                chefeID = funcionarioChefe.getIdFuncionario();
            }

            String sql = "INSERT INTO DEPARTAMENTO (NOME, SIGLA, ID_CHEFE)\n" +
                    "VALUES ('"+nome+"', '"+sigla+"', "+chefeID+")";

            int resultado = this.databaseServices.fazerUpdate(sql);
            if(resultado == 0){
                return false;
            }else{
                return true;
            }

        }catch (Exception e){
            System.out.println("[DepartamentoServices] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }


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
                    departamento = new Departamento(id, nome, sigla, chefe, numFuncionarios);
                    resultado.add(departamento);
                }else{
                    // Se não há ID de chefe, crie um funcionário vazio
                    Funcionario chefe = new Funcionario(null,null,null,null,null,null);
                    departamento = new Departamento(id, nome, sigla, chefe, numFuncionarios);
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


    public boolean departamentoExiste(Integer idDepartamento) {
        int quantidadeExistente = 0;
        try{
            String sql = "SELECT COUNT(*) AS CONTAGEM\n" +
                    "FROM DEPARTAMENTO\n" +
                    "WHERE ID_DEPARTAMENTO = '"+idDepartamento+"'";

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

    public boolean deletarDepartamento(Departamento departamento) {
        try {
            String sql = "DELETE FROM DEPARTAMENTO\n" +
                    "WHERE ID_DEPARTAMENTO = "+departamento.getIdDepartamento();
            int resultado = this.databaseServices.fazerUpdate(sql);

            if(resultado == 0){
                return false;
            }else {
                return true;
            }

        }catch (Exception e) {
            System.out.println("[DepartamentoServices] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }
    
    public boolean verificaRelacionamentoDepartamentoxFuncionario(Integer idDepartamento){
        int relacionamentos = 0;
        try{
            String sql = "SELECT COUNT(1) TOTAL_DEPARTAMENTO FROM FUNCIONARIO \n" +
                    "WHERE ID_DEPARTAMENTO = "+idDepartamento;

            ResultSet consulta = this.databaseServices.fazerConsulta(sql);
            if(consulta.next()){
                relacionamentos = consulta.getInt("TOTAL_DEPARTAMENTO");
            }
        }catch (SQLException e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }

        if(relacionamentos != 0){
            return true;
        }
        return false;
    }

    public boolean removeFuncionariosdoDepartamento(Departamento departamento) {
        try {
            String sql = "UPDATE FUNCIONARIO\n" +
                    "SET ID_DEPARTAMENTO = NULL\n" +
                    "WHERE ID_DEPARTAMENTO ="+departamento.getIdDepartamento();

            int resultado = this.databaseServices.fazerUpdate(sql);

            if(resultado == 0){
                return false;
            }else {
                return true;
            }

        }catch (Exception e) {
            System.out.println("[DepartamentoServices] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }
    
    public boolean atualizaDepartamento(Departamento departamento) {
        try {
            String idChefe = departamento.getChefeDepartamento() != null ? departamento.getChefeDepartamento().getIdFuncionario().toString() : "NULL";
            String sql = "UPDATE DEPARTAMENTO d\n" +
                    "SET NOME= '"+departamento.getNomeDepartamento()+"', SIGLA= '"+departamento.getSigla()+"', ID_CHEFE= "+ idChefe +
                    "WHERE d.ID_DEPARTAMENTO = " + departamento.getIdDepartamento();

            int resultado = this.databaseServices.fazerUpdate(sql);
            return resultado != 0;
        } catch (Exception e) {
            System.out.println("[DepartamentoServices] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }
}
