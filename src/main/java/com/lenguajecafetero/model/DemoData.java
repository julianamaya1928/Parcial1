package com.lenguajecafetero.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Carga datos de demostracion en la academia (para la GUI).
 */
public final class DemoData {

    private DemoData() {
    }

    public static void cargar(Academia academia) {
        Estudiante e1 = new Estudiante("Ana Lopez", "1001", "3001112233",
                "ana@mail.com", 22, LocalDate.of(2026, 1, 10));
        Estudiante e2 = new Estudiante("Carlos Ruiz", "1002", "3004445566",
                "carlos@mail.com", 28, LocalDate.of(2026, 1, 15));
        academia.registrarEstudiante(e1);
        academia.registrarEstudiante(e2);

        Profesor p1 = new Profesor("PR01", "Luis Gomez", Idioma.INGLES, "310111", 25000);
        Profesor p2 = new Profesor("PR02", "Marie Dupont", Idioma.FRANCES, "310222", 28000);
        academia.registrarProfesor(p1);
        academia.registrarProfesor(p2);

        ServicioAdicional s1 = new ServicioAdicional("SV01", "Simulacro certificacion",
                "Examen de practica", 80000, true);
        ServicioAdicional s2 = new ServicioAdicional("SV02", "Material impreso",
                "Cuaderno y fotocopias", 35000, true);
        academia.registrarServicio(s1);
        academia.registrarServicio(s2);

        Curso c1 = CursoFactory.crearRegular("C-REG-01", "Ingles Regular B1", Idioma.INGLES,
                "Formacion estandar", 6, 150000, EstadoCurso.ACTIVO,
                List.of("Plataforma virtual", "Material didactico"));
        Curso c2 = CursoFactory.crearIntensivo("C-INT-01", "Frances Intensivo", Idioma.FRANCES,
                "Ritmo concentrado", 3, 180000, EstadoCurso.ACTIVO,
                List.of("Club de conversacion"), 1.3);
        CursoPersonalizado c3 = CursoFactory.crearPersonalizado("C-PER-01", "Portugues 1:1",
                Idioma.PORTUGUES, "Sesiones individuales", 4, 120000, EstadoCurso.ACTIVO,
                List.of("Tutorias"), 8, NivelIdioma.A2, "Viaje de negocios");
        academia.registrarCurso(c1);
        academia.registrarCurso(c2);
        academia.registrarCurso(c3);

        Matricula m1 = new MatriculaBuilder()
                .conCodigo("MAT-001")
                .conEstudiante(e1)
                .conCurso(c1)
                .conMeses(3)
                .conServicio(s2)
                .conDescuento(20000)
                .conFecha(LocalDate.of(2026, 2, 5))
                .build();
        Matricula m2 = new MatriculaBuilder()
                .conCodigo("MAT-002")
                .conEstudiante(e2)
                .conCurso(c3)
                .conMeses(2)
                .conProfesor(p1)
                .conServicio(s1)
                .conDescuento(0)
                .conFecha(LocalDate.of(2026, 2, 12))
                .build();
        academia.registrarMatricula(m1);
        academia.registrarMatricula(m2);
    }
}
