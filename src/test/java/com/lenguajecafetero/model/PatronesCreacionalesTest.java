package com.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatronesCreacionalesTest {

    @Test
    void factoryCreaSubclasesCorrectas() {
        Curso r = CursoFactory.crear(TipoCurso.REGULAR, "R1", "Reg", Idioma.INGLES, "d",
                3, 50, EstadoCurso.ACTIVO, List.of());
        Curso i = CursoFactory.crear(TipoCurso.INTENSIVO, "I1", "Int", Idioma.FRANCES, "d",
                3, 50, EstadoCurso.ACTIVO, List.of());

        assertInstanceOf(CursoRegular.class, r);
        assertInstanceOf(CursoIntensivo.class, i);
        assertEquals(TipoCurso.REGULAR, r.getTipo());
        assertEquals(TipoCurso.INTENSIVO, i.getTipo());
    }

    @Test
    void factoryRechazaPersonalizadoPorMetodoGenerico() {
        assertThrows(IllegalArgumentException.class, () ->
                CursoFactory.crear(TipoCurso.PERSONALIZADO, "P1", "P", Idioma.PORTUGUES, "d",
                        2, 40, EstadoCurso.ACTIVO, List.of()));
    }

    @Test
    void factoryCrearPersonalizado() {
        CursoPersonalizado p = CursoFactory.crearPersonalizado(
                "P1", "Pers", Idioma.PORTUGUES, "d", 2, 40, EstadoCurso.ACTIVO, List.of(),
                8, NivelIdioma.A1, "obj");

        assertEquals(TipoCurso.PERSONALIZADO, p.getTipo());
        assertEquals(8, p.getCantidadSesiones());
        assertEquals(NivelIdioma.A1, p.getNivelReferencia());
    }

    @Test
    void builderConstruyeMatriculaConValor() {
        Estudiante e = new Estudiante("Ana", "1", "t", "c", 20, LocalDate.now());
        Curso c = CursoFactory.crearRegular("R1", "Ing", Idioma.INGLES, "d",
                6, 100, EstadoCurso.ACTIVO, List.of());
        ServicioAdicional s = new ServicioAdicional("S1", "Mat", "m", 25, true);

        Matricula m = new MatriculaBuilder()
                .conCodigo("M-B1")
                .conEstudiante(e)
                .conCurso(c)
                .conMeses(2)
                .conServicio(s)
                .conDescuento(10)
                .conFecha(LocalDate.of(2026, 3, 1))
                .build();

        // 100*2 + 25 - 10 = 215
        assertEquals(215.0, m.getValorFinal(), 0.001);
        assertEquals("M-B1", m.getCodigo());
        assertEquals(1, m.getServicios().size());
        assertEquals(LocalDate.of(2026, 3, 1), m.getFecha());
    }

    @Test
    void builderFallaSinDatosObligatorios() {
        assertThrows(IllegalStateException.class, () -> new MatriculaBuilder().build());
        assertThrows(IllegalStateException.class, () ->
                new MatriculaBuilder().conCodigo("X").build());
    }

    @Test
    void builderConProfesorEnPersonalizado() {
        Estudiante e = new Estudiante("Ana", "1", "t", "c", 20, LocalDate.now());
        CursoPersonalizado c = CursoFactory.crearPersonalizado(
                "P1", "P", Idioma.INGLES, "d", 3, 50, EstadoCurso.ACTIVO, List.of(),
                4, NivelIdioma.B2, "obj");
        Profesor p = new Profesor("PR1", "Luis", Idioma.INGLES, "t", 15);

        Matricula m = new MatriculaBuilder()
                .conCodigo("M-P")
                .conEstudiante(e)
                .conCurso(c)
                .conMeses(1)
                .conProfesor(p)
                .build();

        // 50*1 + 4*15 = 110
        assertEquals(110.0, m.getValorFinal(), 0.001);
        assertTrue(m.tieneProfesorAsignado());
    }
}
