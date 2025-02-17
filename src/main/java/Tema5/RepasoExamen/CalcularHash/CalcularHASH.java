package Tema5.RepasoExamen.CalcularHash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalcularHASH {

    public static String calcularHash(File archivo) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

        try (FileInputStream fis = new FileInputStream(archivo)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos = fis.read(buffer);
            
            
            while (bytesLeidos != -1) {
                digest.update(buffer, 0, bytesLeidos);
                bytesLeidos = fis.read(buffer);
            }
            
            byte[] hashBytes = digest.digest();
            StringBuilder hash = new StringBuilder();
            
            for(byte b : hashBytes){
                hash.append(String.format("%02x", b));
            }
            
            System.out.println("Hash calculado del archivo: "+hash.toString());
            
            return hash.toString();
        }
    }
    
    public static void main(String[] args) {
        try {
            File archivo = new File("C:\\Users\\Fernando\\Desktop\\archivo.txt");
            calcularHash(archivo);
            
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CalcularHASH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalcularHASH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
