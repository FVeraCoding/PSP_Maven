
package Tema2.Concurrencia.ExecutorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Restaurante {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService cocina = Executors.newFixedThreadPool(5);
        
        // Se instancian las diferentes demandas.
        PrepararBebida bebida = new PrepararBebida("Coca-Cola");
        PrepararEntrante entrante = new PrepararEntrante("Panecillos y Hummus");
        PrepararPlatoPrincipal platoPrincipal = new PrepararPlatoPrincipal("Falafel con salsa casera");
        PrepararSegundoPlato platoSecundario = new PrepararSegundoPlato("Arroz con verduras");
        PrepararPostre postre = new PrepararPostre("Tarta de queso");
        
        
        //Se envían las demandas a la cocina y se guardan los mensajes de retorno en un objeto Future.
        Future<String> futuroBebida = cocina.submit(bebida);
        Future<String> futuroPlatoPrincipal = cocina.submit(platoPrincipal);
        Future<String> futuroPostre = cocina.submit(postre);
        cocina.submit(entrante);
        cocina.submit(platoSecundario);
        
        //Se obtienen los mensajes de retorno almacenados en los objetos Future y se muestran por pantalla.
        System.out.println(futuroBebida.get());
        System.out.println(futuroPlatoPrincipal.get());
        System.out.println(futuroPostre.get());
        
        
        
        cocina.close();
    }
}
