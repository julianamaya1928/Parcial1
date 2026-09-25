package com.lenguajecafetero.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatriculaTest {

    private Estudiante estudiante;
    private CursoRegular cursoRegular;
    private CursoPersonalizado cursoPersonalizado;
    private Profesor profesor;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante("Ana Lopez", "1001", "300", "ana@mail.com", 22, LocalDate.now());
        cursoRegular = CursoFactory.crearRegular(
                "R1", "Ingles", Idioma.INGLES, "d", 6, 100.0, EstadoCurso.ACTIVO, List.of());
        cursoPersonalizado = CursoFactory.crearPersonalizado(
                "P1", "Pers", Idioma.FRANCES, "d", 4, 80.0, EstadoCurso.ACTIVO, List.of(),
                10, NivelIdioma.B1, "obj");
        profesor = new Profesor("PR1", "Luis", Idioma.FRANCES, "301", 20.0);
    }

    @Test
    void calculaValorConServiciosYDescuento() {
        // 100*3 + 30 - 50 = 280
        Matricula m = new Matricula("M1", LocalDate.now(), estudiante, cursoRegular, 3, 50.0);
        m.agregarServicio(new ServicioAdicional("S1", "Tutor", "t", 30.0, true));

        assertEquals(280.0, m.getValorFinal(), 0.001);
        assertEquals(280.0, m.calcularValor(), 0.001);
    }

    @Test
    void personalizadoSumaSesionesConProfesor() {
        // 80*2 + 10*20 - 10 = 350
        Matricula m = new Matricula("M2", LocalDate.now(), estudiante, cursoPersonalizado, 2, 10.0);
        m.asignarProfesor(profesor);

        assertEquals(350.0, m.getValorFinal(), 0.001);
        assertTrue(m.tieneProfesorAsignado());
    }

    @Test
    void noPermiteProfesorEnCursoNoPersonalizado() {
        Matricula m = new Matricula("M3", LocalDate.now(), estudiante, cursoRegular, 1, 0);

        assertThrows(IllegalStateException.class, () -> m.asignarProfesor(profesor));
    }

    @Test
    void valorNoBajaDeCero() {
        Matricula m = new Matricula("M4", LocalDate.now(), estudiante, cursoRegular, 1, 9999.0);

        assertEquals(0.0, m.getValorFinal(), 0.001);
    }
}
