package Tema2.Concurrencia.SincronizacionExhanger5Tareas;

import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
       
        Exchanger<String> exchanger1 = new Exchanger<>(); // Entre Recursos Humanos y Marketing
        Exchanger<String> exchanger2 = new Exchanger<>(); // Entre Marketing y Finanzas
        Exchanger<String> exchanger3 = new Exchanger<>(); // Entre Finanzas y Producción
        Exchanger<String> exchanger4 = new Exchanger<>(); // Entre Producción y Ventas
        
        Departamento recursosHumanos = new Departamento(null, exchanger1, "Recursos Humanos");
        Departamento marketing = new Departamento(exchanger1, exchanger2, "Marketing");
        Departamento finanzas = new Departamento(exchanger2, exchanger3, "Finanzas");
        Departamento produccion = new Departamento(exchanger3, exchanger4, "Produccion");
        Departamento ventas = new Departamento(exchanger4, null, "Ventas");

        
        new Thread(recursosHumanos).start();
        new Thread(marketing).start();
        new Thread(finanzas).start();
        new Thread(produccion).start();
        new Thread(ventas).start();
    }
}
