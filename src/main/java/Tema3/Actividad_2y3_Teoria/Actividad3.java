
package Tema3.Actividad_2y3_Teoria;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Actividad3 {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String ip;
            
            System.out.println("Introduce la direccion IP: ");
            ip = sc.nextLine();
            
            InetAddress address = InetAddress.getByName(ip);
            
            System.out.println("La URL asociada a la direccion IP introducida es: "+address.getHostName());
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Actividad3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
