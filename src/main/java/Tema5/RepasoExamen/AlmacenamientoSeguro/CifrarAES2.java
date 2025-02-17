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

public class CifrarAES2 {

    public static File cifrarArchivo(File archivoOriginal, SecretKey clave) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        
        File archivoCifrado = new File("C:\\Users\\Feranndo\\Desktop\\archivoCifrado.aes");

        //Abrimos flujos de archivos.
        try (FileInputStream fis = new FileInputStream(archivoOriginal);
                FileOutputStream fos = new FileOutputStream(archivoCifrado)){
            
            //Leemos los bytes del archivo original.
            byte[] bytesOriginales = fis.readAllBytes();
            
            //Los ciframos a través del cifrador.
            byte[] bytesCifrados = cifrador.doFinal(bytesOriginales);
            
            //Creamos el archivo cifrado.
            fos.write(bytesCifrados);
            
        }
        return archivoCifrado;
    }
    

    public static File descifrarArchivo(File archivoCifrado, SecretKey clave) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cifrador = Cipher.getInstance("SHA-256");
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        
        File archivoDescifrado = new File("C:\\Users\\Fernando\\Desktop\\archivoDescifrado.txt");
        
        try(FileInputStream fis = new FileInputStream(archivoCifrado);
                FileOutputStream fos = new FileOutputStream(archivoDescifrado)){
            
            byte[] bytesCifrados = fis.readAllBytes();
            byte[] bytesDescifrados = cifrador.doFinal(bytesCifrados);
            
            fos.write(bytesCifrados);
            
        }
        return archivoDescifrado;
    }

    public static SecretKey generarClave() throws NoSuchAlgorithmException {
        KeyGenerator clave = KeyGenerator.getInstance("AES");
        clave.init(128);

        return clave.generateKey();
    }

    public static void main(String[] args) {
        try {
            File archivoOriginal = new File("C:\\Users\\Fernando\\Desktop\\archivo.txt");
            SecretKey clave = generarClave();
            File archivoCifrado = cifrarArchivo(archivoOriginal, clave);
            File archivoDescifrado = descifrarArchivo(archivoCifrado, clave);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CifrarAES2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CifrarAES2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CifrarAES2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CifrarAES2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CifrarAES2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CifrarAES2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
