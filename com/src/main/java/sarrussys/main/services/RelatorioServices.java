package sarrussys.main.services;

import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;
import sarrussys.main.repository.DepartamentoRepository;
import sarrussys.main.repository.FuncionarioRepository;

import java.util.ArrayList;
import java.util.List;

;

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
            List<Departamento> departamentos = departamentoRepository.buscarDepartamentos();
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
            List<Departamento> departamentos = departamentoRepository.buscarDepartamentos();

            for (Departamento departamento : departamentos) {
                resultado.add(departamento.getNomeDepartamento());
                resultado.add(departamento.getNomeChefe());
            }

            return resultado;
        }catch (Exception e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    //metodo que faz consulta sql e retorna a quantidade de departamentos cadastrados no banco
    public int contarDepartamentosService(){
        try {//o ponto e virgula no final do script select count estava dando erro na consulta
            return departamentoRepository.buscarDepartamentos().size();
        } catch (Exception e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return 0;
        }
    }

    //metodo que faz consulta sql e retorna a quantidade de funcionarios cadastrados no banco
    public int contarFuncionariosServices(){
        try {
            return funcionarioRepository.buscarFuncionarios().size();
        } catch (Exception e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return 0;
        }
    }
}
