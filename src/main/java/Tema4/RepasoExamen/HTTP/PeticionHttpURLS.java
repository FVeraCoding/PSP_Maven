package Tema4.RepasoExamen.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeticionHttpURLS {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.uned.es/universidad/inicio.html");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            int codigoRespuesta = conexion.getResponseCode();

            System.out.println("Elementos contenidos en href: de etiquetas <a>");

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

                String linea = br.readLine();

                while (linea != null) {
                    if (linea.strip().contains("<a")) {
                        String lineaLink = linea.strip();

                        String urlDireccion = lineaLink.substring(lineaLink.indexOf("\"") + 1, lineaLink.indexOf("\"", lineaLink.indexOf("\"") + 1));
                        System.out.println(urlDireccion);
                    }
                    linea = br.readLine();
                }

                br.close();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(PeticionHttpURLS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PeticionHttpURLS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
