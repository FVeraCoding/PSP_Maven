package Tema4.RepasoExamen.HTTP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTP_DescargaArchivo {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.uv.es/fragar/html/pdf/html11.pdf");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            int codigoRespuesta = conexion.getResponseCode();

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                BufferedInputStream bis = new BufferedInputStream(conexion.getInputStream());
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Fernando\\Downloads\\archivoDescargado.pdf"));

                byte[] buffer = new byte[4096];
                int bytesLeidos = bis.read(buffer);

                while (bytesLeidos != -1) {
                    bos.write(buffer, 0, bytesLeidos);
                    bytesLeidos = bis.read(buffer);
                }
                System.out.println("Archivo descargado con exito.");

                bis.close();
                bos.close();
                conexion.disconnect();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(HTTP_DescargaArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HTTP_DescargaArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
