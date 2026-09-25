package com.lenguajecafetero.controller;

import com.lenguajecafetero.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador principal MVC: navegacion + CRUD.
 * Toda la logica de negocio vive en el modelo (Academia, Matricula, Factory, Builder).
 */
public class MainController {

    private Academia academia;

    @FXML private TabPane tabSecciones;
    @FXML private Label lblAcademia;
    @FXML private Label lblEstado;

    // --- Estudiantes ---
    @FXML private TableView<Estudiante> tblEstudiantes;
    @FXML private TableColumn<Estudiante, String> colEstDoc;
    @FXML private TableColumn<Estudiante, String> colEstNombre;
    @FXML private TableColumn<Estudiante, String> colEstTel;
    @FXML private TableColumn<Estudiante, String> colEstCorreo;
    @FXML private TableColumn<Estudiante, Integer> colEstEdad;
    @FXML private TextField txtEstDocumento;
    @FXML private TextField txtEstNombre;
    @FXML private TextField txtEstTelefono;
    @FXML private TextField txtEstCorreo;
    @FXML private TextField txtEstEdad;

    // --- Profesores ---
    @FXML private TableView<Profesor> tblProfesores;
    @FXML private TableColumn<Profesor, String> colProfId;
    @FXML private TableColumn<Profesor, String> colProfNombre;
    @FXML private TableColumn<Profesor, Idioma> colProfIdioma;
    @FXML private TableColumn<Profesor, Double> colProfTarifa;
    @FXML private TextField txtProfId;
    @FXML private TextField txtProfNombre;
    @FXML private ComboBox<Idioma> cmbProfIdioma;
    @FXML private TextField txtProfTelefono;
    @FXML private TextField txtProfTarifa;

    // --- Servicios ---
    @FXML private TableView<ServicioAdicional> tblServicios;
    @FXML private TableColumn<ServicioAdicional, String> colSrvCodigo;
    @FXML private TableColumn<ServicioAdicional, String> colSrvNombre;
    @FXML private TableColumn<ServicioAdicional, Double> colSrvPrecio;
    @FXML private TableColumn<ServicioAdicional, Boolean> colSrvDisp;
    @FXML private TextField txtSrvCodigo;
    @FXML private TextField txtSrvNombre;
    @FXML private TextField txtSrvDescripcion;
    @FXML private TextField txtSrvPrecio;
    @FXML private CheckBox chkSrvDisponible;

    // --- Cursos ---
    @FXML private TableView<Curso> tblCursos;
    @FXML private TableColumn<Curso, String> colCurCodigo;
    @FXML private TableColumn<Curso, String> colCurNombre;
    @FXML private TableColumn<Curso, TipoCurso> colCurTipo;
    @FXML private TableColumn<Curso, Idioma> colCurIdioma;
    @FXML private TableColumn<Curso, Double> colCurValor;
    @FXML private TableColumn<Curso, EstadoCurso> colCurEstado;
    @FXML private TextField txtCurCodigo;
    @FXML private TextField txtCurNombre;
    @FXML private ComboBox<TipoCurso> cmbCurTipo;
    @FXML private ComboBox<Idioma> cmbCurIdioma;
    @FXML private TextField txtCurDescripcion;
    @FXML private TextField txtCurDuracion;
    @FXML private TextField txtCurValor;
    @FXML private ComboBox<EstadoCurso> cmbCurEstado;
    @FXML private TextField txtCurFactor;
    @FXML private TextField txtCurSesiones;
    @FXML private ComboBox<NivelIdioma> cmbCurNivel;
    @FXML private TextField txtCurObjetivos;

    // --- Matriculas ---
    @FXML private TableView<Matricula> tblMatriculas;
    @FXML private TableColumn<Matricula, String> colMatCodigo;
    @FXML private TableColumn<Matricula, LocalDate> colMatFecha;
    @FXML private TableColumn<Matricula, String> colMatEstudiante;
    @FXML private TableColumn<Matricula, String> colMatCurso;
    @FXML private TableColumn<Matricula, Double> colMatValor;
    @FXML private TextField txtMatCodigo;
    @FXML private DatePicker dtpMatFecha;
    @FXML private ComboBox<Estudiante> cmbMatEstudiante;
    @FXML private ComboBox<Curso> cmbMatCurso;
    @FXML private ComboBox<Profesor> cmbMatProfesor;
    @FXML private TextField txtMatMeses;
    @FXML private TextField txtMatDescuento;
    @FXML private ListView<ServicioAdicional> lstMatServicios;
    @FXML private Label lblMatValorPreview;

    @FXML private TextField txtConsultaDocumento;
    @FXML private Label lblConsultaEstudiante;
    @FXML private DatePicker dtpIngresosInicio;
    @FXML private DatePicker dtpIngresosFin;
    @FXML private Label lblIngresosResultado;

@FXML
    private void initialize() {
        configurarTablas();
        if (cmbProfIdioma != null) {
            cmbProfIdioma.setItems(FXCollections.observableArrayList(Idioma.values()));
        }
        if (cmbCurTipo != null) {
            cmbCurTipo.setItems(FXCollections.observableArrayList(TipoCurso.values()));
            cmbCurTipo.getSelectionModel().selectFirst();
            cmbCurTipo.valueProperty().addListener((o, a, b) -> actualizarCamposCurso());
        }
        if (cmbCurIdioma != null) {
            cmbCurIdioma.setItems(FXCollections.observableArrayList(Idioma.values()));
        }
        if (cmbCurEstado != null) {
            cmbCurEstado.setItems(FXCollections.observableArrayList(EstadoCurso.values()));
            cmbCurEstado.getSelectionModel().select(EstadoCurso.ACTIVO);
        }
        if (cmbCurNivel != null) {
            cmbCurNivel.setItems(FXCollections.observableArrayList(NivelIdioma.values()));
        }
        if (lstMatServicios != null) {
            lstMatServicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
        if (dtpMatFecha != null) {
            dtpMatFecha.setValue(LocalDate.now());
        }
        if (dtpIngresosInicio != null) {
            dtpIngresosInicio.setValue(LocalDate.of(2026, 2, 1));
        }
        if (dtpIngresosFin != null) {
            dtpIngresosFin.setValue(LocalDate.of(2026, 2, 28));
        }
        if (tabSecciones != null) {
            tabSecciones.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldTab, newTab) -> actualizarEstado(newTab));
        }
        actualizarCamposCurso();
    }

    private void configurarTablas() {
        if (colEstDoc != null) {
            colEstDoc.setCellValueFactory(new PropertyValueFactory<>("documento"));
            colEstNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
            colEstTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            colEstCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            colEstEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        }
        if (colProfId != null) {
            colProfId.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
            colProfNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colProfIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
            colProfTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifaPorSesion"));
        }
        if (colSrvCodigo != null) {
            colSrvCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            colSrvNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colSrvPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            colSrvDisp.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));
        }
        if (colCurCodigo != null) {
            colCurCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            colCurNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colCurTipo.setCellValueFactory(c ->
                    new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTipo()));
            colCurIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
            colCurValor.setCellValueFactory(new PropertyValueFactory<>("valorMensual"));
            colCurEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        }
        if (colMatCodigo != null) {
            colMatCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            colMatFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            colMatEstudiante.setCellValueFactory(c ->
                    new javafx.beans.property.SimpleStringProperty(
                            c.getValue().getEstudiante().getNombreCompleto()));
            colMatCurso.setCellValueFactory(c ->
                    new javafx.beans.property.SimpleStringProperty(
                            c.getValue().getCurso().getNombre()));
            colMatValor.setCellValueFactory(new PropertyValueFactory<>("valorFinal"));
        }
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
        refrescarTodo();
        if (lblAcademia != null && academia != null) {
            lblAcademia.setText(academia.getNombreComercial()
                    + "  | NIT " + academia.getNit()
                    + "  | " + nullSafe(academia.getDireccion()));
        }
        if (tabSecciones != null) {
            actualizarEstado(tabSecciones.getSelectionModel().getSelectedItem());
        }
    }

    public void refrescarTodo() {
        if (academia == null) {
            return;
        }
        if (tblEstudiantes != null) {
            tblEstudiantes.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
        }
        if (tblProfesores != null) {
            tblProfesores.setItems(FXCollections.observableArrayList(academia.getProfesores()));
        }
        if (tblServicios != null) {
            tblServicios.setItems(FXCollections.observableArrayList(academia.getServicios()));
        }
        if (tblCursos != null) {
            tblCursos.setItems(FXCollections.observableArrayList(academia.getCursos()));
        }
        if (tblMatriculas != null) {
            tblMatriculas.setItems(FXCollections.observableArrayList(academia.getMatriculas()));
        }
        if (cmbMatEstudiante != null) {
            cmbMatEstudiante.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
        }
        if (cmbMatCurso != null) {
            cmbMatCurso.setItems(FXCollections.observableArrayList(academia.getCursos()));
        }
        if (cmbMatProfesor != null) {
            cmbMatProfesor.setItems(FXCollections.observableArrayList(academia.getProfesores()));
        }
        if (lstMatServicios != null) {
            lstMatServicios.setItems(FXCollections.observableArrayList(academia.getServicios()));
        }
    }

    public Academia getAcademia() {
        return academia;
    }

@FXML
    private void onRegistrarEstudiante() {
        try {
            int edad = Integer.parseInt(txtEstEdad.getText().trim());
            Estudiante e = new Estudiante(
                    txtEstNombre.getText(), txtEstDocumento.getText(),
                    txtEstTelefono.getText(), txtEstCorreo.getText(),
                    edad, LocalDate.now());
            academia.registrarEstudiante(e);
            limpiarEstudiante();
            refrescarTodo();
            mostrarInfo("Estudiante registrado: " + e);
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void onRegistrarProfesor() {
        try {
            double tarifa = Double.parseDouble(txtProfTarifa.getText().trim());
            Profesor p = new Profesor(
                    txtProfId.getText(), txtProfNombre.getText(),
                    cmbProfIdioma.getValue(), txtProfTelefono.getText(), tarifa);
            academia.registrarProfesor(p);
            limpiarProfesor();
            refrescarTodo();
            mostrarInfo("Profesor registrado: " + p);
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void onRegistrarServicio() {
        try {
            double precio = Double.parseDouble(txtSrvPrecio.getText().trim());
            ServicioAdicional s = new ServicioAdicional(
                    txtSrvCodigo.getText(), txtSrvNombre.getText(),
                    txtSrvDescripcion.getText(), precio,
                    chkSrvDisponible == null || chkSrvDisponible.isSelected());
            academia.registrarServicio(s);
            limpiarServicio();
            refrescarTodo();
            mostrarInfo("Servicio registrado: " + s);
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void onRegistrarCurso() {
        try {
            TipoCurso tipo = cmbCurTipo.getValue();
            int duracion = Integer.parseInt(txtCurDuracion.getText().trim());
            double valor = Double.parseDouble(txtCurValor.getText().trim());
            EstadoCurso estado = cmbCurEstado.getValue() != null
                    ? cmbCurEstado.getValue() : EstadoCurso.ACTIVO;
            List<String> beneficios = new ArrayList<>();
            Curso curso = crearCursoDesdeFormulario(tipo, duracion, valor, estado, beneficios);
            academia.registrarCurso(curso);
            limpiarCurso();
            refrescarTodo();
            mostrarInfo("Curso registrado: " + curso);
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    private Curso crearCursoDesdeFormulario(TipoCurso tipo, int duracion, double valor,
                                            EstadoCurso estado, List<String> beneficios) {
        return switch (tipo) {
            case REGULAR -> CursoFactory.crearRegular(
                    txtCurCodigo.getText(), txtCurNombre.getText(),
                    cmbCurIdioma.getValue(), txtCurDescripcion.getText(),
                    duracion, valor, estado, beneficios);
            case INTENSIVO -> {
                double factor = txtCurFactor.getText() == null || txtCurFactor.getText().isBlank()
                        ? CursoIntensivo.FACTOR_POR_DEFECTO
                        : Double.parseDouble(txtCurFactor.getText().trim());
                yield CursoFactory.crearIntensivo(
                        txtCurCodigo.getText(), txtCurNombre.getText(),
                        cmbCurIdioma.getValue(), txtCurDescripcion.getText(),
                        duracion, valor, estado, beneficios, factor);
            }
            case PERSONALIZADO -> {
                int sesiones = Integer.parseInt(txtCurSesiones.getText().trim());
                yield CursoFactory.crearPersonalizado(
                        txtCurCodigo.getText(), txtCurNombre.getText(),
                        cmbCurIdioma.getValue(), txtCurDescripcion.getText(),
                        duracion, valor, estado, beneficios,
                        sesiones, cmbCurNivel.getValue(), txtCurObjetivos.getText());
            }
        };
    }

@FXML
    private void onRegistrarMatricula() {
        try {
            int meses = Integer.parseInt(txtMatMeses.getText().trim());
            double descuento = parseDescuento();
            MatriculaBuilder builder = new MatriculaBuilder()
                    .conCodigo(txtMatCodigo.getText())
                    .conFecha(dtpMatFecha.getValue() != null ? dtpMatFecha.getValue() : LocalDate.now())
                    .conEstudiante(cmbMatEstudiante.getValue())
                    .conCurso(cmbMatCurso.getValue())
                    .conMeses(meses)
                    .conDescuento(descuento);
            if (cmbMatProfesor.getValue() != null) {
                builder.conProfesor(cmbMatProfesor.getValue());
            }
            if (lstMatServicios != null) {
                for (ServicioAdicional s : lstMatServicios.getSelectionModel().getSelectedItems()) {
                    builder.conServicio(s);
                }
            }
            Matricula m = builder.build();
            academia.registrarMatricula(m);
            limpiarMatricula();
            refrescarTodo();
            mostrarInfo("Matricula registrada. Valor final: $"
                    + String.format("%.2f", m.getValorFinal()));
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void onPreviewValorMatricula() {
        try {
            if (cmbMatEstudiante.getValue() == null || cmbMatCurso.getValue() == null
                    || txtMatMeses.getText() == null || txtMatMeses.getText().isBlank()) {
                lblMatValorPreview.setText("Seleccione estudiante, curso y meses");
                return;
            }
            int meses = Integer.parseInt(txtMatMeses.getText().trim());
            MatriculaBuilder b = new MatriculaBuilder()
                    .conCodigo("PREVIEW")
                    .conEstudiante(cmbMatEstudiante.getValue())
                    .conCurso(cmbMatCurso.getValue())
                    .conMeses(meses)
                    .conDescuento(parseDescuento());
            if (cmbMatProfesor.getValue() != null) {
                b.conProfesor(cmbMatProfesor.getValue());
            }
            if (lstMatServicios != null) {
                b.conServicios(new ArrayList<>(
                        lstMatServicios.getSelectionModel().getSelectedItems()));
            }
            Matricula preview = b.build();
            lblMatValorPreview.setText("Valor estimado: $"
                    + String.format("%.2f", preview.getValorFinal()));
        } catch (Exception ex) {
            lblMatValorPreview.setText("Error: " + ex.getMessage());
        }
    }

    private double parseDescuento() {
        if (txtMatDescuento.getText() == null || txtMatDescuento.getText().isBlank()) {
            return 0;
        }
        return Double.parseDouble(txtMatDescuento.getText().trim());
    }

    private void actualizarCamposCurso() {
        TipoCurso tipo = cmbCurTipo != null ? cmbCurTipo.getValue() : null;
        boolean intensivo = tipo == TipoCurso.INTENSIVO;
        boolean personalizado = tipo == TipoCurso.PERSONALIZADO;
        if (txtCurFactor != null) {
            txtCurFactor.setDisable(!intensivo);
        }
        if (txtCurSesiones != null) {
            txtCurSesiones.setDisable(!personalizado);
        }
        if (cmbCurNivel != null) {
            cmbCurNivel.setDisable(!personalizado);
        }
        if (txtCurObjetivos != null) {
            txtCurObjetivos.setDisable(!personalizado);
        }
    }

    private void actualizarEstado(Tab tab) {
        if (lblEstado == null || academia == null) {
            return;
        }
        String seccion = tab != null ? tab.getText() : "-";
        lblEstado.setText("Seccion: " + seccion
                + "  | " + academia.getNombreComercial()
                + "  | " + academia.getEstudiantes().size() + " est. / "
                + academia.getCursos().size() + " cursos / "
                + academia.getMatriculas().size() + " matr.");
    }

private void limpiarEstudiante() {
        txtEstDocumento.clear();
        txtEstNombre.clear();
        txtEstTelefono.clear();
        txtEstCorreo.clear();
        txtEstEdad.clear();
    }

    private void limpiarProfesor() {
        txtProfId.clear();
        txtProfNombre.clear();
        cmbProfIdioma.getSelectionModel().clearSelection();
        txtProfTelefono.clear();
        txtProfTarifa.clear();
    }

    private void limpiarServicio() {
        txtSrvCodigo.clear();
        txtSrvNombre.clear();
        txtSrvDescripcion.clear();
        txtSrvPrecio.clear();
        if (chkSrvDisponible != null) {
            chkSrvDisponible.setSelected(true);
        }
    }

    private void limpiarCurso() {
        txtCurCodigo.clear();
        txtCurNombre.clear();
        txtCurDescripcion.clear();
        txtCurDuracion.clear();
        txtCurValor.clear();
        txtCurFactor.clear();
        txtCurSesiones.clear();
        txtCurObjetivos.clear();
        if (cmbCurTipo != null) {
            cmbCurTipo.getSelectionModel().selectFirst();
        }
        if (cmbCurEstado != null) {
            cmbCurEstado.getSelectionModel().select(EstadoCurso.ACTIVO);
        }
        actualizarCamposCurso();
    }

    private void limpiarMatricula() {
        txtMatCodigo.clear();
        txtMatMeses.clear();
        txtMatDescuento.clear();
        if (dtpMatFecha != null) {
            dtpMatFecha.setValue(LocalDate.now());
        }
        if (cmbMatEstudiante != null) {
            cmbMatEstudiante.getSelectionModel().clearSelection();
        }
        if (cmbMatCurso != null) {
            cmbMatCurso.getSelectionModel().clearSelection();
        }
        if (cmbMatProfesor != null) {
            cmbMatProfesor.getSelectionModel().clearSelection();
        }
        if (lstMatServicios != null) {
            lstMatServicios.getSelectionModel().clearSelection();
        }
        if (lblMatValorPreview != null) {
            lblMatValorPreview.setText("Valor estimado: -");
        }
    }

    private void mostrarError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText("No se pudo completar la operacion");
        a.setContentText(msg != null ? msg : "Error desconocido");
        a.showAndWait();
    }

    private void mostrarInfo(String msg) {
        if (lblEstado != null) {
            lblEstado.setText(msg);
        }
    }

    @FXML
    private void onBuscarEstudiante() {
        try {
            if (academia == null) {
                mostrarError("Academia no inicializada");
                return;
            }
            String doc = txtConsultaDocumento != null ? txtConsultaDocumento.getText() : null;
            var encontrado = academia.buscarEstudiantePorDocumento(doc);
            if (encontrado.isPresent()) {
                Estudiante e = encontrado.get();
                String texto = "Encontrado: " + e.getNombreCompleto()
                        + "\nDocumento: " + e.getDocumento()
                        + "\nTelefono: " + nullSafe(e.getTelefono())
                        + "\nCorreo: " + nullSafe(e.getCorreo())
                        + "\nEdad: " + e.getEdad()
                        + "\nRegistro: " + e.getFechaRegistro();
                if (lblConsultaEstudiante != null) {
                    lblConsultaEstudiante.setText(texto);
                }
                mostrarInfo("RF-07: estudiante " + e.getDocumento() + " encontrado");
            } else {
                if (lblConsultaEstudiante != null) {
                    lblConsultaEstudiante.setText("No se encontro estudiante con documento: "
                            + (doc != null ? doc.trim() : "(vacio)"));
                }
                mostrarInfo("RF-07: sin resultados");
            }
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void onCalcularIngresos() {
        try {
            if (academia == null) {
                mostrarError("Academia no inicializada");
                return;
            }
            LocalDate ini = dtpIngresosInicio != null ? dtpIngresosInicio.getValue() : null;
            LocalDate fin = dtpIngresosFin != null ? dtpIngresosFin.getValue() : null;
            double total = academia.calcularIngresosPorPeriodo(ini, fin);
            long conteo = academia.getMatriculas().stream()
                    .filter(m -> {
                        LocalDate f = m.getFecha();
                        return (f.isEqual(ini) || f.isAfter(ini))
                                && (f.isEqual(fin) || f.isBefore(fin));
                    })
                    .count();
            if (lblIngresosResultado != null) {
                lblIngresosResultado.setText(
                        "Periodo: " + ini + " a " + fin
                                + "\nMatriculas en rango: " + conteo
                                + "\nTotal ingresos: $" + String.format("%,.2f", total));
            }
            mostrarInfo("RF-08: ingresos $" + String.format("%,.2f", total));
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    private static String nullSafe(String s) {
        return s != null ? s : "";
    }
}

