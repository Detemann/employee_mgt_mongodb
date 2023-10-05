package sarrussys.main.controllers;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;
import sarrussys.main.services.RelatorioServices;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    RelatorioServices relatorioServices;

    public MenuController(OracleDataSource conexao){
        this.relatorioServices = new RelatorioServices(conexao);
    }


    //relatorios INICIO
    public List<String> relatorioDepartamentoNumFuncionariosController(){
        List<String> resultado = relatorioServices.relatorioDepartamentoNumFuncionarios();
        return resultado;
    }

    public List<String> relatorioFuncionarioDepartamentoController(){
        List<String> resultado = relatorioServices.relatorioFuncionarioDepartamento();
        return resultado;
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

    public List<Funcionario> mostrarFuncionarios(){
        return this.relatorioServices.mostrarFuncionarios();
    }

    public List<Departamento> mostrarDepartamentos(){
        return this.relatorioServices.mostraDepartamentos();
    }

    public Funcionario pesquisaFuncionarioID(Integer id){
        return this.relatorioServices.pesquisaFuncionarioID(id);
    }

    public Departamento pesquisaDepartamentoID(Integer id){
        return this.relatorioServices.pesquisaDepartamentoID(id);
    }
    //relatorios FIM



}
