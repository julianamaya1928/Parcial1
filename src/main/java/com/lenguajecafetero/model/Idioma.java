package com.lenguajecafetero.model;

/**
 * Idiomas que ofrece la academia LenguajeCafetero.
 */
public enum Idioma {
    INGLES("Inglés"),
    FRANCES("Francés"),
    PORTUGUES("Portugués");

    private final String etiqueta;

    Idioma(String etiqueta) {
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
