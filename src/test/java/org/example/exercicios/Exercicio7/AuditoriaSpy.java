package org.example.exercicios.Exercicio7;

import org.example.exercicios.Exercicio5.Consulta;

public class AuditoriaSpy implements Auditoria{
    private boolean consultaRegistrada = false;

    @Override
    public void verificarRegitroConsulta(Consulta consulta) {
        consultaRegistrada = true;
    }
    public boolean isConsultaRegistrada() {
        return consultaRegistrada;
    }
}
