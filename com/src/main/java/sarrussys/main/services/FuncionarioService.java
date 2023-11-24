package sarrussys.main.services;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;
import sarrussys.main.repository.FuncionarioRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;
    private RelatorioServices relatorioServices;

    public FuncionarioService(ConexaoMongoDB conexao){
        this.funcionarioRepository = new FuncionarioRepository(conexao);
        this.relatorioServices = new RelatorioServices(conexao);
    }



    public boolean cadastrarFuncionarioService(Funcionario novoFuncionario){
        try {
            funcionarioRepository.save(novoFuncionario);
            return true;
        }catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }

    public boolean funcionaroExisteService(String cpf) {
        int quantidadeExistente = 0;
        try{

        }catch (SQLException e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
    }

    public Funcionario pesquisaFuncionarioID(Integer id){
        Funcionario funcionario = new Funcionario();

        try{

        }catch (SQLException e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return null;
    }

    public List<Funcionario> mostrarFuncionarios(){
        List<Funcionario> resultado = new ArrayList<>();

        try {

        }catch (SQLException e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    public boolean deletarFuncionario(Funcionario funcionario){
        try {

        }catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }

    public boolean verificaRelacionamentFuncionarioxDepartamento(Integer idFuncionario) {
        int relacionamentos = 0;
        try{

        }catch (SQLException e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: \n"+e.getMessage());
        }

        if(relacionamentos != 0){
            return true;
        }
        return false;
    }

    public boolean removeDepartamentodoFuncionario(Funcionario funcionario) {
        try {
            String sql = "UPDATE DEPARTAMENTO\n" +
                    "SET ID_CHEFE = NULL\n" +
                    "WHERE ID_CHEFE ="+funcionario.getIdFuncionario();

            int resultado = this.databaseServices.fazerUpdate(sql);

            if(resultado == 0){
                return false;
            }else {
                return true;
            }

        }catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }
    
    public boolean atualizaFuncionario(Funcionario funcionario) {
        try {
            String idDepartamento = funcionario.getDepartamento() != null ? funcionario.getDepartamento().getIdDepartamento().toString() : "NULL";
            String sql = "UPDATE FUNCIONARIO f\n"+
                    "SET NOME= '"+funcionario.getNome()+"', CPF= '"+funcionario.getCpf()+"', EMAIL= '"+funcionario.getEmail()+"', " +
                    "SALARIO_BRUTO= "+funcionario.getSalarioBruto()+", SALARIO_LIQUIDO= "+funcionario.getSalarioLiquido()+", " +
                    "ID_DEPARTAMENTO= "+ idDepartamento +
                    "WHERE f.ID_FUNCIONARIO = " + funcionario.getIdFuncionario();

            int resultado = this.databaseServices.fazerUpdate(sql);
            return resultado != 0;
        } catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }

    public Boolean verificaRelacionamentoDepartamentoxFuncionario(String nomeDepartamento) {
        try {
            List<Funcionario> funcionarios = funcionarioRepository.buscarFuncionarios();

             return funcionarios
                     .stream()
                     .anyMatch(funcionario -> funcionario.getNomeDepartamento().equalsIgnoreCase(nomeDepartamento));
        } catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }
}
