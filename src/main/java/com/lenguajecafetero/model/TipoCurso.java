package com.lenguajecafetero.model;

/**
 * Tipos de curso que ofrece la academia.
 * Facilita la creación mediante {@code CursoFactory} (fase 6).
 */
public enum TipoCurso {
    REGULAR("Regular"),
    INTENSIVO("Intensivo"),
    PERSONALIZADO("Personalizado");

    private final String etiqueta;

    TipoCurso(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /** Texto legible para UI y reportes. */
    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
