package Tema5.ActividadesPropuestas;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Actividad5_2 {
    
    public static String calcularHASH(File archivo) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        try (FileInputStream fis = new FileInputStream(archivo.getAbsolutePath())) {
            byte[] buffer = new byte[1024];
            int bytesLeidos = fis.read(buffer);
            
            while (bytesLeidos!= -1) {
                digest.update(buffer, 0, bytesLeidos);
                bytesLeidos = fis.read(buffer);    
            }
        }
        
        byte[] hashBytes = digest.digest();
        StringBuilder hexString = new StringBuilder();
        
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        
        System.out.println("HASH calculado del archivo: \n"+hexString.toString());
        
        return hexString.toString();
    }
    
    public static void main(String[] args) {
        File archivo = new File("C:\\Users\\Fernando\\Desktop\\eclipse.exe");    
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("Introduce el hash esperado del archivo: ");
            String hashEsperado = sc.nextLine();
            
            
            
            if(calcularHASH(archivo).equals(hashEsperado)){
                System.out.println("Ambos hash son iguales, la integridad del archivo esta verificada.");
            }else{
                System.out.println("Los hash son distintos. No se puede verificar la integridad del archivo.");
            }
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Actividad5_2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Actividad5_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
