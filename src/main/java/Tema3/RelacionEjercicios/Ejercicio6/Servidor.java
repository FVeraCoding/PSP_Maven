package Tema3.RelacionEjercicios.Ejercicio6;

import java.io.*;
import java.net.*;

public class Servidor {

    ServerSocket servidor;
    Socket socket;
    InputStream is;
    OutputStream os;

    public Servidor(int puerto) throws IOException {
        servidor = new ServerSocket(puerto);
    }

    public void start() throws IOException {
        System.out.println("Esperando conexiones en el puerto " + servidor.getLocalPort());
        socket = servidor.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("Cliente conectado desde: " + socket.getInetAddress());
    }

    public String recibirNombreArchivo() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br.readLine();
    }

    public void enviarArchivo(String nombreArchivo) throws IOException {
        File archivo = new File("C:\\Users\\Fernando\\Desktop\\" + nombreArchivo);


        long tamanoArchivo = archivo.length();
        PrintWriter pw = new PrintWriter(os, true);
        pw.println(tamanoArchivo + "," + archivo.getName());

        try (FileInputStream fit = new FileInputStream(archivo)) {
            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while ((bytesLeidos = fit.read(buffer)) != -1) {
                os.write(buffer, 0, bytesLeidos);
            }

            os.flush();
            System.out.println("Archivo enviado correctamente: " + archivo.getName());
        }
    }

    public void stop() throws IOException {
        is.close();
        os.close();
        socket.close();
        System.out.println("Conexión cerrada.");
    }
    
    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor(6060);
            servidor.start();

            String nombreArchivo = servidor.recibirNombreArchivo();
            System.out.println("Solicitud de archivo recibida: " + nombreArchivo);

            servidor.enviarArchivo(nombreArchivo);
            servidor.stop();
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}