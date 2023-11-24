package sarrussys.main.services;

import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.repository.DepartamentoRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DepartamentoService {
    private DepartamentoRepository departamentoRepository;
    private RelatorioServices relatorioServices;

    public DepartamentoService(ConexaoMongoDB conexao){
        this.departamentoRepository = new DepartamentoRepository(conexao);
        this.relatorioServices = new RelatorioServices(conexao);
    }

    public boolean cadastrarDepartamento(Departamento novoDepartamento){
        try {
            departamentoRepository.insert(novoDepartamento);
            return true;
        }catch (Exception e){
            System.out.println("[DepartamentoServices] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }


    public List<Departamento> mostraDepartamentos(){
        try {
            return departamentoRepository.buscarDepartamentos();
        }catch (Exception e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    public Departamento pesquisarDepartamentoPorNome(String nome) {
        try {
            return departamentoRepository.buscarDepartamentoPorNome(nome);
        } catch (Exception e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
    }

    public Departamento pesquisaDepartamentoID(Integer id){
        try{
            return departamentoRepository.buscarDepartamentoPorId(id);
        }catch (Exception e){
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
            departamentoRepository.delete(departamento);
            return true;
        }catch (Exception e) {
            System.out.println("[DepartamentoServices] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }

    public boolean removeFuncionariosdoDepartamento(Departamento departamento) {
        try {

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
