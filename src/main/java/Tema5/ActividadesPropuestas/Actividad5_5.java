package Tema5.ActividadesPropuestas;

import java.io.*;
import java.security.*;

public class Actividad5_5 {
    public static void main(String[] args) throws Exception {
                
        KeyPair claves = generarClaves();
        
        File archivoOriginal = new File("C:\\Users\\Fernando\\Desktop\\archivo.txt");
        
        File archivoFirmado = firmarDatos(archivoOriginal, claves.getPrivate());
        
        boolean firmaVerificada = verificarFirma(archivoOriginal, archivoFirmado, claves.getPublic());
        
        
        System.out.println("¿Firma verificada? "+firmaVerificada);
    }
    
    public static KeyPair generarClaves() throws NoSuchAlgorithmException{
        KeyPairGenerator claves = KeyPairGenerator.getInstance("RSA");
        claves.initialize(2048);
        return claves.generateKeyPair();
    }
    
    public static File firmarDatos(File archivo, PrivateKey clavePrivada) throws Exception {
        Signature firma = Signature.getInstance("SHA256withRSA");
        firma.initSign(clavePrivada);
        
        File archivoFirmado = new File("C:\\Users\\Fernando\\Desktop\\archivoFirmado.txt");
        
        try(FileInputStream fis = new FileInputStream(archivo);
                FileOutputStream fos = new FileOutputStream(archivoFirmado)){
            
            byte[] bytesArchivo = fis.readAllBytes();
            firma.update(bytesArchivo);
            byte[] bytesFirmados = firma.sign();
            
            fos.write(bytesFirmados);
        }
        
        return archivoFirmado;
        
    }
    
    public static boolean verificarFirma(File archivoOriginal, File archivoFirmado, PublicKey clavePublica) throws Exception {
        Signature firma = Signature.getInstance("SHA256withRSA");
        firma.initVerify(clavePublica);
        
        try(FileInputStream fis = new FileInputStream(archivoOriginal)){
            byte[] bytesOriginales = fis.readAllBytes();
            firma.update(bytesOriginales);
        }
        
        byte[] bytesFirma;
        try(FileInputStream fis = new FileInputStream(archivoFirmado)){
             bytesFirma = fis.readAllBytes();
        }
        
        boolean verificacion = firma.verify(bytesFirma);
        
        return verificacion;
    }
}
