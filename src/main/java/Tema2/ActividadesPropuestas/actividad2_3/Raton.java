package Tema2.ActividadesPropuestas.actividad2_3;

import java.util.ArrayList;

public class Raton extends Thread {

    private String nombre;
    private int tiempoAlimentacion;

    public Raton(String nombre, int tiempoAlimentacion) {
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public void comer() {
        System.out.printf("El raton %s ha comenzado a alimentarse.%n", this.nombre);
        System.out.printf("El raton %s ha terminado de alimentarse.%n", this.nombre);
    }

    @Override
    public void run() {
        this.comer();
    }

    public static void main(String[] args) {
        Raton mickey = new Raton("Mickey", 6);
        ArrayList<Thread.State> estadosHilo = new ArrayList();
        Thread h = new Thread(mickey);
        estadosHilo.add(h.getState());
        h.start();

        while (h.getState() != Thread.State.TERMINATED) {
            if (!estadosHilo.contains(h.getState())) {
                estadosHilo.add(h.getState());
            }
        }

        if (!estadosHilo.contains(h.getState())) {
            estadosHilo.add(h.getState());
        }

        for (Thread.State estado : estadosHilo) {
            System.out.println(estado);
        }

    }

}
