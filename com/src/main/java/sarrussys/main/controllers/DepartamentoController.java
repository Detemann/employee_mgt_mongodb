package sarrussys.main.controllers;

import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;
import sarrussys.main.services.DepartamentoService;


import java.util.ArrayList;
import java.util.List;

public class DepartamentoController {
    private DepartamentoService departamentoService;

    public DepartamentoController(OracleDataSource conexao){
        this.departamentoService = new DepartamentoService(conexao);
    }




    public List<Departamento> mostraDepartamentos(){
        List<Departamento> resultado = new ArrayList<>();
        resultado = this.departamentoService.mostraDepartamentos();
        return resultado;
    }

    public Departamento pesquisaDepartamentoID(Integer id){
        return this.departamentoService.pesquisaDepartamentoID(id);
    }



}
