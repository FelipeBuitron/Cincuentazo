package model;

/**
 * Contiene toda la lógica del juego Cincuentazo.
 *
 * Administra el mazo, la mesa, los jugadores,
 * los turnos y las reglas de la partida.
 *
 * @author Andrés Felipe Escobar
 * @author Carlos Delgado
 * @version 1.0
 */

import model.jugadores.Jugador;
import model.jugadores.JugadorHumano;
import model.jugadores.JugadorMaquina;
import java.util.ArrayList;
import model.excepciones.CartaInvalidaExcepcion;
import model.excepciones.SinCartasExcepcion;

public class Juego {

    private Mazo mazo;
    private ArrayList<Jugador> jugadores;
    private Mesa mesa;
    private int sumaMesa;
    private int turnoActual;

    public Juego() {
        mazo = new Mazo();
        jugadores = new ArrayList<>();
        mesa = new Mesa();
        sumaMesa = 0;
        turnoActual = 0;
    }

    public void iniciarJuego(int cantidadMaquinas) {
        jugadores.clear();
        turnoActual = 0;
        jugadores.add(new JugadorHumano("Jugador"));
        for (int i = 1; i <= cantidadMaquinas; i++) {
            jugadores.add(new JugadorMaquina("Máquina " + i));
        }
        repartirCartas();
        iniciarMesa();
    }

    private void repartirCartas() {
        for (int i = 0; i < 4; i++) {
            for (Jugador jugador : jugadores) {
                jugador.agregarCarta(mazo.tomarCarta());
            }
        }
    }

    private void iniciarMesa() {
        Carta cartaInicial = mazo.tomarCarta();
        cartaInicial.setBocaArriba(true);
        mesa.agregarCarta(cartaInicial);
        sumaMesa = obtenerValorCarta(cartaInicial);
    }

    private void reciclarMazo() {

        if (mazo.cantidadCartas() == 0) {

            for (Carta carta : mesa.retirarCartasParaReciclar()) {
                mazo.agregarCarta(carta);
            }

            mazo.barajarMazo();
        }
    }

    private int obtenerValorCarta(Carta carta) {
        switch (carta.getValor()) {
            case "A": return (sumaMesa <= 40) ? 10 : 1;
            case "9": return 0;
            case "J": case "Q": case "K": return -10;
            default: return Integer.parseInt(carta.getValor());
        }
    }

    public boolean puedeJugar(Carta carta) {

        if (carta == null) {
            return false;
        }

        return (sumaMesa + obtenerValorCarta(carta)) <= 50;
    }

    public Mesa getMesa() { return mesa; }
    public int getSumaMesa() { return sumaMesa; }
    public ArrayList<Jugador> getJugadores() { return jugadores; }
    public Jugador getJugadorHumano() { return jugadores.get(0); }
    public int getTurnoActual() { return turnoActual; }

    public Carta jugarCartaJugador(int indice) {
        Jugador jugador = getJugadorHumano();
        if (indice < 0 || indice >= jugador.getMano().size()) return null;
        Carta carta = jugador.getMano().get(indice);
        if (!puedeJugar(carta)) return null;
        carta = jugador.quitarCarta(indice);
        mesa.agregarCarta(carta);
        sumaMesa += obtenerValorCarta(carta);
        reciclarMazo();
        Carta nuevaCarta = mazo.tomarCarta();
        if (nuevaCarta != null) {
            jugador.agregarCarta(nuevaCarta);
        }
        return carta;
    }

    public void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }

    public void eliminarJugadorActual() {

        Jugador eliminado = jugadores.get(turnoActual);

        // Devolver las cartas al mazo
        for (Carta carta : eliminado.getMano()) {
            mazo.agregarCarta(carta);
        }

        // Barajar nuevamente
        mazo.barajarMazo();

        // Vaciar la mano del jugador eliminado
        eliminado.vaciarMano();

        eliminado.setEliminado(true);

        jugadores.remove(turnoActual);

        if (turnoActual >= jugadores.size()) {
            turnoActual = 0;
        }
    }

    public boolean esFinDeJuego() { return jugadores.size() == 1; }
    public Jugador getGanador() { return jugadores.get(0); }

    public void registrarJugadaEnMesa(Carta carta, Jugador j) {
        mesa.agregarCarta(carta);
        sumaMesa += obtenerValorCarta(carta);
        reciclarMazo();
        Carta n = mazo.tomarCarta();
        if (n != null) {
            j.agregarCarta(n);
        }
    }
}