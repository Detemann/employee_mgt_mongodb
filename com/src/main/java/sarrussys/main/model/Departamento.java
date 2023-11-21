package sarrussys.main.model;

public class Departamento {
    private Integer idDepartamento;
    private String nomeDepartamento;
    private String sigla;
    private Funcionario chefeDepartamento;

    private String nomeChefe;

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

    public Departamento(String nome, String sigla, Funcionario funcionario) {
        this.nomeDepartamento = nome;
        this.sigla = sigla;
        this.chefeDepartamento = funcionario;
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
        if(this.chefeDepartamento == null){
            return null;
        } else {
            return this.chefeDepartamento;
        }
    }

    public void setChefeDepartamento(Funcionario chefeDepartamento) {
        this.chefeDepartamento = chefeDepartamento;
    }

    public String getNomeChefe() {
        return nomeChefe;
    }

    public void setNomeChefe(String nomeChefe) {
        this.nomeChefe = nomeChefe;
    }
}
