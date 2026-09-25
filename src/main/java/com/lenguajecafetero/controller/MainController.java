package com.lenguajecafetero.controller;

import com.lenguajecafetero.model.Academia;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Controlador principal (MVC): navegacion entre secciones.
 * Delega lecturas al modelo Academia; no calcula tarifas aqui.
 */
public class MainController {

    private Academia academia;

    @FXML private TabPane tabSecciones;
    @FXML private Label lblAcademia;
    @FXML private Label lblEstado;

    @FXML private Label lblResumenEstudiantes;
    @FXML private Label lblResumenCursos;
    @FXML private Label lblResumenProfesores;
    @FXML private Label lblResumenServicios;
    @FXML private Label lblResumenMatriculas;
    @FXML private Label lblResumenConsultas;

    @FXML
    private void initialize() {
        if (tabSecciones != null) {
            tabSecciones.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldTab, newTab) -> actualizarEstado(newTab));
        }
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
        refrescarResumenes();
        if (lblAcademia != null && academia != null) {
            lblAcademia.setText(academia.getNombreComercial()
                    + "  | NIT " + academia.getNit()
                    + "  | " + nullSafe(academia.getDireccion()));
        }
        if (tabSecciones != null) {
            actualizarEstado(tabSecciones.getSelectionModel().getSelectedItem());
        }
    }

    public void refrescarResumenes() {
        if (academia == null) {
            return;
        }
        setResumen(lblResumenEstudiantes, "Estudiantes registrados", academia.getEstudiantes().size());
        setResumen(lblResumenCursos, "Cursos registrados", academia.getCursos().size());
        setResumen(lblResumenProfesores, "Profesores registrados", academia.getProfesores().size());
        setResumen(lblResumenServicios, "Servicios registrados", academia.getServicios().size());
        setResumen(lblResumenMatriculas, "Matriculas registradas", academia.getMatriculas().size());
        if (lblResumenConsultas != null) {
            lblResumenConsultas.setText(
                    "Consultas: busqueda por documento e ingresos por periodo.\n"
                            + "Se implementaran en la fase de GUI de consultas.");
        }
    }

    public Academia getAcademia() {
        return academia;
    }

    private void actualizarEstado(Tab tab) {
        if (lblEstado == null) {
            return;
        }
        if (tab == null || academia == null) {
            lblEstado.setText("Listo");
            return;
        }
        lblEstado.setText("Seccion: " + tab.getText()
                + "  | " + academia.getNombreComercial()
                + "  | " + academia.getEstudiantes().size() + " est. / "
                + academia.getCursos().size() + " cursos / "
                + academia.getMatriculas().size() + " matr.");
    }

    private static void setResumen(Label label, String titulo, int cantidad) {
        if (label != null) {
            label.setText(titulo + ": " + cantidad + "\n(CRUD en la siguiente fase)");
        }
    }

    private static String nullSafe(String s) {
        return s != null ? s : "";
    }
}
