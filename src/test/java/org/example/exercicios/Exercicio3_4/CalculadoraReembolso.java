package org.example.exercicios.Exercicio3_4;

//Exercício 3
public class CalculadoraReembolso {
    public double calcularReembolso(double valor, double porcentagem){
        return valor * (porcentagem/100);
    }


    //Exercício 4
    public double calcularReembolso(double valor,  double porcentagem, Paciente paciente){
        System.out.println("Paciente: " + paciente.getNome());
        return valor * (porcentagem/100);
    }
}
