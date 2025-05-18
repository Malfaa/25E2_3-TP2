package org.example.exercicios.Exercicio5;

//Exercício 5
public interface HistoricoConsultas {
    //guardar dados em uma lista
    void registrarConsulta(Consulta consulta);
    //resgatar consultas
    void buscarConsultas(Consulta consulta);

}
