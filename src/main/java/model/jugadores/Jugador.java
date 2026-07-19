package model.jugadores;

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

import model.Carta;
import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<Carta> mano;
    private boolean eliminado;
    /**
     * Crea un nuevo jugador.
     *
     * @param nombre Nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.eliminado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    /**
     * Agrega una carta a la mano del jugador.
     *
     * @param carta Carta que será agregada.
     */
    public void agregarCarta(Carta carta) {
        mano.add(carta);
    }
    /**
     * Obtiene la cantidad de cartas en la mano.
     *
     * @return Número de cartas.
     */
    public int cantidadCartas() {
        return mano.size();
    }
    /**
     * Elimina una carta de la mano.
     *
     * @param indice Posición de la carta.
     * @return Carta eliminada.
     */
    public Carta quitarCarta(int indice) {
        return mano.remove(indice);
    }
    public void vaciarMano() {
        mano.clear();
    }
}
