package br.edu.ifpr.stiehl.kotlin.java;

public class Principal {
    public static void main(String[] args) {
        new Principal().calcularIdade(2019, 1988);
    }

    public int calcularIdade(int anoAtual, int anoNascimento) {
        return anoNascimento - anoAtual;
    }
}

//public class J {
//
//}