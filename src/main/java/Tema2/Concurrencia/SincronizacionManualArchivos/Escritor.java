package Tema2.Concurrencia.SincronizacionManualArchivos;

public class Escritor implements Runnable {

    private final IntercambioInformacion intercambio;

    public Escritor(IntercambioInformacion intercambio) {
        this.intercambio = intercambio;
    }

    @Override
    public void run() {
        while (true) {
            intercambio.escribirLinea();
            try {
                Thread.sleep(500); 
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                break;
            }
            if (intercambio.getLinea() == null) {
                intercambio.cerrarArchivos(); 
                break;
            }
        }
    }
}
