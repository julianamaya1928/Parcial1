package com.lenguajecafetero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Raiz / fachada del dominio: datos institucionales y orquestacion
 * de registros y consultas (busqueda e ingresos por periodo).
 */
public class Academia {

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private String paginaWeb;

    private final List<Estudiante> estudiantes = new ArrayList<>();
    private final List<Profesor> profesores = new ArrayList<>();
    private final List<Curso> cursos = new ArrayList<>();
    private final List<ServicioAdicional> servicios = new ArrayList<>();
    private final List<Matricula> matriculas = new ArrayList<>();

    public Academia(String nombreComercial, String nit, String direccion,
                    String telefono, String correo, String paginaWeb) {
        setNombreComercial(nombreComercial);
        setNit(nit);
        setDireccion(direccion);
        setTelefono(telefono);
        setCorreo(correo);
        setPaginaWeb(paginaWeb);
    }

    public Estudiante registrarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo");
        }
        if (buscarEstudiantePorDocumento(estudiante.getDocumento()).isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe un estudiante con documento " + estudiante.getDocumento());
        }
        estudiantes.add(estudiante);
        return estudiante;
    }

    public Profesor registrarProfesor(Profesor profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo");
        }
        for (Profesor p : profesores) {
            if (p.getIdentificacion().equals(profesor.getIdentificacion())) {
                throw new IllegalArgumentException(
                        "Ya existe un profesor con identificacion " + profesor.getIdentificacion());
            }
        }
        profesores.add(profesor);
        return profesor;
    }

    public Curso registrarCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo");
        }
        for (Curso c : cursos) {
            if (c.getCodigo().equals(curso.getCodigo())) {
                throw new IllegalArgumentException(
                        "Ya existe un curso con codigo " + curso.getCodigo());
            }
        }
        cursos.add(curso);
        return curso;
    }

    public ServicioAdicional registrarServicio(ServicioAdicional servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo");
        }
        for (ServicioAdicional s : servicios) {
            if (s.getCodigo().equals(servicio.getCodigo())) {
                throw new IllegalArgumentException(
                        "Ya existe un servicio con codigo " + servicio.getCodigo());
            }
        }
        servicios.add(servicio);
        return servicio;
    }

    /**
     * Registra una matricula. Idealmente el curso debe estar ACTIVO (RN-10).
     */
    public Matricula registrarMatricula(Matricula matricula) {
        if (matricula == null) {
            throw new IllegalArgumentException("La matricula no puede ser nula");
        }
        for (Matricula m : matriculas) {
            if (m.getCodigo().equals(matricula.getCodigo())) {
                throw new IllegalArgumentException(
                        "Ya existe una matricula con codigo " + matricula.getCodigo());
            }
        }
        if (!matricula.getCurso().estaActivo()) {
            throw new IllegalStateException(
                    "Solo se puede matricular en cursos en estado Activo");
        }
        if (buscarEstudiantePorDocumento(matricula.getEstudiante().getDocumento()).isEmpty()) {
            throw new IllegalArgumentException(
                    "El estudiante no esta registrado en la academia");
        }
        if (buscarCursoPorCodigo(matricula.getCurso().getCodigo()).isEmpty()) {
            throw new IllegalArgumentException(
                    "El curso no esta registrado en la academia");
        }
        matriculas.add(matricula);
        return matricula;
    }

    /**
     * RF-07: busca un estudiante por documento de identidad.
     */
    public Optional<Estudiante> buscarEstudiantePorDocumento(String documento) {
        if (documento == null || documento.isBlank()) {
            return Optional.empty();
        }
        String doc = documento.trim();
        for (Estudiante e : estudiantes) {
            if (e.getDocumento().equals(doc)) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    /**
     * RF-08: suma el valorFinal de matriculas cuya fecha esta en [inicio, fin] (inclusive).
     */
    public double calcularIngresosPorPeriodo(LocalDate inicio, LocalDate fin) {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias");
        }
        if (fin.isBefore(inicio)) {
            throw new IllegalArgumentException(
                    "La fecha fin no puede ser anterior a la fecha inicio");
        }
        double total = 0;
        for (Matricula m : matriculas) {
            LocalDate f = m.getFecha();
            if ((f.isEqual(inicio) || f.isAfter(inicio))
                    && (f.isEqual(fin) || f.isBefore(fin))) {
                total += m.getValorFinal();
            }
        }
        return total;
    }

    public Optional<Curso> buscarCursoPorCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return Optional.empty();
        }
        String c = codigo.trim();
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(c)) {
                return Optional.of(curso);
            }
        }
        return Optional.empty();
    }

    public Optional<Profesor> buscarProfesorPorIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.isBlank()) {
            return Optional.empty();
        }
        String id = identificacion.trim();
        for (Profesor p : profesores) {
            if (p.getIdentificacion().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Optional<ServicioAdicional> buscarServicioPorCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return Optional.empty();
        }
        String c = codigo.trim();
        for (ServicioAdicional s : servicios) {
            if (s.getCodigo().equals(c)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        if (nombreComercial == null || nombreComercial.isBlank()) {
            throw new IllegalArgumentException("El nombre comercial es obligatorio");
        }
        this.nombreComercial = nombreComercial.trim();
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        if (nit == null || nit.isBlank()) {
            throw new IllegalArgumentException("El NIT es obligatorio");
        }
        this.nit = nit.trim();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion != null ? direccion.trim() : null;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono != null ? telefono.trim() : null;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo != null ? correo.trim() : null;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb != null ? paginaWeb.trim() : null;
    }

    public List<Estudiante> getEstudiantes() {
        return Collections.unmodifiableList(estudiantes);
    }

    public List<Profesor> getProfesores() {
        return Collections.unmodifiableList(profesores);
    }

    public List<Curso> getCursos() {
        return Collections.unmodifiableList(cursos);
    }

    public List<ServicioAdicional> getServicios() {
        return Collections.unmodifiableList(servicios);
    }

    public List<Matricula> getMatriculas() {
        return Collections.unmodifiableList(matriculas);
    }

    @Override
    public String toString() {
        return nombreComercial + " (NIT " + nit + ")";
    }
}
