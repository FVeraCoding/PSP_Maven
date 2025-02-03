
package Tema4.RepasoExamen.FTP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class DescargarArchivosFTP {
    public static void main(String[] args) {
        try {
            //Asignamos parámetros.
            String servidor = "ftp.gnu.org";
            int puerto = 21;
            String usuario = "anonymous";
            String contrasena = "";
            
            //Rutas de archivos (Local y Remoto).
            String archivoRemoto = "/README";
            String archivoLocal = "C:\\Users\\Fernando\\Desktop\\archivoDescargado.txt";
            
            //Instanciamos FTPClient.
            FTPClient ftpClient = new FTPClient();
            
            //Nos conectamos.
            System.out.println("Conectandose a "+servidor);
            ftpClient.connect(servidor, puerto);
            ftpClient.login(usuario, contrasena);
            
            //Añadimos algunas propiedades.
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            
            //Descargamos el archivo.
            FileOutputStream fos = new FileOutputStream(archivoLocal);
            
            boolean exito = ftpClient.retrieveFile(archivoRemoto, fos);
            
            if(exito == true){
                System.out.println("Archivo descargado correctamente en "+archivoLocal);
            }else{
                System.out.println("El archivo no se ha podido descargar.");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(DescargarArchivosFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
