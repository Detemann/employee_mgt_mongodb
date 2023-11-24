package sarrussys.main.model;

public class Departamento {
    private Integer idDepartamento;
    private String nomeDepartamento;
    private String sigla;
    private String nomeChefe;
    private Integer numFuncionarios;

    public Departamento(Integer idDepartamento, String nomeDepartamento, String sigla, Integer numFuncionarios) {
        this.idDepartamento = idDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.sigla = sigla;
        this.numFuncionarios = numFuncionarios;
    }

    public Departamento() {
        this.idDepartamento = null;
        this.nomeDepartamento = null;
        this.sigla = null;
    }

    public Departamento(String nome, String sigla, String nomeFuncionario) {
        this.nomeDepartamento = nome;
        this.sigla = sigla;
        this.nomeChefe = nomeFuncionario;
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

    public String getNomeChefe() {
        return nomeChefe;
    }

    public void setNomeChefe(String nomeChefe) {
        this.nomeChefe = nomeChefe;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "idFuncionario=" + idDepartamento +
                ", nome='" + nomeDepartamento + '\'' +
                ", sigla='"+ sigla + '\'' +
                ", nomeChefe='"+ nomeChefe + '\'' +
                '}';
    }

    public Integer getNumFuncionarios() {
        return numFuncionarios;
    }

    public void setNumFuncionarios(Integer numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }
}
