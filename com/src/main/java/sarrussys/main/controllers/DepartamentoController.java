package sarrussys.main.controllers;

import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.services.DepartamentoService;

import java.util.List;

public class DepartamentoController {
    private DepartamentoService departamentoService;

    public DepartamentoController(ConexaoMongoDB conexao){
        this.departamentoService = new DepartamentoService(conexao);
    }

    public boolean cadastrarDepartamento(Departamento novoDepartamento){
        return this.departamentoService.cadastrarDepartamento(novoDepartamento);
    }

    public List<Departamento> mostraDepartamentos(){
        return this.departamentoService.mostraDepartamentos();
    }

    public Departamento pesquisaDepartamentoID(Integer id){
        return this.departamentoService.pesquisaDepartamentoID(id);
    }

    public boolean deletarDepartamento(Departamento departamento) {
        return this.departamentoService.deletarDepartamento(departamento);
    }

    public boolean atualizaDepartamento(Departamento departamento) {
        return this.departamentoService.atualizaDepartamento(departamento);
    }

    public Departamento buscarDepartamentoPorNome(String nome) {
        return departamentoService.pesquisarDepartamentoPorNome(nome);
    }
}
