package controller;

/**
 * Controlador principal de la interfaz del juego Cincuentazo.
 * Gestiona la interacción entre la interfaz gráfica y la lógica
 * del juego, mostrando las cartas, la mesa y administrando
 * los turnos de los jugadores.
 *
 * @author Andrés Felipe Escobar
 * @author Carlos Delgado
 * @version 1.0
 */

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Carta;
import model.Juego;
import model.jugadores.Jugador;
import model.jugadores.JugadorMaquina;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.animation.ParallelTransition;

public class JuegoController {
    private Juego juego;

    @FXML private ImageView cartaJugador1, cartaJugador2, cartaJugador3, cartaJugador4, cartaMesa, mazoImagen;
    @FXML private Label lblSuma;
    @FXML private ImageView cartaMaquina1_1, cartaMaquina1_2, cartaMaquina1_3, cartaMaquina1_4;
    @FXML private ImageView cartaMaquina2_1, cartaMaquina2_2, cartaMaquina2_3, cartaMaquina2_4;
    @FXML private ImageView cartaMaquina3_1, cartaMaquina3_2, cartaMaquina3_3, cartaMaquina3_4;

    @FXML private void jugarCarta1(MouseEvent e) { jugarCarta(0); }
    @FXML private void jugarCarta2(MouseEvent e) { jugarCarta(1); }
    @FXML private void jugarCarta3(MouseEvent e) { jugarCarta(2); }
    @FXML private void jugarCarta4(MouseEvent e) { jugarCarta(3); }

    @FXML
    private void menuPrincipal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/InicioView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * Muestra una carta en el ImageView indicado.
     *
     * @param imageView Componente donde se mostrará la carta.
     * @param carta Carta que será mostrada.
     */
    private void mostrarCarta(ImageView imageView, Carta carta) {
        if (imageView == null) return;
        imageView.setImage(carta == null ? null : new Image(getClass().getResourceAsStream(carta.getRutaImagen())));
    }
    /**
     * Inicializa el controlador con una nueva partida.
     *
     * @param juego Instancia del juego que será utilizada.
     */
    public void setJuego(Juego juego) {
        this.juego = juego;

        actualizarJugadoresVisibles();

        mostrarCartasJugador();
        mostrarCartasMaquina();
        mostrarMesa();
        mostrarMazo();
    }
    /**
     * Actualiza la carta visible de la mesa y la suma acumulada.
     */
    private void mostrarMesa() {
        mostrarCarta(cartaMesa, juego.getMesa().getUltimaCarta());
        lblSuma.setText("Suma: " + juego.getSumaMesa());
    }
    /**
     * Muestra la imagen correspondiente al reverso del mazo.
     */
    private void mostrarMazo() {
        mazoImagen.setImage(new Image(getClass().getResourceAsStream("/cards/back.png")));
    }
    /**
     * Actualiza las cartas visibles del jugador humano.
     */
    private void mostrarCartasJugador() {
        Jugador jugador = juego.getJugadorHumano();
        ImageView[] ivs = {cartaJugador1, cartaJugador2, cartaJugador3, cartaJugador4};
        if (jugador.isEliminado()) {
            for (ImageView iv : ivs) mostrarCarta(iv, null);
        } else {
            for (int i = 0; i < 4; i++) {
                mostrarCarta(ivs[i], i < jugador.getMano().size() ? jugador.getMano().get(i) : null);
            }
        }
    }
    /**
     * Permite al jugador jugar una carta de su mano.
     *
     * @param indice Posición de la carta seleccionada.
     */
    private void jugarCarta(int indice) {

        if (juego.esFinDeJuego()) return;

        Carta cartaJugada = juego.jugarCartaJugador(indice);

        if (cartaJugada != null) {

            ImageView[] cartas = {
                    cartaJugador1,
                    cartaJugador2,
                    cartaJugador3,
                    cartaJugador4
            };

            animarCarta(cartas[indice], () -> {

                mostrarMesa();
                mostrarCartasJugador();

                if (juego.getSumaMesa() >= 50)
                    return;

                juego.siguienteTurno();
                manejarTurnos();

            });

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Movimiento inválido");
            alert.setHeaderText(null);
            alert.setContentText("No puedes jugar esa carta porque la suma de la mesa superaría 50.");
            alert.showAndWait();

        }
    }
    /**
     * Controla los turnos de los jugadores controlados por la computadora.
     */
    private void manejarTurnos() {
        if (juego.esFinDeJuego()) return;
        Jugador jugadorActual = juego.getJugadores().get(juego.getTurnoActual());
        if (jugadorActual instanceof JugadorMaquina) {
            JugadorMaquina maquina = (JugadorMaquina) jugadorActual;
            double tiempo = 2 + Math.random() * 2;
            PauseTransition pausa = new PauseTransition(Duration.seconds(tiempo));
            pausa.setOnFinished(event -> {
                Carta elegida = maquina.elegirCarta(juego.getSumaMesa());
                if (elegida != null) {
                    juego.registrarJugadaEnMesa(elegida, maquina);
                } else {
                    juego.eliminarJugadorActual();
                }
                mostrarMesa();
                mostrarCartasJugador();
                if (juego.esFinDeJuego()) {
                    mostrarGanador();
                    return;
                }

                if (!maquina.isEliminado()) {
                    juego.siguienteTurno();
                    manejarTurnos();
                }
            });
            pausa.play();
        }
    }
    /**
     * Muestra el reverso de las cartas pertenecientes a las máquinas.
     */
    private void mostrarCartasMaquina() {
        Image reverso = new Image(getClass().getResourceAsStream("/cards/back.png"));
        ImageView[] cartas = {cartaMaquina1_1, cartaMaquina1_2, cartaMaquina1_3, cartaMaquina1_4,
                cartaMaquina2_1, cartaMaquina2_2, cartaMaquina2_3, cartaMaquina2_4,
                cartaMaquina3_1, cartaMaquina3_2, cartaMaquina3_3, cartaMaquina3_4};
        for (ImageView iv : cartas) if (iv != null) iv.setImage(reverso);
    }
    /**
     * Muestra una alerta que informa el ganador de la partida
     */
    private void mostrarGanador() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fin del juego");
        alert.setHeaderText("¡Tenemos un ganador!");
        alert.setContentText("El ganador es: " + juego.getGanador().getNombre());

        alert.showAndWait();
    }
    /**
     * Ejecuta una animación sobre una carta antes de actualizar
     * el estado del juego.
     *
     * @param carta Carta que será animada.
     * @param alFinal Acción que se ejecutará al finalizar la animación.
     */
    private void animarCarta(ImageView carta, Runnable alFinal) {

        TranslateTransition mover = new TranslateTransition(Duration.millis(500), carta);
        double dx = cartaMesa.getLayoutX() - carta.getLayoutX();
        double dy = cartaMesa.getLayoutY() - carta.getLayoutY();

        mover.setToX(dx);
        mover.setToY(dy);

        ScaleTransition escalar = new ScaleTransition(Duration.millis(500), carta);
        escalar.setToX(0.7);
        escalar.setToY(0.7);

        RotateTransition girar = new RotateTransition(Duration.millis(500), carta);
        girar.setByAngle(360);

        ParallelTransition animacion = new ParallelTransition(
                mover,
                escalar,
                girar
        );

        animacion.setOnFinished(e -> {

            carta.setTranslateX(0);
            carta.setTranslateY(0);
            carta.setScaleX(1);
            carta.setScaleY(1);
            carta.setRotate(0);

            alFinal.run();
        });

        animacion.play();
    }
    /**
     * Oculta las cartas de las máquinas que no participan
     * en la partida según el número de jugadores.
     */
    private void actualizarJugadoresVisibles() {

        int maquinas = juego.getJugadores().size() - 1;

        ImageView[] maquina2 = {
                cartaMaquina2_1,
                cartaMaquina2_2,
                cartaMaquina2_3,
                cartaMaquina2_4
        };

        ImageView[] maquina3 = {
                cartaMaquina3_1,
                cartaMaquina3_2,
                cartaMaquina3_3,
                cartaMaquina3_4
        };

        if (maquinas < 2) {
            for (ImageView iv : maquina2) {
                iv.setVisible(false);
            }
        }

        if (maquinas < 3) {
            for (ImageView iv : maquina3) {
                iv.setVisible(false);
            }
        }
    }
}