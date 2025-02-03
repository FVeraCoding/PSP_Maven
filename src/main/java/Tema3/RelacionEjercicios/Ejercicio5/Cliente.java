package Tema3.RelacionEjercicios.Ejercicio5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public Cliente(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void iniciar() throws IOException {
        System.out.println("Estableciendo conexion...");
        socket = new Socket(serverIP, serverPort);
        System.out.println("Conexion establecida.");
        
        is = socket.getInputStream();
        os = socket.getOutputStream();
        
        manejarChat();
    }

    private void manejarChat() throws IOException {
        Scanner sc = new Scanner(System.in);
        String mensajeEnviado;
        String mensajeRecibido;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        PrintWriter pw = new PrintWriter(os, true);

        while (true) {
            System.out.print("Cliente: ");
            mensajeEnviado = sc.nextLine();
            pw.println(mensajeEnviado);

            if (mensajeEnviado.equalsIgnoreCase("fin")) {
                System.out.println("Has finalizado la comunicacion.");
                break;
            }

            mensajeRecibido = br.readLine();
            
            if (mensajeRecibido.equalsIgnoreCase("fin")) {
                System.out.println("El servidor ha finalizado la comunicacion.");
                break;
            }
            System.out.println("Servidor: " + mensajeRecibido);
        }
        detener();
    }

    public void detener() throws IOException {
        System.out.println("Finalizando conexion...");
        is.close();
        os.close();
        socket.close();
        System.out.println("Conexion cerrada.");
    }

    public static void main(String[] args) {
        try {
            Cliente cliente = new Cliente("localhost", 8000);
            cliente.iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
