package controller;

/**
 * Controlador de la ventana de instrucciones.
 *
 * Permite regresar al menú principal del juego.
 *
 * @author Andrés Felipe Escobar
 * @author Carlos Delgado
 * @version 1.0
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InstruccionesController {
    /**
     * Regresa a la ventana principal.
     *
     * @param event Evento generado por el botón.
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    @FXML
    private void atras(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/InicioView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}

