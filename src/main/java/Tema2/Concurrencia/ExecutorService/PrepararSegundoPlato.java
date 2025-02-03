
package Tema2.Concurrencia.ExecutorService;

import java.util.logging.Level;
import java.util.logging.Logger;


public class PrepararSegundoPlato implements Runnable{

    private String segundoPlato;

    public PrepararSegundoPlato(String segundoPlato) {
        this.segundoPlato = segundoPlato;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(4000);
            System.out.println("Segundo Plato listo: "+this.segundoPlato);
        } catch (InterruptedException ex) {
            Logger.getLogger(PrepararSegundoPlato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
