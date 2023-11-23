package sarrussys.main.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import oracle.jdbc.pool.OracleDataSource;
import org.bson.Document;
import org.bson.json.JsonObject;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;
import sarrussys.main.repository.DepartamentoRepository;
import sarrussys.main.repository.FuncionarioRepository;


import java.sql.ResultSet;
import java.sql.SQLException;;
import java.util.ArrayList;
import java.util.List;

public class RelatorioServices {
    private DepartamentoRepository departamentoRepository;
    private FuncionarioRepository funcionarioRepository;

    //construtor
    public RelatorioServices(ConexaoMongoDB conexao){
        this.departamentoRepository = new DepartamentoRepository(conexao);
        this.funcionarioRepository = new FuncionarioRepository(conexao);
    }

    //relatorio que retorna cada departamento e o numero de funcionarios respectavamente
    public List<String> relatorioDepartamentoNumFuncionarios(){
        List<String> resultado = new ArrayList<>();
        try {
            List<Departamento> departamentos = departamentoRepository.buscarDepartamento();
            List<Funcionario> funcionarios = funcionarioRepository.buscarFuncionarios();

            for (Departamento departamento : departamentos) {
                int count = 0;
                for (Funcionario funcionario: funcionarios) {
                    if (funcionario.getNomeDepartamento().equalsIgnoreCase(departamento.getNomeDepartamento())) {
                        count++;
                    }
                }
                resultado.add(departamento.getNomeDepartamento());
                resultado.add(String.valueOf(count));
            }

            return resultado;
        }catch (Exception e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    //relatorio que retorna os funcionarios e seu respectivo departamento
    public List<Funcionario> relatorioFuncionarioDepartamento(){
        try {
            return funcionarioRepository.buscarFuncionarios();
        }catch (Exception e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    //relatorio que retorna os departamentos e o seu respectivo chefe
    public List<String> relatorioDepartamentoChefe(){
        List<String> resultado = new ArrayList<>();

        try {
            ResultSet consulta = this.databaseServices.fazerConsulta("SELECT DEPARTAMENTO.NOME AS Nome_Departamento, FUNCIONARIO.NOME AS Nome_Chefe\n" +
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
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    //metodo que faz consulta sql e retorna a quantidade de departamentos cadastrados no banco
    public int contarDepartamentosService(){
        int totalDepartamentos = 0;

        try {//o ponto e virgula no final do script select count estava dando erro na consulta
            ResultSet consulta = this.databaseServices.fazerConsulta("SELECT COUNT(1) total_departamento FROM DEPARTAMENTO");
            if (consulta.next()) {
                totalDepartamentos = consulta.getInt("total_departamento");
            }
        } catch (SQLException e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return totalDepartamentos;
    }

    //metodo que faz consulta sql e retorna a quantidade de funcionarios cadastrados no banco
    public int contarFuncionariosServices(){
        int totalFuncionarios = 0;
        try {
            ResultSet consulta = this.databaseServices.fazerConsulta("SELECT COUNT(1) total_funcionario FROM FUNCIONARIO");
            if (consulta.next()) {
                totalFuncionarios = consulta.getInt("total_funcionario");
            }
        } catch (SQLException e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return totalFuncionarios;
    }

    //departamento service
    public Departamento pesquisaDepartamentoIdRelatorioService(Integer id){
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
                Funcionario chefeDepartamento = pesquisaFuncionarioIdRelatorioService(idChfe);
                departamento.setChefeDepartamento(chefeDepartamento);
                return departamento;
            }

        }catch (SQLException e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return null;
    }


    //funcionario service
    public Funcionario pesquisaFuncionarioIdRelatorioService(Integer id){
        Funcionario funcionario = new Funcionario();

        try{
            String sql = "SELECT *\n" +
                    "FROM FUNCIONARIO\n" +
                    "WHERE ID_FUNCIONARIO = "+id;
            ResultSet consulta = this.databaseServices.fazerConsulta(sql);

            if (consulta.next()){
                String nome = consulta.getString("NOME");
                String cpf = consulta.getString("CPF");
                String email = consulta.getString("EMAIL");
                double salarioBruto = consulta.getDouble("SALARIO_BRUTO");
                double salarioLiquido = consulta.getDouble("SALARIO_LIQUIDO");
                Integer idDepartamento = consulta.getInt("ID_DEPARTAMENTO");
                funcionario.setIdFuncionario(id);
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setEmail(email);
                funcionario.setSalarioBruto(salarioBruto);
                funcionario.setSalarioLiquido(salarioLiquido);
                return funcionario;
            }

        }catch (SQLException e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return null;
    }

}
