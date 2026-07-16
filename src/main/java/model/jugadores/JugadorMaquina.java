package model.jugadores;

import model.Carta;

public class JugadorMaquina extends Jugador {

    public JugadorMaquina(String nombre) {
        super(nombre);
    }

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

    private int obtenerValorCarta(Carta carta, int sumaMesa) {
        switch (carta.getValor()) {
            case "A": return (sumaMesa <= 40) ? 10 : 1;
            case "9": return 0;
            case "J": case "Q": case "K": return -10;
            default: return Integer.parseInt(carta.getValor());
        }
    }
}