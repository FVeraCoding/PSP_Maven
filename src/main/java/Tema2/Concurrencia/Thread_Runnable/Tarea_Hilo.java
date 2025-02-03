
package Tema2.Concurrencia.Thread_Runnable;


public class Tarea_Hilo extends Thread{
    private int id;

    public Tarea_Hilo(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Hilo que extiende de Thread iniciado con ID: "+this.id);
    }   
}
