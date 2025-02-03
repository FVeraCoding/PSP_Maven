package Tema2.Concurrencia.SincronizacionExhanger5Tareas;

import java.util.concurrent.Exchanger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Departamento implements Runnable {

    private Exchanger<String> entrada;
    private Exchanger<String> salida;
    private String nombreDepartamento;

    public Departamento(Exchanger<String> entrada, Exchanger<String> salida, String nombreDepartamento) {
        this.entrada = entrada;
        this.salida = salida;
        this.nombreDepartamento = nombreDepartamento;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.nombreDepartamento + " ha iniciado su tarea...");
            Thread.sleep(500);

            String mensajeEnviado = this.nombreDepartamento + " ha finalizado su tarea.";
            String mensajeRecibido = "confirmacion de finalizacion de tarea por parte de: " + this.nombreDepartamento;

            
            if (this.salida != null) {
                System.out.println(this.nombreDepartamento+" ha recibido la "+salida.exchange(mensajeEnviado));
            }

            Thread.sleep(500);
            if (this.entrada != null) {
                System.out.println(entrada.exchange(mensajeRecibido));
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
