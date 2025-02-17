
package Tema5.RepasoExamen.CalcularHash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CalcularHash2 {
    
    public static String calcularHASH(File archivo) throws NoSuchAlgorithmException, FileNotFoundException, IOException{
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        
        try(FileInputStream fis = new FileInputStream(archivo)){
            byte[] buffer = new byte[1024];
            int bytesLeidos = fis.read(buffer);
            
            while(bytesLeidos != -1){
                digest.update(buffer, 0, bytesLeidos);
                bytesLeidos = fis.read(buffer);
            }
            
            byte[] hashBytes = digest.digest();
            StringBuilder hashCalculado = new StringBuilder();
            
            for(byte b : hashBytes){
                hashCalculado.append(String.format("%02x", b));
            }
            
            System.out.println("Hash calculado: "+hashCalculado.toString());
            return hashCalculado.toString();
            
        }
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        File archivo = new File("C:\\Users\\Fernando\\Desktop\\archivo.txt");
        calcularHASH(archivo);
    }
    
}
