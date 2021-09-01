package Locadora;

public class Pessoa {
    private String nome;
    private String cpf;
    private boolean temAlugel = false;
    private boolean estaCadastrado = false;

    public void setEstaCadastrado(boolean estaCadastrado) {
        this.estaCadastrado = estaCadastrado;
    }

    public boolean getEstaCadastrado() {
        return estaCadastrado;
    }

    public void setTemAlugel(boolean temAlugel) {
        this.temAlugel = temAlugel;
    }

    public boolean getTemAlugel() {
        return temAlugel;
    }

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }


}
