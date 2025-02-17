
package Tema5.RepasoExamen.CalcularHash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CalcularHash3 {
    public static void main(String[] args) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            File archivo = new File("C:\\Users\\Fernando\\Desktop\\archivo.txt");
            
            try(FileInputStream fis = new FileInputStream(archivo)){
                
                byte[] buffer = new byte[1024];
                int bytesLeidos = fis.read(buffer);
                
                while(bytesLeidos != -1){
                    digest.update(buffer, 0, bytesLeidos);
                    bytesLeidos = fis.read(buffer);
                }
                
                byte[] bytesCifrados = digest.digest();
                StringBuilder hash = new StringBuilder();
                
                for(byte b : bytesCifrados){
                    hash.append(String.format("%02x", b));
                }
                
                System.out.println("Hash calculado: "+hash.toString());
                
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CalcularHash3.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CalcularHash3.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CalcularHash3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
