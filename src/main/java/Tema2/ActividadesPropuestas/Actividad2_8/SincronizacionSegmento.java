
package Tema2.ActividadesPropuestas.Actividad2_8;

import java.util.logging.Level;
import java.util.logging.Logger;


public class SincronizacionSegmento extends Thread{
    int id;
    static Object bloqueo = new Object();
    

    public SincronizacionSegmento(int id) {
        this.id = id;
    }
    
    public void metodo1(){
        synchronized(bloqueo){
            System.out.println("Comienzo del metodo 1 del hilo "+id);
        }
        
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ex){
            return;
        }
        
        System.out.println("Fin del metodo 1 del hilo "+id);
    }
    
    public void metodo2(){
        synchronized(bloqueo){
            System.out.println("Comienzo del metodo 2 del hilo "+id);
        }
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SincronizacionSegmento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Fin del metodo 2 del hilo "+id);
    }

    @Override
    public void run() {
        if(id==1){
            metodo1();
            metodo2();
        }else{
            metodo2();
            metodo1();
        }
    }
    
    public static void main(String[] args) {
        new SincronizacionSegmento(1).start();
        new SincronizacionSegmento(2).start();
    }
    
    
}
