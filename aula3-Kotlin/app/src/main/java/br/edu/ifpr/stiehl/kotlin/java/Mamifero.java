package br.edu.ifpr.stiehl.kotlin.java;

public class Mamifero extends Animal {
    private String cpf;

    public Mamifero(String nome, String raca, String cpf) {
        super(nome, raca);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
