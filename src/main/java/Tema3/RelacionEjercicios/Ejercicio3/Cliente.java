package Tema3.RelacionEjercicios.Ejercicio3;
import java.net.*;
import java.util.*;

public class Cliente {

    private String nombre;
    private String ipServidor;
    private int puertoServidor;
    private DatagramSocket socket;
    private InetAddress servidor;

    public Cliente(String nombre, String ipServidor, int puertoServidor) throws SocketException, UnknownHostException {
        this.nombre = nombre;
        this.ipServidor = ipServidor;
        this.puertoServidor = puertoServidor;
        this.socket = new DatagramSocket();
        this.servidor = InetAddress.getByName(ipServidor);
        System.out.println("Cliente " + nombre + " iniciado. Conectando al servidor...");
    }

    public void enviarPropuesta(int numero) throws Exception {
        String mensaje = "@" + nombre + "#" + numero;
        DatagramPacket paquete = new DatagramPacket(mensaje.getBytes(), mensaje.length(), servidor, puertoServidor);
        socket.send(paquete);
    }

    public String recibirRespuesta() throws Exception {
        byte[] buffer = new byte[1024];
        DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
        socket.receive(respuesta);
        return new String(respuesta.getData(), 0, respuesta.getLength());
    }

    
    public void jugar() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Introduce un numero (0-100): ");
            int numero = scanner.nextInt();

            enviarPropuesta(numero);

            String respuestaStr = recibirRespuesta();
            System.out.println("Respuesta del servidor: " + respuestaStr);

            if (respuestaStr.contains("@acierto#")) {
                System.out.println("Has adivinado el numero. El juego ha terminado.");
                break;
            }
        }

        socket.close();
    }

    
    public static void main(String[] args) throws Exception {
       
        Cliente cliente = new Cliente("Cliente", "localhost", 6200);
        cliente.jugar();
    }
}
