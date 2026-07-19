package model.jugadores;

/**
 * Representa al jugador controlado por el usuario.
 *
 * Hereda el comportamiento de la clase Jugador.
 *
 * @author Andrés Felipe Escobar
 * @author Carlos Delgado
 * @version 1.0
 */

public class JugadorHumano extends Jugador {
    /**
     * Crea un nuevo jugador humano.
     *
     * @param nombre Nombre del jugador.
     */
    public JugadorHumano(String nombre) {
        super(nombre);
    }
}