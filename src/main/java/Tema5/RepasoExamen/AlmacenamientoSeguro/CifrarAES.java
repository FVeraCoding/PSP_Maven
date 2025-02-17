

package Tema5.RepasoExamen.AlmacenamientoSeguro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class CifrarAES {
    public static SecretKey generarClave() throws NoSuchAlgorithmException{
        KeyGenerator clave = KeyGenerator.getInstance("AES");
        clave.init(128);
        return clave.generateKey();
    }
    
    public static File cifrarArchivo(File archivoOriginal, SecretKey clave) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException{
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        File archivoCifrado = new File("C:\\Users\\Fernando\\Desktop\\archivoCifrado.aes");
        
        try(FileInputStream fis = new FileInputStream(archivoOriginal);
                FileOutputStream fos = new FileOutputStream(archivoCifrado)){
            
            byte[] bytesOriginales = fis.readAllBytes();
            byte[] bytesCifrados = cifrador.doFinal(bytesOriginales);
            fos.write(bytesCifrados);
            System.out.println("Archivo cifrado con éxito.");
            
            return archivoCifrado;
        }
    }
    
    public static File descifrarArchivo(File archivoCifrado, SecretKey clave) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException{
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        File archivoDescifrado = new File("C:\\Users\\Fernando\\Desktop\\archivoDescifrado.txt");
        
        try(FileInputStream fis = new FileInputStream(archivoCifrado);
                FileOutputStream fos = new FileOutputStream(archivoDescifrado)){
            
            byte[] bytesCifrados = fis.readAllBytes();
            byte[] bytesDescifrados = cifrador.doFinal(bytesCifrados);
            fos.write(bytesDescifrados);
            System.out.println("Archivo descifrado con exito");
            
            return archivoDescifrado;
        }
    }
    
    public static void main(String[] args){
        try {
            File archivoOriginal = new File("C:\\Users\\Fernando\\Desktop\\archivo.txt");
            SecretKey clave = generarClave();
            File archivoCifrado = cifrarArchivo(archivoOriginal, clave);
            File archivoDescifrado = descifrarArchivo(archivoCifrado, clave);
            
            
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CifrarAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CifrarAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CifrarAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CifrarAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CifrarAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CifrarAES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
