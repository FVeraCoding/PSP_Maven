
package Tema2.ActividadesPropuestas.Actividad2_4;

import java.util.Timer;
import java.util.TimerTask;


public class EstirarPiernas extends TimerTask{

    @Override
    public void run() {
        System.out.println("¡Es momento de estirar las piernas!");
    }
    
    public static void main(String[] args) {
        Timer temporizador = new Timer();
        temporizador.schedule(new EstirarPiernas(), 0, 1800000);
    }
    
}
