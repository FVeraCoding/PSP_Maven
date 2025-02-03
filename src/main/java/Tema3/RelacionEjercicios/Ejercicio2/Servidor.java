package Tema3.RelacionEjercicios.Ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Servidor {

    int puerto = 12345;
    String nombreServidor = "ServidorUDP";
    DatagramSocket socket;
    Scanner scanner;

    public Servidor() {
        try {
            socket = new DatagramSocket(puerto);
            scanner = new Scanner(System.in); 
            System.out.println("Servidor UDP iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recibir() {
        try {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                if (mensaje.equals(".")) {
                    System.out.println("Conversación terminada.");
                    continue;
                }

                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                if (mensaje.startsWith("@hola#")) {
                    String response = "@hola#" + nombreServidor + "@";
                    enviarRespuesta(response, direccionCliente, puertoCliente);
                    System.out.println("Saludo enviado al cliente.");
                } else {
                    
                    System.out.print("Escribe tu respuesta: ");
                    String respuestaServidor = scanner.nextLine();  

                    enviarRespuesta(respuestaServidor, direccionCliente, puertoCliente);
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void enviarRespuesta(String respuesta, InetAddress direccionCliente, int puertoCliente) {
        try {
            byte[] respuestaBytes = respuesta.getBytes();
            DatagramPacket paqueteRespuesta = new DatagramPacket(respuestaBytes, respuestaBytes.length, direccionCliente, puertoCliente);
            socket.send(paqueteRespuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.recibir();
    }
}
