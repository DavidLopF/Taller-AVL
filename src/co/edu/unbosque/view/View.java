package co.edu.unbosque.view;

import java.util.Scanner;

public class View {

    private Scanner leer;

    public View() {
        leer = new Scanner(System.in);

    }

    public void mostrarDato(String mensaje) {
        System.out.println(mensaje);
    }

    public int capturarInt(String m) {
        int resultado = 0;
        System.out.println(m);
        String linea = leer.nextLine();
        while (!esNumero(linea) || Integer.parseInt(linea) <= 0) {
            System.out.println(m);
            linea = leer.nextLine();
        }

        resultado = Integer.parseInt(linea);
        return resultado;
    }

    public boolean esNumero(String m) {
        try {
            Integer.parseInt(m);
            return true;
        } catch (NumberFormatException nfe) {
            System.out.println("Entrada invalida.");
            return false;
        }
    }
}