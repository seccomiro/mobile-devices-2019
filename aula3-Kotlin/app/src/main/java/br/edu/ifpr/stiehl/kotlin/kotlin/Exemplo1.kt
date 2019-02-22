package br.edu.ifpr.stiehl.kotlin.kotlin

import br.edu.ifpr.stiehl.kotlin.java.Animal
import java.util.*

var x: Int = 10
var y = 20

fun main(args: Array<String>) {
//    print("Quem é voce? ")
//    val nome = readLine()
//    println("Faaaaaala, $nome")
//
////    nome = ""
//    y = 30
//
//    val pessoa = Pessoa()
//    pessoa.xxx()
//
//    x.toString()
//
//    var mensagem = "Olá amigo: $nome"
//    var outraMensagem =
//"""Olá queridão
//Seja
//bem vindo"""
//
//    println(outraMensagem)

//    val idade = calcularIdade(2019, 1988)
//    val idade = calcularIdade(anoNascimento = 1988, anoAtual = 2019)
    val idade = calcularIdade(1988)

    println("Idade: $idade")

    val c = Calendar.getInstance()

    val animal = Animal("Frida", "Vira-lata")
    animal.nome = "Fritz"
    animal.raca = "Vira-Lata"
    println(animal.nome)
    println(animal.raca)

    val pessoa = Pessoa("Diego", "Curitibano")
//    pessoa.nome = "Diego"
//    pessoa.raca = "Curitibano"
    println(pessoa.nome)
    println(pessoa.raca)

    val outro = PessoaFisica("Diego", "Paranaguaiense", "654892")
    println(outro.nome)
    println(outro.raca)
    println(outro.cpf)
    outro.dizerOla()

    if (pessoa == outro)
        println("São o mesmo")
    else
        println("São diferentes")

//    val resultado = if (pessoa.nome == "Diego")
//        "Você é legal!"
//    else
//       "Você é meio tongo!"

    println(avaliarPessoa(pessoa))



//    peso = 10
//    peso = null

//    if (peso != null)
//    println(peso.toUShort())

    calcularPeso(5)

    var opcao = 1
    var status =
        when (opcao) {
            1 -> "Primeiro"
            2 -> "Segundo"
            3 -> "Terceiro"
            4 -> "Quarto"
            else -> "Não especificado"
        }

    println(status)

    val j = "10"
    j.toInt()

    for (i in 1..10) {
        println(i)
    }


    println("Diego".bagunca())
    println(50.maisUm())

    print(1 + 1)
}

fun calcularPeso(peso: Int?) {
//    if (peso != null)
//        println(peso.toUShort())
    println(peso!!.toUShort())
}

fun avaliarPessoa(pessoa: Pessoa) =
    if (pessoa.nome == "Diego")
        "Você é legal!"
    else
        "Você é meio tongo!"

//fun calcularIdade(anoAtual: Int, anoNascimento: Int): Int {
//    return anoAtual - anoNascimento
//}

//fun calcularIdade(anoAtual: Int, anoNascimento: Int) = anoAtual - anoNascimento

fun calcularIdade(anoNascimento: Int, anoAtual: Int = 2019) = anoAtual - anoNascimento

open class Pessoa(var nome: String, var raca: String) {
    fun dizerOla() {
        println("Olá! Eu sou o ${this.nome}")
    }

    override fun equals(other: Any?): Boolean {
        return this.nome == (other as Pessoa).nome
    }
}

class PessoaFisica(nome: String, raca: String, var cpf: String) : Pessoa(nome, raca)

//class Animal

class X




fun String.bagunca() = "Olha só: $this"

fun Int.maisUm() = this + 1

operator fun Int.plus(valor: Int): Int {
    return this - valor
}