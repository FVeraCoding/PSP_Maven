/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema2.Concurrencia.SincronizacionExchangerArchivos;


import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class Main {
    public static void main(String[] args) {
        File archivo1 = new File("C:\\Users\\Fernando\\Desktop\\archivo1.txt");
        File archivo2 = new File("C:\\Users\\Fernando\\Desktop\\archivo2.txt");
        
        Intercambio intercambio1 = new Intercambio(archivo1, archivo2);
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
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
