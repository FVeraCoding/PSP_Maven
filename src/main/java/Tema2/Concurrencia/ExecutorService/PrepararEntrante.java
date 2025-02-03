package Tema2.Concurrencia.ExecutorService;

import java.util.logging.Level;
import java.util.logging.Logger;


public class PrepararEntrante implements Runnable {

    private String entrante;

    public PrepararEntrante(String entrante) {
        this.entrante = entrante;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("Entrante listo: "+this.entrante);
        } catch (InterruptedException ex) {
            Logger.getLogger(PrepararEntrante.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
