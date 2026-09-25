package com.lenguajecafetero.model;

import java.util.Objects;

/**
 * Docente de la academia.
 * Se asigna a matrículas de cursos personalizados.
 * Identificador de negocio: {@link #identificacion}.
 */
public class Profesor {

    private String identificacion;
    private String nombre;
    private Idioma idioma;
    private String telefono;
    private double tarifaPorSesion;

    public Profesor(String identificacion, String nombre, Idioma idioma,
                    String telefono, double tarifaPorSesion) {
        setIdentificacion(identificacion);
        setNombre(nombre);
        setIdioma(idioma);
        setTelefono(telefono);
        setTarifaPorSesion(tarifaPorSesion);
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.isBlank()) {
            throw new IllegalArgumentException("La identificación del profesor es obligatoria");
        }
        this.identificacion = identificacion.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del profesor es obligatorio");
        }
        this.nombre = nombre.trim();
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        if (idioma == null) {
            throw new IllegalArgumentException("El idioma que enseña el profesor es obligatorio");
        }
        this.idioma = idioma;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono != null ? telefono.trim() : null;
    }

    public double getTarifaPorSesion() {
        return tarifaPorSesion;
    }

    public void setTarifaPorSesion(double tarifaPorSesion) {
        if (tarifaPorSesion < 0) {
            throw new IllegalArgumentException("La tarifa por sesión no puede ser negativa");
        }
        this.tarifaPorSesion = tarifaPorSesion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profesor that)) {
            return false;
        }
        return Objects.equals(identificacion, that.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion);
    }

    @Override
    public String toString() {
        return nombre + " (" + identificacion + ") — " + idioma;
    }
}
