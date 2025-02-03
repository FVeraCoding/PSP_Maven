
package Tema2.ActividadesPropuestas.Actividad2_5;


public class Main {
    public static void main(String[] args) {
        
        HiloPrincipal hilo1 = new HiloPrincipal("Hilo 1");
        HiloPrincipal hilo2 = new HiloPrincipal("Hilo 2");
        HiloPrincipal hilo3 = new HiloPrincipal("Hilo 3");
        HiloPrincipal hilo4 = new HiloPrincipal("Hilo 4");
        HiloPrincipal hilo5 = new HiloPrincipal("Hilo 5");
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        
    }
}

