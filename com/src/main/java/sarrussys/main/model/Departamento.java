package sarrussys.main.model;

public class Departamento {
    private Integer idDepartamento;
    private String nomeDepartamento;
    private String sigla;
    private Funcionario chefeDepartamento;

    public Departamento(Integer idDepartamento, String nomeDepartamento, String sigla, Funcionario chefeDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.sigla = sigla;
        this.chefeDepartamento = chefeDepartamento;
    }

    public Departamento() {
        this.idDepartamento = null;
        this.nomeDepartamento = null;
        this.sigla = null;
        this.chefeDepartamento = null;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Funcionario getChefeDepartamento() {
        return chefeDepartamento;
    }

    public void setChefeDepartamento(Funcionario chefeDepartamento) {
        this.chefeDepartamento = chefeDepartamento;
    }
}
