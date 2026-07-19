package model.jugadores;

/**
 * Representa un jugador controlado por la computadora.
 *
 * Implementa una estrategia sencilla para seleccionar
 * una carta válida durante su turno.
 *
 * @author Andrés Felipe Escobar
 * @author Carlos Delgado
 * @version 1.0
 */

import model.Carta;

public class JugadorMaquina extends Jugador {
    /**
     * Crea un nuevo jugador controlado por la máquina.
     *
     * @param nombre Nombre de la máquina.
     */
    public JugadorMaquina(String nombre) {
        super(nombre);
    }
    /**
     * Selecciona una carta válida para jugar.
     *
     * @param sumaMesa Valor actual de la suma de la mesa.
     * @return Carta seleccionada o null si no existe una jugada válida.
     */
    public Carta elegirCarta(int sumaMesa) {

        for (int i = 0; i < getMano().size(); i++) {
            Carta carta = getMano().get(i);
            int valor = obtenerValorCarta(carta, sumaMesa);

            if (sumaMesa + valor <= 50) {
                return quitarCarta(i);
            }
        }
        return null;
    }
    /**
     * Selecciona una carta válida para jugar.
     *
     * @param sumaMesa Valor actual de la suma de la mesa.
     * @return Carta seleccionada o null si no existe una jugada válida.
     */
    private int obtenerValorCarta(Carta carta, int sumaMesa) {
        switch (carta.getValor()) {
            case "A": return (sumaMesa <= 40) ? 10 : 1;
            case "9": return 0;
            case "J": case "Q": case "K": return -10;
            default: return Integer.parseInt(carta.getValor());
        }
    }
}