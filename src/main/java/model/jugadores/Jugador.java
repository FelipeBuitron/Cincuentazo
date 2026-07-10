package model.jugadores;

import model.Carta;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<Carta> mano;
    private boolean eliminado;

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
    public void agregarCarta(Carta carta) {
        mano.add(carta);
    }
    public int cantidadCartas() {
        return mano.size();
    }
    public Carta quitarCarta(int indice) {
        return mano.remove(indice);
    }
}
