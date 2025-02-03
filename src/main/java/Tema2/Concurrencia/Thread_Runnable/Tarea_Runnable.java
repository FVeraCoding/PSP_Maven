
package Tema2.Concurrencia.Thread_Runnable;


public class Tarea_Runnable implements Runnable{

    private int id;

    public Tarea_Runnable(int id) {
        this.id = id;
    } 
    
    @Override
    public void run() {
        System.out.println("Hilo que implementa Runnable iniciado con id: "+this.id);
    }
    
}
