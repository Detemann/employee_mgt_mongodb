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

    public boolean cadastrarDepartamento(Departamento novoDepartamento){
        return this.departamentoService.cadastrarDepartamento(novoDepartamento);
    }


    public List<Departamento> mostraDepartamentos(){
        List<Departamento> resultado = new ArrayList<>();
        resultado = this.departamentoService.mostraDepartamentos();
        return resultado;
    }

    public Departamento pesquisaDepartamentoID(Integer id){
        return this.departamentoService.pesquisaDepartamentoID(id);
    }


    public boolean departamentoExiste(Integer idDepartamento) {
        return this.departamentoService.departamentoExiste(idDepartamento);
    }


    public boolean deletarDepartamento(Departamento departamento) {
        return this.departamentoService.deletarDepartamento(departamento);
    }

    public boolean verificaRelacionamentoDepartamentoxFuncionario(Integer id){
        return this.departamentoService.verificaRelacionamentoDepartamentoxFuncionario(id);
    }


    //altera o ID_FUNCIONARIO do DEPARTAMENTO
    public boolean removeFuncionariosdoDepartamento(Departamento departamento) {
        return this.departamentoService.removeFuncionariosdoDepartamento(departamento);
    }

}
