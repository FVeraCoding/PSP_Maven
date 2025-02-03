package Tema3.ActividadesPropuestas.Actividad3_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {
        try {
            leerDatagramas();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void leerDatagramas() throws SocketException, IOException {
        DatagramSocket socket = new DatagramSocket(49171);
        byte[] bufferLectura = new byte[64];
        DatagramPacket datagramaEntrada = new DatagramPacket(bufferLectura, bufferLectura.length);

        PrintWriter pw = new PrintWriter(new FileWriter(new File("C:\\Users\\Fernando\\Desktop\\datagramas.txt")), true);

        while (true) {

            socket.receive(datagramaEntrada);
            String mensajeRecibido = new String(bufferLectura);

            if (mensajeRecibido.equalsIgnoreCase("FIN")) {
                break;
            }

            pw.println(mensajeRecibido);
            pw.flush();
        }

        socket.close();
    }
}
