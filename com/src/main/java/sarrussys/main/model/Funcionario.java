package sarrussys.main.model;

public class Funcionario {
    private Integer idFuncionario;
    private String nome;
    private String cpf;
    private String email;
    private Double salarioBruto;
    private Double salarioLiquido;

    public Funcionario(Integer idFuncionario, String nome, String cpf, String email, Double salarioBruto, Double salarioLiquido) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.salarioBruto = salarioBruto;
        this.salarioLiquido = salarioLiquido;
    }

    public Funcionario() {
        this.idFuncionario = null;
        this.nome = null;
        this.cpf = null;
        this.email = null;
        this.salarioBruto = null;
        this.salarioLiquido = null;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(Double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public Double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(Double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }
}
