package sarrussys.main.controllers;

import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Funcionario;
import sarrussys.main.services.RelatorioServices;

import java.util.List;

public class MenuController {
    RelatorioServices relatorioServices;

    public MenuController(ConexaoMongoDB conexao){
        this.relatorioServices = new RelatorioServices(conexao);
    }


    //relatorios INICIO
    public List<String> relatorioDepartamentoNumFuncionariosController(){
        return relatorioServices.relatorioDepartamentoNumFuncionarios();
    }

    public List<Funcionario> relatorioFuncionarioDepartamentoController(){
        return relatorioServices.relatorioFuncionarioDepartamento();
    }

    public List<String> relatorioDepartamentoChefeController(){
        return relatorioServices.relatorioDepartamentoChefe();
    }

    public int contarFuncionarios(){
        return this.relatorioServices.contarFuncionariosServices();
    }
    public int contarDepartamentos(){
        return this.relatorioServices.contarDepartamentosService();
    }
}
