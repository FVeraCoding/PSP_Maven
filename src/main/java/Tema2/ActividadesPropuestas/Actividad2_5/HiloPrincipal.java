
package Tema2.ActividadesPropuestas.Actividad2_5;

import java.util.logging.Level;
import java.util.logging.Logger;


public class HiloPrincipal extends Thread{

    public HiloPrincipal(String name) {
        super(name);
    }

    
    @Override
    public void run(){
        
        while(true){
            System.out.println("Soy el bucle "+this.getName()+" y estoy trabajando.");
            try {
                Thread.sleep((int) (Math.random() * 10000) + 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}

