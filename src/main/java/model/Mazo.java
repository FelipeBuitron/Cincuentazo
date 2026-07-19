package model;

/**
 * Representa el mazo de cartas del juego.
 *
 * Se encarga de crear, barajar y repartir las cartas.
 *
 * @author Andrés Felipe Escobar
 * @author Carlos Delgado
 * @version 1.0
 */

import model.excepciones.SinCartasExcepcion;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> cartas;
    /**
     * Crea un nuevo mazo y lo baraja automáticamente.
     */
    public Mazo() {
        cartas = new ArrayList<>();
        crearMazo();
        barajar();
    }
    /**
     * Genera todas las cartas que conforman el mazo.
     */
    private void crearMazo() {

        String[] palos = {"S", "H", "D", "C"};

        String[] valores = {
                "A", "2", "3", "4", "5", "6", "7",
                "8", "9", "0", "J", "Q", "K"
        };

        for (String palo : palos) {
            for (String valor : valores) {
                cartas.add(new Carta(valor, palo));
            }
        }
    }
    /**
     * Mezcla aleatoriamente las cartas del mazo.
     */
    public void barajar() {
        Collections.shuffle(cartas);
    }
    /**
     * Extrae la primera carta disponible del mazo.
     *
     * @return Carta obtenida o null si el mazo está vacío.
     */
    public Carta tomarCarta() {
        if (cartas.isEmpty()) {
            throw new SinCartasExcepcion("El mazo está vacío.");
        }
        return cartas.remove(0);
    }
    /**
     * Obtiene la cantidad de cartas restantes en el mazo.
     *
     * @return Número de cartas disponibles.
     */
    public int cantidadCartas() {
        return cartas.size();
    }
    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }
    public void barajarMazo() {
        Collections.shuffle(cartas);
    }

}
