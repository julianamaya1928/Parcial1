package com.lenguajecafetero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Punto de entrada de la aplicación JavaFX.
 * Academia de idiomas LenguajeCafetero — Parcial I Programación II.
 *
 * <p>Arquitectura MVC:
 * <ul>
 *   <li>{@code model} — dominio y reglas de negocio</li>
 *   <li>{@code view} — FXML / recursos visuales</li>
 *   <li>{@code controller} — controladores JavaFX</li>
 * </ul>
 */
public class MainApp extends Application {

    public static final String APP_TITLE = "LenguajeCafetero — Gestión académica";

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(
                        getClass().getResource("/fxml/MainView.fxml"),
                        "No se encontró /fxml/MainView.fxml"
                )
        );

        Scene scene = new Scene(root, 900, 600);
        var css = getClass().getResource("/css/app.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        stage.setTitle(APP_TITLE);
        stage.setScene(scene);
        stage.setMinWidth(720);
        stage.setMinHeight(480);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
