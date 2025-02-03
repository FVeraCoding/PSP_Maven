package Tema3.RelacionEjercicios.Ejercicio5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private ServerSocket server;
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public Servidor(int port) throws IOException {
        this.server = new ServerSocket(port);
        System.out.println("Servidor iniciado en el puerto " + port);
    }

    public void iniciar() throws IOException {
        System.out.println("Esperando conexion...");
        socket = server.accept();
        System.out.println("Conexion establecida con el cliente.");
        
        is = socket.getInputStream();
        os = socket.getOutputStream();        

        manejarChat();
    }

    private void manejarChat() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        PrintWriter pw = new PrintWriter(os, true);
        Scanner sc = new Scanner(System.in);
        
        String mensajeRecibido;
        String mensajeEnviado;

        while (true) {
            
            mensajeRecibido = br.readLine();
            if (mensajeRecibido.equalsIgnoreCase("fin")) {
                System.out.println("El cliente ha finalizado la comunicacion.");
                break;
            }
            System.out.println("Cliente: " + mensajeRecibido);
            
            
            System.out.print("Servidor: ");
            mensajeEnviado = sc.nextLine();
            pw.println(mensajeEnviado);
            
            if (mensajeEnviado.equalsIgnoreCase("fin")) {
                System.out.println("Has finalizado la comunicacion.");
                break;
            }
        }
        cerrarConexion();
    }

    private void cerrarConexion() throws IOException {
        System.out.println("Cerrando la conexion...");
        is.close();
        os.close();
        socket.close();
        server.close();
        System.out.println("Conexion cerrada.");
    }

    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor(8000);
            servidor.iniciar();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
