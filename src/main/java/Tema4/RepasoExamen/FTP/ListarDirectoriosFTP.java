package Tema4.RepasoExamen.FTP;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ListarDirectoriosFTP {
    public static void main(String[] args) {
        try {
            // Asignamos parámetros de conexión
            String servidor = "ftp.gnu.org";
            int puerto = 21;
            String usuario = "anonymous";
            String contrasena = "";
            
            // Instanciamos FTPClient
            FTPClient ftpClient = new FTPClient();
            
            // Nos conectamos al servidor
            System.out.println("Conectándose a " + servidor);
            ftpClient.connect(servidor, puerto);
            ftpClient.login(usuario, contrasena);
            
            // Cambiamos a modo pasivo
            ftpClient.enterLocalPassiveMode();

            // Listamos los directorios del servidor
            FTPFile[] files = ftpClient.listFiles();

            // Mostramos los directorios encontrados
            System.out.println("Directorios en el servidor FTP:");
            for (FTPFile archivo : files) {
                System.out.println(" - " + archivo.getName());
            }

            // Cerramos la conexión
            ftpClient.logout();
            ftpClient.disconnect();
            
        } catch (IOException ex) {
            Logger.getLogger(ListarDirectoriosFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
