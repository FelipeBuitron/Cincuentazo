package model;

import model.jugadores.Jugador;
import java.util.ArrayList;

public class Juego {

    private Mazo mazo;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Carta> mesa;
    private int sumaMesa;
    private int turnoActual;

    public Juego() {
        mazo = new Mazo();
        jugadores = new ArrayList<>();
        mesa = new ArrayList<>();

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
    }

    private void repartirCartas() {
        for (int i = 0; i < 4; i++) {

            for (Jugador jugador : jugadores) {

                jugador.agregarCarta(mazo.tomarCarta());
            }
        }
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
}