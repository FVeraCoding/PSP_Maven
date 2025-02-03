
package Tema2.Concurrencia.SincronizacionExchangerArchivos;


public class Escritor implements Runnable{
    
    Intercambio intercambio;

    public Escritor(Intercambio intercambio) {
        this.intercambio = intercambio;
    }
    
    

    @Override
    public void run() {

            this.intercambio.escribir();

    }
    
}
