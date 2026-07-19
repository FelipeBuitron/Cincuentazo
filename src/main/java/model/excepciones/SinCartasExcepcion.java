package model.excepciones;

public class SinCartasExcepcion extends RuntimeException {

    public SinCartasExcepcion(String mensaje) {
        super(mensaje);
    }

}