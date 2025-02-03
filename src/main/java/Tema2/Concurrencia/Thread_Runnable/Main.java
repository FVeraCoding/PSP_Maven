
package Tema2.Concurrencia.Thread_Runnable;


public class Main {
    public static void main(String[] args) {
        
        
        //Se crean 25 hilos de la clase Tarea_Hilo y se inicializan con el método start();
        for(int i = 0; i<25; i++){
            new Tarea_Hilo(i).start();
        }
        
        //Se crean 25 hilos de la clase Tarea_Runnable y se inicializan con el método start();
        for(int i = 0; i<25; i++){
            new Thread(new Tarea_Runnable(i)).start();
        }
        
    }
}
