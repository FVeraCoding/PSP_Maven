/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema4.RepasoExamen.File;

import java.io.File;


public class CrearDirectorio {
    public static void main(String[] args) {
        File directorio = new File("C:\\Users\\Fernando\\Upload");
        
        if(!directorio.exists()){
            System.out.println("El directorio Upload no existe en el sistema.");
            System.out.println("Procediendo a crearlo...");
            directorio.mkdir();
            System.out.println("Directorio Upload creado correctamente.");
        }else{
            System.out.println("El directorio Upload ya existe en este sistema.");
        }
    }
}
