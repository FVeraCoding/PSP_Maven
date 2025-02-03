package Tema2.ActividadesPropuestas.actividad2_1;

public class Raton extends Thread {

    private String nombre;
    private int tiempoAlimentacion;

    public Raton(String nombre, int tiempoAlimentacion) {
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }
    
    public void comer() {
        try {
            System.out.printf("El raton %s ha comenzado a alimentarse.%n", this.nombre);
            Thread.sleep(this.tiempoAlimentacion * 1000);
            System.out.printf("El raton %s ha terminado de alimentarse.%n", this.nombre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.comer();
    }

    public static void main(String[] args) {
        Raton fievel = new Raton("Fievel", 4);
        Raton jerry = new Raton("Jerry", 5);
        Raton pinky = new Raton("Pinky", 3);
        Raton mickey = new Raton("Mickey", 6);
        
        fievel.run();
        jerry.run();
        pinky.run();
        mickey.run();
    }

}
