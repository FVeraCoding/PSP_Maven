package Tema4.ActividadesGenerales;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EnviarCorreoElectronico {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        final String username = "FVeraCoding@gmail.com";
        final String password = "hfaboqhkshizhkhx";

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(username));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse("fverabernal@gmail.com")); 
            mensaje.setSubject("Correo enviado a través de Java."); 
            mensaje.setText("Este correo sólo contiene texto plano y ha sido enviado a través de Java."); 
            
            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");

            

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
