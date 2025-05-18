package org.example.exercicios.Exercicio5;

import org.example.exercicios.Exercicio3_4.Paciente;

import java.time.LocalDate;

public class Consulta {
    int id;
    Paciente paciente;
    LocalDate data;
    boolean autorizado;

    public Consulta (int id, Paciente paciente, LocalDate data, boolean autorizado){
        this. id = id;
        this.paciente = paciente;
        this.data = data;
        this.autorizado = autorizado;
    }

    public boolean getAutorizado(){
        return autorizado;
    }

    public void setAutorizado(boolean autorizado){
        this.autorizado = autorizado;
    }
}
