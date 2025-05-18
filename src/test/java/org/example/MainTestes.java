package org.example;

import org.example.exercicios.Exercicio3_4.CalculadoraReembolso;
import org.example.exercicios.Exercicio3_4.Paciente;
import org.example.exercicios.Exercicio5.Consulta;
import org.example.exercicios.Exercicio5.VersaoFake;
import org.example.exercicios.Exercicio6.PlanoSaude;
import org.example.exercicios.Exercicio7.Auditoria;
import org.example.exercicios.Exercicio7.AuditoriaSpy;
import org.example.exercicios.Exercicio8.AutorizadorReembolso;
import org.example.exercicios.Exercicio9.ConsultaBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MainTestes {
    public Paciente paciente;
    public Consulta consulta;
    public CalculadoraReembolso calculadora;
    public VersaoFake versaoFake;
    ConsultaBuilder builder = new ConsultaBuilder();

    Consulta novaConsulta;


    @BeforeEach
    void setup(){
        calculadora = new CalculadoraReembolso();
        versaoFake = new VersaoFake();
        paciente = new Paciente("Jorge", 30);
        consulta = new Consulta(1, paciente, LocalDate.now(), false);
        novaConsulta = builder.comId(2).comPaciente(new Paciente("Cleber", 49)).comDataAtual().foiAutorizado(true).build();
    }

    //Exercício 1
    @Test
    // Este teste faz o reembolso, calculando o valor fixo com o percentual, retornando o valor correto
    public void reembolso_calcularReembolsoValorFixoComPercentual_retornoCorreto(){

        double valor = 200;
        double porcentagem = 70;
        var result = valor * (porcentagem /100);

        assertEquals(140, result);
    }

    //Exercício 2
    @Test
    public void reembolso_calcularReembolsoComSituacaoLimite_retornoCorreto(){

        double valor = 200;
        double porcentagem = 0;
        var result = valor * (porcentagem /100); // atributo com 0% de reembolso
        var segundoResult = valor * ((porcentagem+100) /100); // atributo com 100% de reembolso

        assertEquals(0, result);
        assertEquals(200, segundoResult);
    }

    //Exercicio 4
    @Test
    public void dummy_calculoComDummyNaoImplementado_retornoCorreto() {
        Paciente pacienteDummy = new Paciente("Teste", 30); // Objeto dummy
        double valor = 200.0;
        double porcentagem = 80;
        double valorEsperado = 160.0; // 80% de 200


        double resultado = calculadora.calcularReembolso(valor, porcentagem, pacienteDummy);

        assertEquals(valorEsperado, resultado, "O cálculo do reembolso está incorreto");
    }

    //Exercicio 5
    @Test
    void consulta_registrarConsultaEBuscarConsulta(){
        VersaoFake vf = new VersaoFake();
        vf.registrarConsulta(consulta);
        vf.buscarConsultas(consulta);


        //Exercício 9
        vf.registrarConsulta(novaConsulta);
        vf.buscarConsultas(novaConsulta);
    }

    //-------------------------
    //Exercicio 6
    @Test
    void stub_criarUmStubDiferentesPlanos(){
        //
        PlanoSaude planoSaude50 = new PlanoSaude() {
            @Override
            public double percentualDeCobertura() {
                return 50.0;
            }
        };

        PlanoSaude planoSaude80 = new PlanoSaude(){
            @Override
            public double percentualDeCobertura() {
                return 80.0;
            }
        };

        double valorConsulta = 200.0;
        //Calcula a cobertura utilizando a interface junto com o método de calcular reembolso
        double cobertura50 = calculadora.calcularReembolso(valorConsulta, planoSaude50.percentualDeCobertura());
        double cobertura80 = calculadora.calcularReembolso(valorConsulta, planoSaude80.percentualDeCobertura()) ;


        assertEquals(100.0, cobertura50);
        assertEquals(160.0, cobertura80);
    }

    //Exercicio 7
    //Método para verificar uma consulta baseada na auditoria
    public void registrarConsulta(Consulta consulta, Auditoria auditoria){
        auditoria.verificarRegitroConsulta(consulta);
    }

    @Test
    public void consulta_verificaConsultaFoiRegistrada_retornaTrue() {
        AuditoriaSpy auditoria = new AuditoriaSpy();
        registrarConsulta(consulta, auditoria);
        assertTrue(auditoria.isConsultaRegistrada());

        //Exercicio 9
        registrarConsulta(novaConsulta, auditoria);
        assertTrue(auditoria.isConsultaRegistrada());
    }

    //Exercicio 8
    @Test
    public void auth_simularAutorizadorReembolso() {
        AutorizadorReembolso autorizadorMock = Mockito.mock(AutorizadorReembolso.class);

        //Faz o teste retornando verdadeiro
        Mockito.when(autorizadorMock.autorizarReembolso(consulta)).thenReturn(true);
        assertTrue(autorizadorMock.autorizarReembolso(consulta));

        //Faz o teste retornando falso
        Mockito.when(autorizadorMock.autorizarReembolso(consulta)).thenReturn(false);
        assertFalse(autorizadorMock.autorizarReembolso(consulta));

        //Faz o teste retornando uma exceção
        assertThrows(
                RuntimeException.class,
                () -> {
                    Mockito.when(autorizadorMock.autorizarReembolso(consulta)).thenThrow(new RuntimeException("Reembolso"));
                    autorizadorMock.autorizarReembolso(consulta);
                },
                "Reembolso"
        );

        //Exercicio 9
        Mockito.when(autorizadorMock.autorizarReembolso(novaConsulta)).thenReturn(true);
        assertTrue(autorizadorMock.autorizarReembolso(novaConsulta));

    }

    //Exercicio 10
    public static boolean margemDeErro(double primeiroValor, double segundoValor, double margem) {
        return Math.abs(primeiroValor - segundoValor) <= margem;
    }

    @Test
    public void margem_comparaDoisValoresComMargemDeErro() {
        var valorUm = 10;
        var valorDois = 9.99;
        final double MARGEM_ERRO = 0.01;

        //Verifica a margem de erro entre os valores
        Assertions.assertTrue(margemDeErro(valorUm, valorDois, MARGEM_ERRO),
                "Margem de erro");
    }

    //Exercicio 11
    @Test
    public void reembolso_dentroDoLimite() {

        double valor = 160;
        double porcentagem = 10;
        double valorMaximo = 150;
        double resultado = calculadora.calcularReembolso(valor, porcentagem);

        Assertions.assertTrue(resultado < valorMaximo);
    }

    @Test
    public void reembolso_acimaDoLimite_valorDeveSerLimitadoAMaximo() {
        // Cenário: valor calculado excede o limite
        double valorConsulta = 400;
        double porcentagem = 50;
        double valorMaximo = 150; // Limite máximo

        double resultado = calculadora.calcularReembolso(valorConsulta, porcentagem);

        // Verifica que o valor retornado é limitado a R$ 150
        Assertions.assertTrue(valorMaximo < resultado);
    }

    //Exercício 12

}
