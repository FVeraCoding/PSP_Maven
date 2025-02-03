package Tema2.ActividadesPropuestas.Actividad2_7;

import java.util.concurrent.CopyOnWriteArrayList;

public class GestorHojas extends Thread {

    private static final CopyOnWriteArrayList<String> lista = new CopyOnWriteArrayList<>();

    @Override
    public void run() {
        while (true) {
            synchronized (lista) {
                if (lista.size() >= 10) {
                    lista.remove(0);
                } else if (lista.size() < 10) {
                    lista.add("Texto");
                }

                for (String string : lista) {
                    // Recorriendo la lista.
                }
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            synchronized (lista) {
                lista.add("Texto");
            }
        }

        for (int i = 0; i < 100; i++) {
            new GestorHojas().start();
        }
    }
}
