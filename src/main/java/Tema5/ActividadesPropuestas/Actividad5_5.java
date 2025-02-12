package Tema5.ActividadesPropuestas;

import java.io.*;
import java.security.*;

public class Actividad5_5 {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator generadorClaves = KeyPairGenerator.getInstance("RSA");
        generadorClaves.initialize(2048);
        KeyPair claves = generadorClaves.generateKeyPair();
        
        File archivoOriginal = new File("C:\\Users\\Fernando\\Desktop\\archivo.txt");
        File archivoFirmado = new File("C:\\Users\\Fernando\\Desktop\\archivoFirmado.dat");
        
        byte[] datos = leerArchivo(archivoOriginal);
        byte[] firma = firmarDatos(datos, claves.getPrivate());
        
        guardarArchivo(archivoFirmado, firma);
        
        boolean esValida = verificarFirma(datos, firma, claves.getPublic());
        System.out.println("Firma valida: " + esValida);
    }
    
    public static byte[] leerArchivo(File archivo) throws IOException {
        return new FileInputStream(archivo).readAllBytes();
    }
    
    public static void guardarArchivo(File archivo, byte[] datos) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(archivo)) {
            fos.write(datos);
        }
    }
    
    public static byte[] firmarDatos(byte[] datos, PrivateKey clavePrivada) throws Exception {
        Signature firma = Signature.getInstance("SHA256withRSA");
        firma.initSign(clavePrivada);
        firma.update(datos);
        return firma.sign();
    }
    
    public static boolean verificarFirma(byte[] datos, byte[] firmaBytes, PublicKey clavePublica) throws Exception {
        Signature firma = Signature.getInstance("SHA256withRSA");
        firma.initVerify(clavePublica);
        firma.update(datos);
        return firma.verify(firmaBytes);
    }
}
