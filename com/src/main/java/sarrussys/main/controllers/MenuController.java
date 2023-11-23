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
        List<String> resultado = relatorioServices.relatorioDepartamentoChefe();
        return resultado;
    }

    public int contarFuncionarios(){
        int resultado = this.relatorioServices.contarFuncionariosServices();
        return resultado;
    }
    public int contarDepartamentos(){
        int resultado = this.relatorioServices.contarDepartamentosService();
        return resultado;
    }

    //relatorios FIM
    public Funcionario pesquisaFuncionarioIdRelatorioService(Integer id){
        return this.relatorioServices.pesquisaFuncionarioIdRelatorioService(id);
    }


}
