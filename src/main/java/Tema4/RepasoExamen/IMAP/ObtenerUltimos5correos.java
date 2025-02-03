
package Tema4.RepasoExamen.IMAP;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;


public class ObtenerUltimos5correos {
    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");
            props.put("mail.imap.host", "imap.gmail.com");
            props.put("mail.imap.port", "993");
            
            String usuario = "fveracoding@gmail.com";
            String password = "___";
            
            Session sesion = Session.getDefaultInstance(props);
            Store almacen = sesion.getStore();
            almacen.connect("imap.gmail.com", usuario, password);
            
            Folder bandejaEntrada = almacen.getFolder("INBOX");
            bandejaEntrada.open(Folder.READ_ONLY);
            Message[] mensajes = bandejaEntrada.getMessages(1, 6);
            
            for(int i = mensajes.length-1; i>=0; i--){
                System.out.println("Remitente: "+mensajes[i].getFrom()[0]+" - Asunto: "+mensajes[i].getSubject());
            }
            
            
            
            
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(ObtenerUltimos5correos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ObtenerUltimos5correos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
