package Tema4.ActividadesPropuestas;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ActividadPropuesta4_2 {
    public static void main(String[] args) {
        String servidor = "ftp.pureftpd.org";
        int puerto = 21;
        String usuario = "anonymous"; 
        String contrasena = ""; 

        
        String archivoRemoto = "/docs/configuration_nginx_php.pdf";
        String archivoLocal = "C:\\Users\\Fernando\\Desktop\\archivoDescargado.pdf";

        
        FTPClient ftpClient = new FTPClient();

        try {
            
            System.out.println("Conectando a " + servidor + "...");
            ftpClient.connect(servidor, puerto);
            ftpClient.login(usuario, contrasena);
            
            ftpClient.enterLocalPassiveMode(); 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            
            try (OutputStream outputStream = new FileOutputStream(archivoLocal)) {
                boolean exito = ftpClient.retrieveFile(archivoRemoto, outputStream);
                if (exito) {
                    System.out.println("Archivo descargado exitosamente: " + archivoLocal);
                } else {
                    System.out.println("Error al descargar el archivo.");
                }
            }

            
            ftpClient.logout();
            ftpClient.disconnect();
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                    System.out.println("Desconectado del servidor.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
