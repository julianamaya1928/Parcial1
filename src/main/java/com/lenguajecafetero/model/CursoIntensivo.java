package com.lenguajecafetero.model;

import java.util.List;

/**
 * Curso de ritmo concentrado.
 * Costo: {@code valorMensual × meses × factorIntensidad}.
 * <p>
 * El factor (&gt; 0) refleja la mayor carga académica; por defecto 1.25.
 * </p>
 */
public class CursoIntensivo extends Curso {

    public static final double FACTOR_POR_DEFECTO = 1.25;

    private double factorIntensidad;

    public CursoIntensivo(String codigo, String nombre, Idioma idioma, String descripcion,
                          int duracionMeses, double valorMensual, EstadoCurso estado,
                          List<String> beneficios, double factorIntensidad) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado, beneficios);
        setFactorIntensidad(factorIntensidad);
    }

    public CursoIntensivo(String codigo, String nombre, Idioma idioma, String descripcion,
                          int duracionMeses, double valorMensual, EstadoCurso estado,
                          List<String> beneficios) {
        this(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado,
                beneficios, FACTOR_POR_DEFECTO);
    }

    @Override
    public double calcularCosto(int meses) {
        validarMeses(meses);
        return getValorMensual() * meses * factorIntensidad;
    }

    @Override
    public TipoCurso getTipo() {
        return TipoCurso.INTENSIVO;
    }

    public double getFactorIntensidad() {
        return factorIntensidad;
    }

    public void setFactorIntensidad(double factorIntensidad) {
        if (factorIntensidad <= 0) {
            throw new IllegalArgumentException("El factor de intensidad debe ser mayor que cero");
        }
        this.factorIntensidad = factorIntensidad;
    }
}
