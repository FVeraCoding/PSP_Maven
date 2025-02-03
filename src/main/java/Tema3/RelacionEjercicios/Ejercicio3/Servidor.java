package Tema3.RelacionEjercicios.Ejercicio3;

import java.net.*;
import java.util.*;

public class Servidor {

    private int puerto;
    private int numeroSecreto;
    private DatagramSocket socket;
    private long tiempoInicio;

    
    public Servidor(int puerto) throws SocketException {
        this.puerto = puerto;
        this.numeroSecreto = new Random().nextInt(101); 
        this.socket = new DatagramSocket(puerto);
        System.out.println("Servidor iniciado. El numero secreto ha sido generado. ("+numeroSecreto+")");
    }

    
    public void procesarMensaje() throws Exception {
        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);

            String mensaje = new String(paquete.getData(), 0, paquete.getLength());
            System.out.println("Mensaje recibido: " + mensaje);

            
            tiempoInicio = System.currentTimeMillis();

            String[] partes = mensaje.split("#");
            if (partes.length == 2) {
                String nombre = partes[0].substring(1); 
                int numeroCliente = Integer.parseInt(partes[1]);

                
                String respuesta;
                if (numeroCliente == numeroSecreto) {
                    respuesta = "@acierto#" + numeroSecreto; 
                    socket.send(new DatagramPacket(respuesta.getBytes(), respuesta.length(), paquete.getAddress(), paquete.getPort()));
                    System.out.println("El cliente " + nombre + " ha acertado el numero!");
                    break; 
                } else {
                    respuesta = "@fallo#" + numeroCliente;  
                    socket.send(new DatagramPacket(respuesta.getBytes(), respuesta.length(), paquete.getAddress(), paquete.getPort()));
                }
            }

            
            long tiempoActual = System.currentTimeMillis();
            if (tiempoActual - tiempoInicio > 10000) {
                System.out.println("Han pasado 10 segundos sin recibir más mensajes. El juego ha terminado.");
                break;
            }
        }

        socket.close();
    }

    
    public static void main(String[] args) throws Exception {
        Servidor servidor = new Servidor(6200);
        servidor.procesarMensaje();
    }
}
