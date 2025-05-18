package org.example.exercicios.Exercicio5;

import java.util.ArrayList;
import java.util.List;

public class VersaoFake implements HistoricoConsultas {

    private final List<Consulta> consultas = new ArrayList<>();

    @Override
    public void registrarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    @Override
    public void buscarConsultas(Consulta consulta) {
        consultas.forEach(System.out::println);
    }
}