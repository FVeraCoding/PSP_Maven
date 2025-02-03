
package Tema2.Concurrencia.ExecutorService;

import java.util.concurrent.Callable;


public class PrepararBebida implements Callable<String>{
    private String bebida;
    
    public PrepararBebida(String bebida){
        this.bebida = bebida;
    }
    
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Bebida lista: "+ this.bebida;
    }
    
}
