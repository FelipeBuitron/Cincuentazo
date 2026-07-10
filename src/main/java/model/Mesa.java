package model;

import java.util.ArrayList;

public class Mesa {

    private ArrayList<Carta> cartas;

    public Mesa() {
        cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public Carta getUltimaCarta() {

        if (cartas.isEmpty()) {
            return null;
        }

        return cartas.get(cartas.size() - 1);
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
}