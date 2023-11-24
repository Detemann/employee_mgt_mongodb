package sarrussys.main.services;

import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Funcionario;
import sarrussys.main.repository.FuncionarioRepository;

import java.util.List;

public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(ConexaoMongoDB conexao){
        this.funcionarioRepository = new FuncionarioRepository(conexao);
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

    public Boolean funcionaroExisteService(String cpf) {
        try{
            return funcionarioRepository.buscarFuncionarios().stream()
                    .anyMatch(funcionario -> funcionario.getCpf().equalsIgnoreCase(cpf));
        }catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    public Funcionario pesquisaFuncionarioID(Integer id){
        try{
            return funcionarioRepository.buscarFuncionarioPorId(id);
        }catch (Exception e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return null;
    }

    public List<Funcionario> mostrarFuncionarios(){
        try {
            return funcionarioRepository.buscarFuncionarios();
        }catch (Exception e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    public boolean deletarFuncionario(Funcionario funcionario){
        try {
            funcionarioRepository.delete(funcionario);
            return true;
        }catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }

    public Boolean verificaRelacionamentFuncionarioxDepartamento(Integer idFuncionario) {
        try{
            return funcionarioRepository.buscarFuncionarioPorId(idFuncionario) != null;
        }catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: \n"+e.getMessage());
            return null;
        }
    }

    public boolean removeDepartamentodoFuncionario(Funcionario funcionario) {
        try {
            funcionarioRepository.update(funcionario);
            return true;
        }catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }

    public Boolean removeFuncionariosdoDepartamento(String nomeDepartamento) {
        try {
            List<Funcionario> funcionarios = funcionarioRepository.buscarFuncionarios();
            funcionarios.stream()
                    .filter(funcionario -> funcionario.getNomeDepartamento().equalsIgnoreCase(nomeDepartamento))
                    .forEach(funcionario -> funcionario.setNomeDepartamento(""));
            funcionarioRepository.updateOnMass(funcionarios);
            return true;
        } catch (Exception e) {
            System.out.println("[FuncionarioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }
    
    public boolean atualizaFuncionario(Funcionario funcionario) {
        try {
            funcionarioRepository.update(funcionario);
            return true;
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
