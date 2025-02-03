/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema3.ActividadesPropuestas.Actividad3_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSocketTCP {

    private ServerSocket socketServidor;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    
    public ServerSocketTCP(int puerto) throws IOException{
        this.socketServidor = new ServerSocket(puerto);
    }
    
    public void start() throws IOException{
        System.out.println("(Servidor) Esperando conexiones...");
        socket = socketServidor.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();        
        System.out.println("(Servidor) Conexion establecida.");
        
        String ruta = leerRuta();
        leerEnviar(ruta);
        
        stop();
    }
    
    public void stop() throws IOException{
        System.out.println("(Servidor) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        socketServidor.close();
        System.out.println("(Servidor) Conexiones cerradas.");
    }
    
    
    public String leerRuta() throws IOException{
        BufferedReader brConexion = new BufferedReader(new InputStreamReader(is));
        
        String ruta = brConexion.readLine();
        
        return ruta;
    }
    
    public void leerEnviar(String ruta) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File(ruta)));

        PrintWriter pw = new PrintWriter(os, true);
        
        String linea = br.readLine();
        
        while(linea != null){
            pw.println(linea);
            linea = br.readLine();
        }
        
        pw.println("FIN");
        
        System.out.println("Mensaje leido y enviado correctamente.");
        
    }
    
    public static void main(String[] args) {
        try {
            ServerSocketTCP socketServidor = new ServerSocketTCP(5050);
            socketServidor.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerSocketTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}
