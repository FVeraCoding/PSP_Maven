package Tema5.ActividadesPropuestas;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;

public class Actividad5_3 {
    public static void main(String[] args) throws Exception {
        SecretKey claveAES = generarClaveAES();
        
        File archivoOriginal = new File("C:\\Users\\Fernando\\Desktop\\archivo.txt");
        File archivoCifrado = new File("C:\\Users\\Fernando\\Desktop\\archivoCifrado.aes");
        File archivoDescifrado = new File("C:\\Users\\Fernando\\Desktop\\archivo_descifrado.txt");
        
        cifrarArchivo(archivoOriginal, archivoCifrado, claveAES);
        System.out.println("Archivo cifrado correctamente.");
        
        descifrarArchivo(archivoCifrado, archivoDescifrado, claveAES);
        System.out.println("Archivo descifrado correctamente.");
    }
    
    public static SecretKey generarClaveAES() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }
    
    public static void cifrarArchivo(File archivoEntrada, File archivoSalida, SecretKey claveAES) throws Exception {
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.ENCRYPT_MODE, claveAES);
        
        byte[] datos = leerArchivo(archivoEntrada);
        byte[] datosCifrados = cifrador.doFinal(datos);
        guardarArchivo(archivoSalida, datosCifrados);
    }
    
    public static void descifrarArchivo(File archivoEntrada, File archivoSalida, SecretKey claveAES) throws Exception {
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.DECRYPT_MODE, claveAES);
        
        byte[] datosCifrados = leerArchivo(archivoEntrada);
        byte[] datosDescifrados = cifrador.doFinal(datosCifrados);
        guardarArchivo(archivoSalida, datosDescifrados);
    }
    
    public static byte[] leerArchivo(File nombre) throws IOException {
        return new FileInputStream(nombre).readAllBytes();
    }
    
    public static void guardarArchivo(File nombre, byte[] datos) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(nombre)) {
            fos.write(datos);
        }
    }
}
