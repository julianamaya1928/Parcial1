package com.lenguajecafetero;

import com.lenguajecafetero.controller.MainController;
import com.lenguajecafetero.model.Academia;
import com.lenguajecafetero.model.DemoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Punto de entrada JavaFX.
 * Crea la Academia, carga datos demo e inyecta el controlador (MVC).
 */
public class MainApp extends Application {

    public static final String APP_TITLE = "LenguajeCafetero - Gestion academica";

    @Override
    public void start(Stage stage) throws IOException {
        Academia academia = crearAcademia();
        DemoData.cargar(academia);

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                getClass().getResource("/fxml/MainView.fxml"),
                "No se encontro /fxml/MainView.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setAcademia(academia);

        Scene scene = new Scene(root, 1100, 720);
        var css = getClass().getResource("/css/app.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        stage.setTitle(APP_TITLE);
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.show();
    }

    private static Academia crearAcademia() {
        return new Academia(
                "LenguajeCafetero",
                "900.123.456-7",
                "Calle 10 # 5-20, Manizales",
                "+57 6 880 0000",
                "contacto@lenguajecafetero.edu.co",
                "https://www.lenguajecafetero.edu.co");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
