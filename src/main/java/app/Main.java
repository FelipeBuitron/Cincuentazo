package app;
import model.Carta;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Carta carta = new Carta("K", "Picas");

        System.out.println(carta.getValor());
        System.out.println(carta.getPalo());
    }
}