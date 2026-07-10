package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import model.Carta;
import model.Juego;
import model.jugadores.Jugador;

import java.io.IOException;

public class JuegoController {
    private Juego juego;

    @FXML
    private ImageView cartaJugador1;

    @FXML
    private ImageView cartaJugador2;

    @FXML
    private ImageView cartaJugador3;

    @FXML
    private ImageView cartaJugador4;

    @FXML
    private void menuPrincipal(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/InicioView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    private void mostrarCarta(ImageView imageView, Carta carta) {

        Image imagen = new Image(getClass().getResourceAsStream(carta.getRutaImagen()));
        imageView.setImage(imagen);

    }

    public void setJuego(Juego juego) {
        this.juego = juego;
        mostrarCartasJugador();
    }

    private void mostrarCartasJugador() {

        Jugador jugador = juego.getJugadorHumano();

        mostrarCarta(cartaJugador1, jugador.getMano().get(0));
        mostrarCarta(cartaJugador2, jugador.getMano().get(1));
        mostrarCarta(cartaJugador3, jugador.getMano().get(2));
        mostrarCarta(cartaJugador4, jugador.getMano().get(3));

    }
}
