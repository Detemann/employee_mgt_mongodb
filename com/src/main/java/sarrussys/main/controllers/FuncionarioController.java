package sarrussys.main.controllers;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.model.Funcionario;
import sarrussys.main.services.FuncionarioService;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private FuncionarioService funcionarioService;

    public FuncionarioController(OracleDataSource conexao){
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

}
