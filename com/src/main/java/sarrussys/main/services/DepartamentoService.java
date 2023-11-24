package sarrussys.main.services;

import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.repository.DepartamentoRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DepartamentoService {
    private DepartamentoRepository departamentoRepository;
    private RelatorioServices relatorioServices;

    public DepartamentoService(ConexaoMongoDB conexao){
        this.departamentoRepository = new DepartamentoRepository(conexao);
        this.relatorioServices = new RelatorioServices(conexao);
    }

    public boolean cadastrarDepartamento(Departamento novoDepartamento){
        try {
            departamentoRepository.insert(novoDepartamento);
            return true;
        }catch (Exception e){
            System.out.println("[DepartamentoServices] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }


    public List<Departamento> mostraDepartamentos(){
        try {
            return departamentoRepository.buscarDepartamentos();
        }catch (Exception e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    public Departamento pesquisarDepartamentoPorNome(String nome) {
        try {
            return departamentoRepository.buscarDepartamentoPorNome(nome);
        } catch (Exception e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
    }

    public Departamento pesquisaDepartamentoID(Integer id){
        try{
            return departamentoRepository.buscarDepartamentoPorId(id);
        }catch (Exception e){
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
        }
        return null;
    }

    public boolean deletarDepartamento(Departamento departamento) {
        try {
            departamentoRepository.delete(departamento);
            return true;
        }catch (Exception e) {
            System.out.println("[DepartamentoServices] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }
    
    public boolean atualizaDepartamento(Departamento departamento) {
        try {
            departamentoRepository.update(departamento);
            return true;
        } catch (Exception e) {
            System.out.println("[DepartamentoServices] Ocorreu um erro inesperado: /n"+e.getMessage());
            return false;
        }
    }
}
