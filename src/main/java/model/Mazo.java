package model;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> cartas;

    public Mazo() {
        cartas = new ArrayList<>();
        crearMazo();
        barajar();
    }
    private void crearMazo() {

        String[] palos = {"S", "H", "D", "C"};

        String[] valores = {
                "A", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "J", "Q", "K"
        };

        for (String palo : palos) {
            for (String valor : valores) {
                cartas.add(new Carta(valor, palo));
            }
        }
    }
    public void barajar() {
        Collections.shuffle(cartas);
    }
    public Carta tomarCarta() {

        if (cartas.isEmpty()) {
            return null;
        }

        return cartas.remove(0);

    }
    public int cantidadCartas() {
        return cartas.size();
    }


}

/*
crearMazo()
barajar()
tomarCarta()
agregarCarta()
estaVacio()
*/
