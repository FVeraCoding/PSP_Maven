
package Tema3.ActividadesPropuestas.Actividad3_1_Repaso;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SocketCliente {
    String serverIP;
    int serverPort;
    Socket socket;
    InputStream is;
    OutputStream os;

    public SocketCliente(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    
    public void start() throws IOException{
        System.out.println("Estableciendo conexion con el host de direccion "+serverIP+" en el puerto "+serverPort);
        socket = new Socket(serverIP, serverPort);
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("Conexion establecida");
    }
    
    public void mandarMensaje(){
        PrintWriter pw = new PrintWriter(os, true);
        pw.println("Hola");
    }
    
    public void stop() throws IOException{
        System.out.println("Cerrando el socket...");
        is.close();
        os.close();
        socket.close();
        System.out.println("Conexion terminada y socket cerrado correctamente.");
    }
    
    public static void main(String[] args) {
        try {
            SocketCliente socketCliente = new SocketCliente("localhost", 9080);
            
            socketCliente.start();
            
            socketCliente.mandarMensaje();
            
            socketCliente.stop();
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
