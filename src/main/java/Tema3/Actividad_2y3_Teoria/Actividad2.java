
package Tema3.Actividad_2y3_Teoria;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Actividad2 {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String url;
            
            System.out.println("Introduce una url: ");
            url = sc.nextLine();
            
            InetAddress address = InetAddress.getByName(url);
            
            System.out.println("La direccion IP de la URL introducida es: "+address.getHostAddress());
            
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Actividad2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
