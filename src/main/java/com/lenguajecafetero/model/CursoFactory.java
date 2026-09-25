package com.lenguajecafetero.model;

import java.util.List;

/**
 * Factory (patrón creacional): crea la subclase correcta de {@link Curso}
 * según el tipo, sin que el cliente instancie Regular/Intensivo/Personalizado a mano.
 */
public final class CursoFactory {

    private CursoFactory() {
        // utilidad estática
    }

    /**
     * Crea un curso según {@link TipoCurso}.
     * Para PERSONALIZADO use {@link #crearPersonalizado}; este método
     * solo acepta REGULAR e INTENSIVO (parámetros comunes).
     */
    public static Curso crear(TipoCurso tipo, String codigo, String nombre, Idioma idioma,
                              String descripcion, int duracionMeses, double valorMensual,
                              EstadoCurso estado, List<String> beneficios) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de curso es obligatorio");
        }
        return switch (tipo) {
            case REGULAR -> crearRegular(codigo, nombre, idioma, descripcion,
                    duracionMeses, valorMensual, estado, beneficios);
            case INTENSIVO -> crearIntensivo(codigo, nombre, idioma, descripcion,
                    duracionMeses, valorMensual, estado, beneficios);
            case PERSONALIZADO -> throw new IllegalArgumentException(
                    "Use crearPersonalizado(...) para cursos personalizados");
        };
    }

    public static CursoRegular crearRegular(String codigo, String nombre, Idioma idioma,
                                            String descripcion, int duracionMeses,
                                            double valorMensual, EstadoCurso estado,
                                            List<String> beneficios) {
        return new CursoRegular(codigo, nombre, idioma, descripcion,
                duracionMeses, valorMensual, estado, beneficios);
    }

    public static CursoIntensivo crearIntensivo(String codigo, String nombre, Idioma idioma,
                                                String descripcion, int duracionMeses,
                                                double valorMensual, EstadoCurso estado,
                                                List<String> beneficios) {
        return new CursoIntensivo(codigo, nombre, idioma, descripcion,
                duracionMeses, valorMensual, estado, beneficios);
    }

    public static CursoIntensivo crearIntensivo(String codigo, String nombre, Idioma idioma,
                                                String descripcion, int duracionMeses,
                                                double valorMensual, EstadoCurso estado,
                                                List<String> beneficios, double factorIntensidad) {
        return new CursoIntensivo(codigo, nombre, idioma, descripcion,
                duracionMeses, valorMensual, estado, beneficios, factorIntensidad);
    }

    public static CursoPersonalizado crearPersonalizado(String codigo, String nombre,
                                                        Idioma idioma, String descripcion,
                                                        int duracionMeses, double valorMensual,
                                                        EstadoCurso estado, List<String> beneficios,
                                                        int cantidadSesiones,
                                                        NivelIdioma nivelReferencia,
                                                        String objetivos) {
        return new CursoPersonalizado(codigo, nombre, idioma, descripcion,
                duracionMeses, valorMensual, estado, beneficios,
                cantidadSesiones, nivelReferencia, objetivos);
    }
}
