package model;

import model.jugadores.Jugador;
import java.util.ArrayList;

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

        // Crear jugador humano
        jugadores.add(new Jugador("Jugador"));

        // Crear jugadores máquina
        for (int i = 1; i <= cantidadMaquinas; i++) {
            jugadores.add(new Jugador("Máquina " + i));
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
    private int obtenerValorCarta(Carta carta) {

        switch (carta.getValor()) {

            case "A":
                return 1;

            case "J":
            case "Q":
            case "K":
                return 10;

            default:
                return Integer.parseInt(carta.getValor());
        }
    }
    public Mesa getMesa() {
        return mesa;
    }

    public int getSumaMesa() {
        return sumaMesa;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void mostrarManos() {

        for (Jugador jugador : jugadores) {

            System.out.println("==== " + jugador.getNombre() + " ====");

            for (Carta carta : jugador.getMano()) {
                System.out.println(carta.getValor() + carta.getPalo());
            }

            System.out.println();
        }

    }
    public Jugador getJugadorHumano() {
        return jugadores.get(0);
    }

    public Carta jugarCartaJugador(int indice) {

        Jugador jugador = getJugadorHumano();

        Carta carta = jugador.quitarCarta(indice);

        mesa.agregarCarta(carta);

        sumaMesa += obtenerValorCarta(carta);

        Carta nuevaCarta = mazo.tomarCarta();

        if (nuevaCarta != null) {
            jugador.agregarCarta(nuevaCarta);
        }

        return carta;
    }
}