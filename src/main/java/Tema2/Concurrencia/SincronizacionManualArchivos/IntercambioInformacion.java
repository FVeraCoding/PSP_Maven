package Tema2.Concurrencia.SincronizacionManualArchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IntercambioInformacion {

    private String linea;
    private BufferedReader lector;
    private PrintWriter escritor;
    private boolean lineaLeida = false;
    private boolean finLectura = false;

    public IntercambioInformacion(File archivoLectura, File archivoEscritura) {
        try {
            lector = new BufferedReader(new FileReader(archivoLectura));
            escritor = new PrintWriter(new FileWriter(archivoEscritura, true));
        } catch (IOException ex) {
            Logger.getLogger(IntercambioInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized String leerLinea() {
        while (lineaLeida) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(IntercambioInformacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            linea = lector.readLine();
            if (linea == null) { 
                finLectura = true;
                notifyAll();
                return null;
            }
            
            System.out.println("Linea leida: " + linea);
            lineaLeida = true;
            notify();
        } catch (IOException ex) {
            Logger.getLogger(IntercambioInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linea;
    }

    public synchronized void escribirLinea() {
        while (!lineaLeida && !finLectura) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(IntercambioInformacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (finLectura && linea == null) {
            notifyAll();
            return;
        }

        escritor.println(linea);
        System.out.println("Linea escrita: " + linea);
        lineaLeida = false;
        notify();
    }

    public void cerrarArchivos() {
        try {
            if (lector != null) lector.close();
            if (escritor != null) escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(IntercambioInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLinea() {
        return linea;
    }
    
    
}
