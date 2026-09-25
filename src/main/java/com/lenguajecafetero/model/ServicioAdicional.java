package com.lenguajecafetero.model;

import java.util.Objects;

/**
 * Servicio extra que un estudiante puede asociar a su matrícula
 * (simulacro, tutoría, material, talleres, etc.).
 * Identificador de negocio: {@link #codigo}.
 */
public class ServicioAdicional {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponibilidad;

    public ServicioAdicional(String codigo, String nombre, String descripcion,
                             double precio, boolean disponibilidad) {
        setCodigo(codigo);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        setDisponibilidad(disponibilidad);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código del servicio es obligatorio");
        }
        this.codigo = codigo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del servicio es obligatorio");
        }
        this.nombre = nombre.trim();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? descripcion.trim() : null;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio del servicio no puede ser negativo");
        }
        this.precio = precio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServicioAdicional that)) {
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
        return nombre + " [" + codigo + "] — $" + precio
                + (disponibilidad ? "" : " (no disponible)");
    }
}
