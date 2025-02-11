package Tema5.ActividadesPropuestas;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Actividad5_3 {

    private static SecretKey generarClaveAES() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }


    public static void cifrarArchivo(String archivoEntrada, SecretKey claveAES) throws Exception {
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.ENCRYPT_MODE, claveAES);
        String archivoCifrado = "C:\\Users\\Fernando\\Desktop\\archivo_cifrado.aes";

        byte[] bytesEntrada = Files.readAllBytes(Paths.get(archivoEntrada));
        byte[] bytesCifrados = cifrador.doFinal(bytesEntrada);

        Files.write(Paths.get(archivoCifrado), bytesCifrados);
        Files.delete(Paths.get(archivoEntrada));
    }

    public static void descifrarArchivo(String archivoEntrada, SecretKey clave) throws Exception {
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        String archivoDescifrado = "C:\\Users\\Fernando\\Desktop\\archivo_descifrado.txt";

        byte[] bytesCifrados = Files.readAllBytes(Paths.get(archivoEntrada));
        byte[] bytesDescifrados = cifrador.doFinal(bytesCifrados);

        Files.write(Paths.get(archivoDescifrado), bytesDescifrados);
    }

    public static void main(String[] args) {
        try {
            String archivoOriginal = "C:\\Users\\Fernando\\Desktop\\archivo.txt";
            String archivoCifrado = "C:\\Users\\Fernando\\Desktop\\archivo_cifrado.aes";
            
            SecretKey claveAES = generarClaveAES();

            cifrarArchivo(archivoOriginal, claveAES);
            System.out.println("Archivo cifrado correctamente.");
            
            descifrarArchivo(archivoCifrado, claveAES);
            System.out.println("Archivo descifrado correctamente.");
                
        } catch (Exception ex) {
            Logger.getLogger(Actividad5_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
