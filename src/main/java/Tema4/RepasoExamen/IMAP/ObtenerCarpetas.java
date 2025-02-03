package Tema4.RepasoExamen.IMAP;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class ObtenerCarpetas {

    public static void main(String[] args) {
        try {
            
            //Crear la conexión con el correo electrónico.
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");
            props.put("mail.imap.host", "imap.gmail.com");
            props.put("mail.imap.port", "993");
            
            String usuario = "fveracoding@gmail.com";
            String password = "___";
            
            Session sesion = Session.getDefaultInstance(props);
            Store almacen = sesion.getStore();
            almacen.connect("imap.gmail.com", usuario, password);
            
            
            //Lo que se haga a partir de aquí depende del ejercicio.
            Folder carpetaRaiz = almacen.getDefaultFolder();
            Folder[] carpetas = carpetaRaiz.list();
            
            for(Folder folder : carpetas){
                System.out.println(folder.getName());
            }
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(ObtenerCarpetas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ObtenerCarpetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }
}
