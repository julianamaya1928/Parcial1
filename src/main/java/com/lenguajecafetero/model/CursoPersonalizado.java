package com.lenguajecafetero.model;

import java.util.List;

/**
 * Curso personalizado con sesiones, nivel de referencia y objetivos del estudiante.
 * <p>
 * Costo base del programa: {@code valorMensual × meses}.
 * El costo de las sesiones con el profesor
 * ({@code cantidadSesiones × tarifaPorSesion}) se aplica en {@code Matricula}
 * al asignar el docente (fase 5), porque la tarifa vive en {@link Profesor}.
 * </p>
 */
public class CursoPersonalizado extends Curso {

    private int cantidadSesiones;
    private NivelIdioma nivelReferencia;
    private String objetivos;

    public CursoPersonalizado(String codigo, String nombre, Idioma idioma, String descripcion,
                              int duracionMeses, double valorMensual, EstadoCurso estado,
                              List<String> beneficios, int cantidadSesiones,
                              NivelIdioma nivelReferencia, String objetivos) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado, beneficios);
        setCantidadSesiones(cantidadSesiones);
        setNivelReferencia(nivelReferencia);
        setObjetivos(objetivos);
    }

    /**
     * Costo base del paquete personalizado por meses contratados.
     * Las sesiones con tarifa de profesor se suman en la matrícula.
     */
    @Override
    public double calcularCosto(int meses) {
        validarMeses(meses);
        return getValorMensual() * meses;
    }

    /**
     * Costo estimado de las sesiones con un profesor dado.
     * Usado por la matrícula al calcular el valor final.
     */
    public double calcularCostoSesiones(Profesor profesor) {
        if (profesor == null) {
            return 0;
        }
        return cantidadSesiones * profesor.getTarifaPorSesion();
    }

    @Override
    public TipoCurso getTipo() {
        return TipoCurso.PERSONALIZADO;
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        if (cantidadSesiones < 0) {
            throw new IllegalArgumentException("La cantidad de sesiones no puede ser negativa");
        }
        this.cantidadSesiones = cantidadSesiones;
    }

    public NivelIdioma getNivelReferencia() {
        return nivelReferencia;
    }

    public void setNivelReferencia(NivelIdioma nivelReferencia) {
        if (nivelReferencia == null) {
            throw new IllegalArgumentException("El nivel de referencia es obligatorio en un curso personalizado");
        }
        this.nivelReferencia = nivelReferencia;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos != null ? objetivos.trim() : null;
    }

    @Override
    public String toString() {
        return super.toString() + " · nivel " + nivelReferencia + " · " + cantidadSesiones + " sesiones";
    }
}
