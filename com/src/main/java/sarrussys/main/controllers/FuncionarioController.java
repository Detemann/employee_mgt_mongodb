package sarrussys.main.controllers;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.model.Funcionario;
import sarrussys.main.services.FuncionarioService;

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
}
