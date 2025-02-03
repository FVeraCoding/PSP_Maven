
package Tema4.RepasoExamen.SMTP;

import java.util.Properties;
import javax.mail.Session;


public class EnvioCorreoPlano {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        
        String usuario = "fveracoding@gmail.com";
        String password = "nbkmcbgwdrybwhma";
        
        Session sesion = Session.getDefaultInstance(props, null);
        
    }
}
