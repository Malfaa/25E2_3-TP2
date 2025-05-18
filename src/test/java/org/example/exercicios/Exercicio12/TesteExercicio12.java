package org.example.exercicios.Exercicio12;

import org.example.exercicios.Exercicio3_4.CalculadoraReembolso;
import org.example.exercicios.Exercicio3_4.Paciente;
import org.example.exercicios.Exercicio5.Consulta;
import org.example.exercicios.Exercicio6.PlanoSaude;
import org.example.exercicios.Exercicio8.AutorizadorReembolso;
import org.example.exercicios.Exercicio9.ConsultaBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TesteExercicio12 {
    public CalculadoraReembolso calculadora = new CalculadoraReembolso();;
    public Paciente paciente;
    public Consulta consulta;
    ConsultaBuilder builder = new ConsultaBuilder();

    @BeforeEach
    void setup(){
        paciente = new Paciente("Jeferson", 80);
        //Helper criação de consultas
        consulta = builder.comId(10).comPaciente(paciente).comDataAtual().foiAutorizado(true).build();
    }

    @Test
    public void teste_testeGeralTestandoTodosOsComponentes(){
        //Mock AutorizadorReembolso
        AutorizadorReembolso autorizadorMock = Mockito.mock(AutorizadorReembolso.class);
        Mockito.when(autorizadorMock.autorizarReembolso(consulta)).thenReturn(true);

        //Stub para PlanoSaude
        PlanoSaude plano70 = new PlanoSaude() {
            @Override
            public double percentualDeCobertura() {
                return 70;
            }
        };

        var valorDaConsulta = 300;

        if(autorizadorMock.autorizarReembolso(consulta)){
            var resultado = calculadora.calcularReembolso(valorDaConsulta, plano70.percentualDeCobertura(), paciente);
            System.out.println(resultado);
            Assertions.assertEquals(210, resultado);
        }else{
            Assertions.fail("O reembolso não foi autorizado");
        }


    }
}
