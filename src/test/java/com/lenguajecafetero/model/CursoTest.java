package com.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CursoTest {

    @Test
    void regularCalculaValorMensualPorMeses() {
        CursoRegular curso = CursoFactory.crearRegular(
                "R1", "Ingles B1", Idioma.INGLES, "desc",
                6, 100.0, EstadoCurso.ACTIVO, List.of("plataforma"));

        assertEquals(300.0, curso.calcularCosto(3), 0.001);
        assertEquals(TipoCurso.REGULAR, curso.getTipo());
    }

    @Test
    void intensivoAplicaFactor() {
        CursoIntensivo curso = CursoFactory.crearIntensivo(
                "I1", "Frances Int", Idioma.FRANCES, "desc",
                3, 100.0, EstadoCurso.ACTIVO, List.of(), 1.5);

        assertEquals(300.0, curso.calcularCosto(2), 0.001); // 100 * 2 * 1.5
    }

    @Test
    void personalizadoCalculaBaseMensual() {
        CursoPersonalizado curso = CursoFactory.crearPersonalizado(
                "P1", "Port personal", Idioma.PORTUGUES, "desc",
                4, 80.0, EstadoCurso.ACTIVO, List.of(),
                10, NivelIdioma.B1, "Conversacion");

        assertEquals(160.0, curso.calcularCosto(2), 0.001);
    }

    @Test
    void personalizadoCalculaCostoSesionesConProfesor() {
        CursoPersonalizado curso = CursoFactory.crearPersonalizado(
                "P2", "Pers", Idioma.INGLES, "d",
                2, 50.0, EstadoCurso.ACTIVO, List.of(),
                5, NivelIdioma.A2, "obj");
        Profesor profe = new Profesor("PR1", "Luis", Idioma.INGLES, "300", 20.0);

        assertEquals(100.0, curso.calcularCostoSesiones(profe), 0.001); // 5 * 20
        assertEquals(0.0, curso.calcularCostoSesiones(null), 0.001);
    }

    @Test
    void mesesInvalidosLanzanExcepcion() {
        CursoRegular curso = CursoFactory.crearRegular(
                "R2", "Test", Idioma.INGLES, "d",
                1, 10.0, EstadoCurso.ACTIVO, List.of());

        assertThrows(IllegalArgumentException.class, () -> curso.calcularCosto(0));
        assertThrows(IllegalArgumentException.class, () -> curso.calcularCosto(-1));
    }
}
