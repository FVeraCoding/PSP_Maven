package Tema3.RelacionEjercicios.Ejercicio6;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    int serverPort;
    String serverIP;
    Socket socket;
    InputStream is;
    OutputStream os;

    public Cliente(int puerto, String serverIP) {
        this.serverIP = serverIP;
        this.serverPort = puerto;
    }

    public void start() throws IOException {
        socket = new Socket(this.serverIP, this.serverPort);
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("Conectado al servidor en " + serverIP + ":" + serverPort);
    }

    public void enviarNombreArchivo(String nombreArchivo) {
        PrintWriter pw = new PrintWriter(os, true);
        pw.println(nombreArchivo);
        System.out.println("Nombre del archivo enviado: " + nombreArchivo);
    }

    public String recibirCabecera() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br.readLine();
    }

    public void recibirArchivo(String cabecera) throws IOException {
        String[] partesCabecera = cabecera.split(",");
        long tamanoArchivo = Long.parseLong(partesCabecera[0]);
        String nombreArchivo = partesCabecera[1];

        System.out.println("Recibiendo archivo: " + nombreArchivo + " (" + tamanoArchivo + " bytes)");

        File archivo = new File("C:\\Users\\Fernando\\Desktop\\" + nombreArchivo);
        try (FileOutputStream fos = new FileOutputStream(archivo);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;
            long totalLeido = 0;

            while (totalLeido < tamanoArchivo && (bytesLeidos = is.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesLeidos);
                totalLeido += bytesLeidos;
            }

            bos.flush();
            System.out.println("Archivo recibido y guardado como: " + archivo.getAbsolutePath());
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
            Cliente cliente = new Cliente(6060, "localhost");
            cliente.start();

            cliente.enviarNombreArchivo("prueba.txt");

            String cabecera = cliente.recibirCabecera();
            if (cabecera.startsWith("ERROR")) {
                System.out.println("Error recibido del servidor: " + cabecera);
            } else {
                cliente.recibirArchivo(cabecera);
            }

            cliente.stop();
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
