package controller;

/**
 * Controlador de la pantalla principal del juego.
 * Permite seleccionar el número de jugadores
 * e iniciar una nueva partida.
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
import model.Juego;

import java.io.IOException;

public class InicioController {

    private int cantidadMaquinas;
    /**
     * Inicia una partida con un jugador humano y una máquina.
     *
     * @param event Evento generado por el botón.
     */
    @FXML
    private void dosJugadores(ActionEvent event) throws IOException {
        cantidadMaquinas = 1;
        abrirJuego(event);
    }
    /**
     * Inicia una partida con un jugador humano y dos máquinas.
     *
     * @param event Evento generado por el botón.
     */
    @FXML
    private void tresJugadores(ActionEvent event) throws IOException {
        cantidadMaquinas = 2;
        abrirJuego(event);
    }
    /**
     * Inicia una partida con un jugador humano y tres máquinas.
     *
     * @param event Evento generado por el botón.
     */
    @FXML
    private void cuatroJugadores(ActionEvent event) throws IOException {
        cantidadMaquinas = 3;
        abrirJuego(event);
    }
    /**
     * Abre la ventana de instrucciones del juego.
     *
     * @param event Evento generado por el botón.
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    @FXML
    private void abrirInstrucciones(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/InstruccionesView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    /**
     * Carga la ventana principal del juego e inicializa
     * una nueva partida.
     *
     * @param event Evento generado por el botón.
     * @throws IOException Si ocurre un error al cargar la vista.
     */
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
