package model;
/**
 * Representa una carta del juego.
 *
 * Cada carta posee un valor, un palo y un estado
 * que indica si está boca arriba.
 *
 * @author Andrés Felipe Escobar Buitrón
 * @author Carlos Delgado
 * @version 1.0
 */
public class Carta {

    private final String valor;
    private final String palo;
    private boolean bocaArriba;
    /**
     * Crea una nueva carta.
     *
     * @param valor Valor de la carta.
     * @param palo Palo de la carta.
     */
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
    /**
     * Obtiene el nombre del archivo de imagen de la carta.
     *
     * @return Nombre de la imagen.
     */
    public String getNombreImagen() {
        return valor + palo + ".png";
    }
    /**
     * Obtiene la ruta de la imagen correspondiente a la carta.
     *
     * @return Ruta de la imagen.
     */
    public String getRutaImagen() {
        return "/cards/" + getNombreImagen();
    }

}
