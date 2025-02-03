
package Tema2.Concurrencia.SincronizacionManualArchivos;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


public class main {
    public static void main(String[] args) {
        File archivo1 = new File("C:\\Users\\Fernando\\Desktop\\archivo1.txt");
        File archivo2 = new File("C:\\Users\\Fernando\\Desktop\\archivo2.txt");
        
        IntercambioInformacion intercambio1 = new IntercambioInformacion(archivo1, archivo2);
        Lector lector = new Lector(intercambio1);
        Escritor escritor = new Escritor(intercambio1);
        
        Thread hiloLector = new Thread(lector);
        Thread hiloEscritor = new Thread(escritor);
        
        
        hiloLector.start();
        hiloEscritor.start();
        
        try {
            hiloLector.join();
            hiloEscritor.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
