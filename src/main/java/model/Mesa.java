package model;

/**
 * Representa la mesa donde se colocan las cartas jugadas.
 *
 * @author Andrés Felipe Escobar
 * @author Carlos Delgado
 * @version 1.0
 */

import java.util.ArrayList;

public class Mesa {

    private ArrayList<Carta> cartas;

    public Mesa() {
        cartas = new ArrayList<>();
    }
    /**
     * Agrega una carta a la mesa.
     *
     * @param carta Carta que será agregada.
     */
    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }
    /**
     * Obtiene la última carta jugada.
     *
     * @return Última carta de la mesa.
     */
    public Carta getUltimaCarta() {

        if (cartas.isEmpty()) {
            return null;
        }

        return cartas.get(cartas.size() - 1);
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public ArrayList<Carta> retirarCartasParaReciclar() {

        ArrayList<Carta> recicladas = new ArrayList<>();

        while (cartas.size() > 1) {
            recicladas.add(cartas.remove(0));
        }

        return recicladas;
    }
}