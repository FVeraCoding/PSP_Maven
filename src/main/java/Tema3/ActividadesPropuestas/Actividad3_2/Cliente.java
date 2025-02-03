package Tema3.ActividadesPropuestas.Actividad3_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {
        try {
            enviarDatagramas();
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void enviarDatagramas() throws UnknownHostException, SocketException, IOException {
        InetAddress hostServidor = InetAddress.getByName("localhost");
        int puertoServidor = 49171;
        DatagramSocket socket = new DatagramSocket();

        for (int i = 0; i < 10000; i++) {
            byte[] mensaje = new String("Mensaje: " + i).getBytes();
            DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puertoServidor);
            socket.send(peticion);
        }

        
        
        byte[] mensaje = new String("FIN").getBytes();
        DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puertoServidor);
        socket.send(peticion);
        
        System.out.println("Datagramas enviados correctamente.");

    }
}
