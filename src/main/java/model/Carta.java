package model;

public class Carta {

    private final String valor;
    private final String palo;
    private boolean bocaArriba;

    public Carta(String valor, String palo) {
        this.valor = valor;
        this.palo = palo;
        this.bocaArriba = false;
    }

    public String getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }

    public boolean isBocaArriba() {
        return bocaArriba;
    }

    public void setBocaArriba(boolean bocaArriba) {
        this.bocaArriba = bocaArriba;
    }

    public String getNombreImagen() {
        return valor + palo + ".png";
    }

    public String getRutaImagen() {
        return "/cards/" + getNombreImagen();
    }

}
