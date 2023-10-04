package sarrussys.main.controllers;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.services.RelatorioServices;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    RelatorioServices relatorioServices;

    public MenuController(OracleDataSource conexao){
        this.relatorioServices = new RelatorioServices(conexao);
    }



    public List<String> relatorio1(){
        List<String> resultado = relatorioServices.relatorio1Service();
        return resultado;
    }

    public List<String> relatorio2(){
        List<String> resultado = relatorioServices.relatorio2Service();
        return resultado;
    }

    public List<String> relatorio3(){
        List<String> resultado = relatorioServices.relatorio3Service();
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


}
