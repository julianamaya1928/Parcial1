package com.lenguajecafetero.model;

import java.util.List;

/**
 * Curso de formación estándar.
 * Costo: {@code valorMensual × meses}.
 */
public class CursoRegular extends Curso {

    public CursoRegular(String codigo, String nombre, Idioma idioma, String descripcion,
                        int duracionMeses, double valorMensual, EstadoCurso estado,
                        List<String> beneficios) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado, beneficios);
    }

    @Override
    public double calcularCosto(int meses) {
        validarMeses(meses);
        return getValorMensual() * meses;
    }

    @Override
    public TipoCurso getTipo() {
        return TipoCurso.REGULAR;
    }
}
