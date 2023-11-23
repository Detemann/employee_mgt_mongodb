package sarrussys.main.controllers;

import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Funcionario;
import sarrussys.main.services.FuncionarioService;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private FuncionarioService funcionarioService;

    public FuncionarioController(ConexaoMongoDB conexao){
        this.funcionarioService = new FuncionarioService(conexao);
    }



    public boolean cadastrarFuncionarioController(Funcionario novoFuncionario){
        return funcionarioService.cadastrarFuncionarioService(novoFuncionario);
    }

    public boolean funcionarioExisteController(String cpf){
        return this.funcionarioService.funcionaroExisteService(cpf);
    }

    public Funcionario pesquisaFuncionarioID(Integer id){
        return this.funcionarioService.pesquisaFuncionarioID(id);
    }

    public List<Funcionario> mostrarFuncionarios(){
        List<Funcionario> resultado = new ArrayList<>();
        resultado = this.funcionarioService.mostrarFuncionarios();
        return resultado;
    }

    public boolean deletarFuncionario(Funcionario funcionario){
        return this.funcionarioService.deletarFuncionario(funcionario);
    }

    public boolean verificaRelacionamentFuncionarioxDepartamento(Integer idFuncionario) {
        return this.funcionarioService.verificaRelacionamentFuncionarioxDepartamento(idFuncionario);
    }

    public boolean removeDepartamentodoFuncionario(Funcionario funcionario) {
        return this.funcionarioService.removeDepartamentodoFuncionario(funcionario);
    }

    public boolean atualizaFuncionario(Funcionario funcionario) {
        return this.funcionarioService.atualizaFuncionario(funcionario);
    }
}