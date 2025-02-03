
package Tema3.ActividadesPropuestas.Actividad3_1_Repaso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SocketServidor {
    ServerSocket socketServidor;
    Socket socket;
    InputStream is;
    OutputStream os;

    public SocketServidor(int puerto) throws IOException {
        this.socketServidor = new ServerSocket(puerto);
    }
    
    public void start() throws IOException{
        System.out.println("Inicializando el socket servidor.");
        System.out.println("Esperando conexiones...");
        socket = socketServidor.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("Conexion establecida con exito.");
    }
    
    public void recibirMensaje() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println(br.readLine());
    }
    
    public void stop() throws IOException{
        System.out.println("Cerrando el socket servidor...");
        is.close();
        os.close();
        socket.close();
        socketServidor.close();
        System.out.println("El socket servidor se ha cerrado con exito.");
    }

    public static void main(String[] args) {
        try {
            SocketServidor socketServidor = new SocketServidor(9080);
            
            socketServidor.start();
            
            socketServidor.recibirMensaje();
            
            socketServidor.stop();
            
        } catch (IOException ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
