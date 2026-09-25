package com.lenguajecafetero.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Definición general de un curso ofrecido por la academia.
 * <p>
 * Clase abstracta: el costo se calcula de forma polimórfica según el tipo
 * ({@link CursoRegular}, {@link CursoIntensivo}, {@link CursoPersonalizado}).
 * Identificador de negocio: {@link #codigo}.
 * </p>
 */
public abstract class Curso {

    private String codigo;
    private String nombre;
    private Idioma idioma;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private EstadoCurso estado;
    private final List<String> beneficios;

    protected Curso(String codigo, String nombre, Idioma idioma, String descripcion,
                    int duracionMeses, double valorMensual, EstadoCurso estado,
                    List<String> beneficios) {
        setCodigo(codigo);
        setNombre(nombre);
        setIdioma(idioma);
        setDescripcion(descripcion);
        setDuracionMeses(duracionMeses);
        setValorMensual(valorMensual);
        setEstado(estado != null ? estado : EstadoCurso.ACTIVO);
        this.beneficios = new ArrayList<>();
        if (beneficios != null) {
            for (String b : beneficios) {
                agregarBeneficio(b);
            }
        }
    }

    /**
     * Calcula el costo del curso para la duración contratada.
     * Cada subclase aplica su propia regla (polimorfismo).
     *
     * @param meses meses contratados (debe ser &gt; 0)
     * @return costo del curso sin servicios ni descuentos
     */
    public abstract double calcularCosto(int meses);

    /** Tipo de curso (útil para factory y UI). */
    public abstract TipoCurso getTipo();

    protected void validarMeses(int meses) {
        if (meses <= 0) {
            throw new IllegalArgumentException("Los meses contratados deben ser mayores que cero");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código del curso es obligatorio");
        }
        this.codigo = codigo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del curso es obligatorio");
        }
        this.nombre = nombre.trim();
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        if (idioma == null) {
            throw new IllegalArgumentException("El idioma del curso es obligatorio");
        }
        this.idioma = idioma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? descripcion.trim() : null;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        if (duracionMeses <= 0) {
            throw new IllegalArgumentException("La duración en meses debe ser mayor que cero");
        }
        this.duracionMeses = duracionMeses;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(double valorMensual) {
        if (valorMensual < 0) {
            throw new IllegalArgumentException("El valor mensual no puede ser negativo");
        }
        this.valorMensual = valorMensual;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoCurso estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado del curso es obligatorio");
        }
        this.estado = estado;
    }

    /** Copia defensiva de la lista de beneficios. */
    public List<String> getBeneficios() {
        return Collections.unmodifiableList(beneficios);
    }

    public void agregarBeneficio(String beneficio) {
        if (beneficio != null && !beneficio.isBlank()) {
            beneficios.add(beneficio.trim());
        }
    }

    public void limpiarBeneficios() {
        beneficios.clear();
    }

    public boolean estaActivo() {
        return estado == EstadoCurso.ACTIVO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Curso that)) {
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
        return nombre + " [" + codigo + "] — " + getTipo() + " / " + idioma + " (" + estado + ")";
    }
}
