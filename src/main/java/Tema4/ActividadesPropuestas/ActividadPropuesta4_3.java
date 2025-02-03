package Tema4.ActividadesPropuestas;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class ActividadPropuesta4_3 {

    public static void main(String[] args) {
        Properties propiedades = new Properties();
        propiedades.put("mail.store.protocol", "imaps");
        propiedades.put("mail.imap.host", "imap.gmail.com");
        propiedades.put("mail.imap.port", "993");

        String correo = "fveracoding@gmail.com";
        String contrasena = "dfpnbiqjryllgyuq";

        try {
            Session sesion = Session.getDefaultInstance(propiedades, null);
            Store almacen = sesion.getStore("imaps");
            almacen.connect("imap.gmail.com", correo, contrasena);

            Folder carpetaRaiz = almacen.getDefaultFolder();
            Folder[] carpetas = carpetaRaiz.list();

            System.out.println("Etiquetas disponibles en la cuenta de Gmail:");
            for (int i = 0; i < carpetas.length; i++) {
                System.out.println((i + 1) + ". " + carpetas[i].getName());
            }

            
            almacen.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
