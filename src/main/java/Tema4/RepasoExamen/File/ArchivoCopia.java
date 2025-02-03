package Tema4.RepasoExamen.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchivoCopia {

    public static void main(String[] args) {
        File archivo = new File("C:\\Users\\Fernando\\Downloads\\report.txt");

        if (archivo.exists()) {
            FileInputStream fis = null;
            try {
                System.out.println("El archivo " + archivo.getName() + " si existe en el directorio " + archivo.getAbsolutePath());
                System.out.println("Procediendo a copiarlo al directorio de trabajo (Escritorio)");
                fis = new FileInputStream(archivo);
                FileOutputStream fos = new FileOutputStream("C:\\Users\\Fernando\\Desktop\\report.txt");

                byte[] buffer = new byte[1024];
                int bytesLeidos = fis.read(buffer);
                
                while(bytesLeidos != -1){
                    fos.write(buffer, 0, bytesLeidos);
                    bytesLeidos = fis.read(buffer);
                }
                
                System.out.println("Archivo copiado correctamente.");
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArchivoCopia.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ArchivoCopia.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(ArchivoCopia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            System.out.println("El archivo " + archivo.getName() + " no existe en el directorio " + archivo.getAbsolutePath());
        }
    }
}
