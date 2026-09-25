package com.lenguajecafetero.model;

/**
 * Niveles de referencia del Marco Común Europeo (MCER).
 * Usados en cursos personalizados.
 */
public enum NivelIdioma {
    A1("A1 — Acceso"),
    A2("A2 — Plataforma"),
    B1("B1 — Umbral"),
    B2("B2 — Avanzado"),
    C1("C1 — Dominio operativo eficaz"),
    C2("C2 — Maestría");

    private final String etiqueta;

    NivelIdioma(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /** Texto legible para UI y reportes. */
    public String getEtiqueta() {
        return etiqueta;
    }

    /** Código corto del nivel (A1, B2, …). */
    public String getCodigo() {
        return name();
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
