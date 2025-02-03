
package Tema2.Concurrencia.ExecutorService;

import java.util.concurrent.Callable;


public class PrepararPostre implements Callable<String>{

    private String postre;

    public PrepararPostre(String postre) {
        this.postre = postre;
    }
    
    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Postre listo: "+this.postre;
    }
    
}
