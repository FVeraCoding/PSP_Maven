package Tema5.RepasoExamen.FirmaElectronica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Firma1 {

    public static KeyPair generarClaves() throws NoSuchAlgorithmException {
        KeyPairGenerator claves = KeyPairGenerator.getInstance("RSA");
        claves.initialize(1048);

        return claves.generateKeyPair();

    }

    public static File firmarArchivo(File archivoOriginal, PrivateKey clavePrivada) throws NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IOException, SignatureException {
        Signature firma = Signature.getInstance("SHA256withRSA");
        firma.initSign(clavePrivada);
        File archivoFirmado = new File("C:\\Users\\Fernando\\Desktop\\archivoFirmado.txt");

        try (FileInputStream fis = new FileInputStream(archivoOriginal); FileOutputStream fos = new FileOutputStream(archivoFirmado)) {

            byte[] bytesOriginales = fis.readAllBytes();
            firma.update(bytesOriginales);

            byte[] bytesFirma = firma.sign();
            fos.write(bytesFirma);
        }

        System.out.println("Archivo firmado con exito.");
        return archivoFirmado;
    }

    public static boolean verificarFirma(File archivoOriginal, File archivoFirmado, PublicKey clavePublica) throws NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IOException, SignatureException {
        Signature firma = Signature.getInstance("SHA256withRSA");
        firma.initVerify(clavePublica);

        try (FileInputStream fis = new FileInputStream(archivoOriginal)) {
            byte[] bytesOriginales = fis.readAllBytes();
            firma.update(bytesOriginales);
        }

        byte[] bytesFirma;
        try (FileInputStream fis = new FileInputStream(archivoFirmado)) {
            bytesFirma = fis.readAllBytes();
        }

        boolean verificacion = firma.verify(bytesFirma);

        return verificacion;

    }

    public static void main(String[] args) {
        try {
            File archivoOriginal = new File("C:\\Users\\Fernando\\Desktop\\archivo.txt");

            KeyPair claves = generarClaves();

            File archivoFirmado = firmarArchivo(archivoOriginal, claves.getPrivate());
            
            boolean verificacionFirma = verificarFirma(archivoOriginal, archivoFirmado, claves.getPublic());
            
            System.out.println("Firma verificada: "+verificacionFirma);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Firma1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Firma1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Firma1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(Firma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
