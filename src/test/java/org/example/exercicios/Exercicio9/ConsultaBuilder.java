package org.example.exercicios.Exercicio9;

import org.example.exercicios.Exercicio3_4.Paciente;
import org.example.exercicios.Exercicio5.Consulta;

import java.time.LocalDate;

public class ConsultaBuilder {
    int id;
    Paciente paciente;;
    LocalDate data;
    boolean autorizado;

    public ConsultaBuilder comId(int id){
        this.id = id;
        return this;
    }
    public ConsultaBuilder comPaciente(Paciente paciente){
        this.paciente = paciente;
        return this;
    }
    public ConsultaBuilder comDataAtual(){
        this.data = LocalDate.now();
        return this;
    }
    public ConsultaBuilder foiAutorizado(boolean autorizado){
        this.autorizado = autorizado;
        return this;
    }

    public Consulta build() {
        return new Consulta(id, paciente, data, autorizado);
    }
}
