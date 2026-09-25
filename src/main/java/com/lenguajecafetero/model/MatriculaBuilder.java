package com.lenguajecafetero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder (patrón creacional): construye una {@link Matricula} compleja paso a paso
 * (estudiante, curso, servicios opcionales, descuento, profesor, fecha).
 * <p>
 * Al {@link #build()} se valida lo obligatorio y se calcula el valor final.
 * </p>
 */
public class MatriculaBuilder {

    private String codigo;
    private LocalDate fecha = LocalDate.now();
    private Estudiante estudiante;
    private Curso curso;
    private Profesor profesor;
    private final List<ServicioAdicional> servicios = new ArrayList<>();
    private double descuento;
    private int mesesContratados = 1;

    public MatriculaBuilder conCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public MatriculaBuilder conFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public MatriculaBuilder conEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        return this;
    }

    public MatriculaBuilder conCurso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public MatriculaBuilder conProfesor(Profesor profesor) {
        this.profesor = profesor;
        return this;
    }

    public MatriculaBuilder conServicio(ServicioAdicional servicio) {
        if (servicio != null) {
            this.servicios.add(servicio);
        }
        return this;
    }

    public MatriculaBuilder conServicios(List<ServicioAdicional> servicios) {
        if (servicios != null) {
            this.servicios.addAll(servicios);
        }
        return this;
    }

    public MatriculaBuilder conDescuento(double descuento) {
        this.descuento = descuento;
        return this;
    }

    public MatriculaBuilder conMeses(int mesesContratados) {
        this.mesesContratados = mesesContratados;
        return this;
    }

    /**
     * Construye la matrícula.
     * Requiere: codigo, estudiante, curso y meses &gt; 0.
     * Si hay profesor, el curso debe ser personalizado.
     */
    public Matricula build() {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalStateException("La matricula requiere un codigo");
        }
        if (estudiante == null) {
            throw new IllegalStateException("La matricula requiere un estudiante");
        }
        if (curso == null) {
            throw new IllegalStateException("La matricula requiere un curso");
        }
        if (mesesContratados <= 0) {
            throw new IllegalStateException("Los meses contratados deben ser mayores que cero");
        }

        Matricula matricula = new Matricula(
                codigo,
                fecha != null ? fecha : LocalDate.now(),
                estudiante,
                curso,
                mesesContratados,
                descuento);

        for (ServicioAdicional s : servicios) {
            matricula.agregarServicio(s);
        }

        if (profesor != null) {
            matricula.asignarProfesor(profesor);
        }

        matricula.calcularValor();
        return matricula;
    }
}
