
package Tema2.Concurrencia.SincronizacionExchangerArchivos;


public class Lector implements Runnable{
    private Intercambio intercambio;

    public Lector(Intercambio intercambio) {
        this.intercambio = intercambio;
    }

    @Override
    public void run() {

            this.intercambio.leer();
            

    }
    
    
}
