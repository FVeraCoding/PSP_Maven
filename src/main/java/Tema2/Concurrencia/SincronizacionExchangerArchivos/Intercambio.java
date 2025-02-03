
package Tema2.Concurrencia.SincronizacionExchangerArchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Exchanger;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Intercambio {
    
    private BufferedReader lector;
    private PrintWriter escritor;
    private Exchanger<String> intercambiador;
    private String linea;
    private String mensaje;
    private boolean finArchivo = false;
    private File archivo1;
    private File archivo2;
    private int contador = 1;

    public Intercambio(File archivo1, File archivo2) {
        this.archivo1 = archivo1;
        this.archivo2 = archivo2;
        
        try {
            lector = new BufferedReader(new FileReader(archivo1));
            escritor = new PrintWriter(new FileWriter(archivo2, true));
            intercambiador = new Exchanger<>();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Intercambio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Intercambio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void leer(){
        try {
            linea = lector.readLine();
            while(linea != null){
                mensaje = intercambiador.exchange(linea);
                linea = lector.readLine();
            }
            intercambiador.exchange(null);
        } catch (IOException ex) {
            Logger.getLogger(Intercambio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Intercambio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(Intercambio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void escribir(){
        try {
            mensaje = intercambiador.exchange(null);
            while(mensaje != null){
                            escritor.println(mensaje);
                            System.out.println("Linea "+contador+" escrita con exito");
                            contador++;
                            mensaje=intercambiador.exchange(null);
            }
                
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Intercambio.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            escritor.close();
        }
    }
}
