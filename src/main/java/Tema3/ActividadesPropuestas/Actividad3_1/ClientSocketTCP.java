/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema3.ActividadesPropuestas.Actividad3_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class ClientSocketTCP {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    
    public ClientSocketTCP(String serverIP, int serverPort){
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    
    public void start() throws IOException{
        System.out.println("(Cliente) Estableciendo conexion...");
        socket = new Socket(serverIP, serverPort);
        os = socket.getOutputStream();
        is = socket.getInputStream();
        System.out.println("(Cliente) Conexion establecida.");
        
        this.enviarRuta();
        this.recibirImprimir();
        
        this.stop();
    }
    
    public void stop() throws IOException{
        System.out.println("(Cliente) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        System.out.println("(Cliente) Conexiones cerradas.");
    }
    
    public void enviarRuta(){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(os, true);
        System.out.println("Escribe la ruta absoluta del archivo que quieres que se lea en el servidor.");
        String ruta = sc.nextLine();
        
        pw.println(ruta);   
    }
    
    public void recibirImprimir() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String linea = br.readLine();
        
        while(!linea.equalsIgnoreCase("FIN")){
            System.out.println(linea);
            linea = br.readLine();
        }       
    }
    
    public static void main(String[] args) {
        try {
            ClientSocketTCP socketCliente = new ClientSocketTCP("localhost", 5050);
            socketCliente.start();
        } catch (IOException ex) {
            Logger.getLogger(ClientSocketTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
