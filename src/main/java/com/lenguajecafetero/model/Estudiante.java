package com.lenguajecafetero.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Persona que se forma en la academia.
 * Identificador de negocio: {@link #documento}.
 */
public class Estudiante {

    private String nombreCompleto;
    private String documento;
    private String telefono;
    private String correo;
    private int edad;
    private LocalDate fechaRegistro;

    public Estudiante(String nombreCompleto, String documento, String telefono,
                      String correo, int edad, LocalDate fechaRegistro) {
        setNombreCompleto(nombreCompleto);
        setDocumento(documento);
        setTelefono(telefono);
        setCorreo(correo);
        setEdad(edad);
        setFechaRegistro(fechaRegistro != null ? fechaRegistro : LocalDate.now());
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("El nombre completo del estudiante es obligatorio");
        }
        this.nombreCompleto = nombreCompleto.trim();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("El documento de identidad es obligatorio");
        }
        this.documento = documento.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono != null ? telefono.trim() : null;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo != null ? correo.trim() : null;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException("La edad debe estar entre 0 y 120");
        }
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        if (fechaRegistro == null) {
            throw new IllegalArgumentException("La fecha de registro es obligatoria");
        }
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estudiante that)) {
            return false;
        }
        return Objects.equals(documento, that.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }

    @Override
    public String toString() {
        return nombreCompleto + " (" + documento + ")";
    }
}
