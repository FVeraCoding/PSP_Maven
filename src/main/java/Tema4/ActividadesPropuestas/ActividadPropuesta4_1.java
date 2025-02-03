package Tema4.ActividadesPropuestas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActividadPropuesta4_1 {

    public static void main(String[] args) {

        String apiUrl = "https://pokeapi.co/api/v2/pokemon/pikachu";

        try {

            URL url = new URL(apiUrl);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            int codigoRespuesta = conexion.getResponseCode();

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String inputLine = in.readLine();
                System.out.println("Respuesta JSON de la API:");

                while (inputLine != null) {
                    System.out.println(inputLine);
                    inputLine = in.readLine();
                }
                in.close();

                conexion.disconnect();

            } else {
                System.out.println("Error en la conexión. Código de respuesta: " + codigoRespuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
