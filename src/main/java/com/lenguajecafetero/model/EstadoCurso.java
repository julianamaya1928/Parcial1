package com.lenguajecafetero.model;

/**
 * Estado operativo de un curso en la academia.
 * Solo los cursos {@link #ACTIVO} deberían aceptar nuevas matrículas.
 */
public enum EstadoCurso {
    ACTIVO("Activo"),
    SUSPENDIDO("Suspendido"),
    FINALIZADO("Finalizado");

    private final String etiqueta;

    EstadoCurso(String etiqueta) {
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
