package Tema2.Concurrencia.SincronizacionManualArchivos;

public class Lector implements Runnable {

    private final IntercambioInformacion intercambio;

    public Lector(IntercambioInformacion intercambio) {
        this.intercambio = intercambio;
    }

    @Override
    public void run() {
        String linea;
        while ((linea = intercambio.leerLinea()) != null) {
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        intercambio.cerrarArchivos();
    }
}
