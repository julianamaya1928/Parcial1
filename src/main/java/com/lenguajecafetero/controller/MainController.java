package com.lenguajecafetero.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador de la vista principal.
 * Por ahora solo muestra el esqueleto; la lógica de negocio se conectará después.
 */
public class MainController {

    @FXML
    private Label lblEstado;

    @FXML
    private void initialize() {
        if (lblEstado != null) {
            lblEstado.setText("Base del proyecto lista. Próximo paso: modelo de dominio.");
        }
    }
}
