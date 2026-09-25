package com.lenguajecafetero.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcademiaTest {

    private Academia academia;
    private Estudiante estudiante;
    private Curso curso;

    @BeforeEach
    void setUp() {
        academia = new Academia(
                "LenguajeCafetero", "900123", "Calle 1",
                "300111", "info@lc.com", "https://lc.com");
        estudiante = new Estudiante("Ana Lopez", "1001", "300", "a@mail.com", 22, LocalDate.now());
        curso = CursoFactory.crearRegular(
                "R1", "Ingles B1", Idioma.INGLES, "d",
                6, 100.0, EstadoCurso.ACTIVO, List.of());
        academia.registrarEstudiante(estudiante);
        academia.registrarCurso(curso);
    }

    @Test
    void buscarEstudiantePorDocumentoEncontrado() {
        Optional<Estudiante> encontrado = academia.buscarEstudiantePorDocumento("1001");

        assertTrue(encontrado.isPresent());
        assertEquals("Ana Lopez", encontrado.get().getNombreCompleto());
    }

    @Test
    void buscarEstudiantePorDocumentoNoEncontrado() {
        assertTrue(academia.buscarEstudiantePorDocumento("9999").isEmpty());
        assertTrue(academia.buscarEstudiantePorDocumento(null).isEmpty());
        assertTrue(academia.buscarEstudiantePorDocumento("  ").isEmpty());
    }

    @Test
    void noPermiteDocumentoDuplicado() {
        Estudiante otro = new Estudiante("Pedro", "1001", "t", "p@mail.com", 30, LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> academia.registrarEstudiante(otro));
    }

    @Test
    void ingresosPorPeriodoSumaSoloEnRango() {
        Matricula dentro1 = new MatriculaBuilder()
                .conCodigo("M1").conEstudiante(estudiante).conCurso(curso)
                .conMeses(1).conFecha(LocalDate.of(2026, 2, 10)).build(); // 100
        Matricula dentro2 = new MatriculaBuilder()
                .conCodigo("M2").conEstudiante(estudiante).conCurso(curso)
                .conMeses(2).conFecha(LocalDate.of(2026, 2, 28)).build(); // 200
        Matricula fuera = new MatriculaBuilder()
                .conCodigo("M3").conEstudiante(estudiante).conCurso(curso)
                .conMeses(1).conFecha(LocalDate.of(2026, 3, 15)).build(); // 100

        academia.registrarMatricula(dentro1);
        academia.registrarMatricula(dentro2);
        academia.registrarMatricula(fuera);

        double ingresos = academia.calcularIngresosPorPeriodo(
                LocalDate.of(2026, 2, 1), LocalDate.of(2026, 2, 28));

        assertEquals(300.0, ingresos, 0.001);
    }

    @Test
    void ingresosPeriodoInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                academia.calcularIngresosPorPeriodo(
                        LocalDate.of(2026, 3, 1), LocalDate.of(2026, 2, 1)));
    }

    @Test
    void noMatriculaCursoInactivo() {
        Curso suspendido = CursoFactory.crearRegular(
                "R2", "Susp", Idioma.FRANCES, "d",
                3, 50, EstadoCurso.SUSPENDIDO, List.of());
        academia.registrarCurso(suspendido);

        Matricula m = new MatriculaBuilder()
                .conCodigo("MX").conEstudiante(estudiante).conCurso(suspendido)
                .conMeses(1).build();

        assertThrows(IllegalStateException.class, () -> academia.registrarMatricula(m));
    }

    @Test
    void noMatriculaEstudianteNoRegistrado() {
        Estudiante externo = new Estudiante("Externo", "8888", "t", "e@m.com", 25, LocalDate.now());
        Matricula m = new MatriculaBuilder()
                .conCodigo("MY").conEstudiante(externo).conCurso(curso)
                .conMeses(1).build();

        assertThrows(IllegalArgumentException.class, () -> academia.registrarMatricula(m));
    }
}
