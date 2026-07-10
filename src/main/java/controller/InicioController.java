package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Juego;

import java.io.IOException;

public class InicioController {

    private int cantidadMaquinas;

    @FXML
    private void dosJugadores(ActionEvent event) throws IOException {
        cantidadMaquinas = 1;
        abrirJuego(event);
    }

    @FXML
    private void tresJugadores(ActionEvent event) throws IOException {
        cantidadMaquinas = 2;
        abrirJuego(event);
    }

    @FXML
    private void cuatroJugadores(ActionEvent event) throws IOException {
        cantidadMaquinas = 3;
        abrirJuego(event);
    }

    @FXML
    private void abrirInstrucciones(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/InstruccionesView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void abrirJuego(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JuegoView.fxml"));
        Parent root = loader.load();

        JuegoController controller = loader.getController();
        Juego juego = new Juego();
        juego.iniciarJuego(cantidadMaquinas);

        controller.setJuego(juego);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
