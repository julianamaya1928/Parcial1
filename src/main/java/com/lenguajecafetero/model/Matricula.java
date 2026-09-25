package com.lenguajecafetero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Nucleo comercial: une estudiante + curso + (profesor) + servicios.
 * <pre>
 * valorCurso     = curso.calcularCosto(meses)
 * valorSesiones  = si personalizado y hay profesor: sesiones * tarifa
 * valorServicios = suma de precios de servicios
 * valorFinal     = max(0, valorCurso + valorSesiones + valorServicios - descuento)
 * </pre>
 * Identificador de negocio: {@link #codigo}.
 */
public class Matricula {

    private String codigo;
    private LocalDate fecha;
    private Estudiante estudiante;
    private Curso curso;
    private Profesor profesor;
    private final List<ServicioAdicional> servicios;
    private double descuento;
    private double valorFinal;
    private int mesesContratados;

    public Matricula(String codigo, LocalDate fecha, Estudiante estudiante, Curso curso,
                     int mesesContratados, double descuento) {
        this.servicios = new ArrayList<>();
        setCodigo(codigo);
        setFecha(fecha != null ? fecha : LocalDate.now());
        setEstudiante(estudiante);
        setCurso(curso);
        setMesesContratados(mesesContratados);
        setDescuento(descuento);
        this.profesor = null;
        recalcularValor();
    }

    public double calcularValor() {
        recalcularValor();
        return valorFinal;
    }

    private void recalcularValor() {
        if (curso == null || mesesContratados <= 0) {
            valorFinal = 0;
            return;
        }
        double valorCurso = curso.calcularCosto(mesesContratados);
        double valorSesiones = 0;
        if (curso instanceof CursoPersonalizado personalizado && profesor != null) {
            valorSesiones = personalizado.calcularCostoSesiones(profesor);
        }
        double valorServicios = 0;
        for (ServicioAdicional s : servicios) {
            valorServicios += s.getPrecio();
        }
        double bruto = valorCurso + valorSesiones + valorServicios;
        valorFinal = Math.max(0, bruto - descuento);
    }

    public void agregarServicio(ServicioAdicional servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio adicional no puede ser nulo");
        }
        if (!servicios.contains(servicio)) {
            servicios.add(servicio);
            recalcularValor();
        }
    }

    public boolean quitarServicio(ServicioAdicional servicio) {
        boolean removed = servicios.remove(servicio);
        if (removed) {
            recalcularValor();
        }
        return removed;
    }

    public void asignarProfesor(Profesor profesor) {
        if (profesor == null) {
            this.profesor = null;
            recalcularValor();
            return;
        }
        if (!(curso instanceof CursoPersonalizado)) {
            throw new IllegalStateException(
                    "Solo se puede asignar profesor a matriculas de cursos personalizados");
        }
        this.profesor = profesor;
        recalcularValor();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo de la matricula es obligatorio");
        }
        this.codigo = codigo.trim();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de la matricula es obligatoria");
        }
        this.fecha = fecha;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante de la matricula es obligatorio");
        }
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso de la matricula es obligatorio");
        }
        this.curso = curso;
        if (!(curso instanceof CursoPersonalizado)) {
            this.profesor = null;
        }
        recalcularValor();
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public List<ServicioAdicional> getServicios() {
        return Collections.unmodifiableList(servicios);
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        if (descuento < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo");
        }
        this.descuento = descuento;
        recalcularValor();
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public int getMesesContratados() {
        return mesesContratados;
    }

    public void setMesesContratados(int mesesContratados) {
        if (mesesContratados <= 0) {
            throw new IllegalArgumentException("Los meses contratados deben ser mayores que cero");
        }
        this.mesesContratados = mesesContratados;
        recalcularValor();
    }

    public boolean tieneProfesorAsignado() {
        return profesor != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matricula that)) {
            return false;
        }
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        String prof = profesor != null ? " · prof. " + profesor.getNombre() : "";
        return "Matricula " + codigo + " — " + estudiante.getNombreCompleto()
                + " en " + curso.getNombre() + prof
                + " · $" + String.format("%.2f", valorFinal);
    }
}
