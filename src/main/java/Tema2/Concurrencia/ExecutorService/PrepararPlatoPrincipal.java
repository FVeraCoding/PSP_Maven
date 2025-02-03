
package Tema2.Concurrencia.ExecutorService;

import java.util.concurrent.Callable;


public class PrepararPlatoPrincipal implements Callable<String>{

    private String platoPrincipal;  

    public PrepararPlatoPrincipal(String platoPrincipal) {
        this.platoPrincipal = platoPrincipal;
    }
    
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "Plato Principal listo: "+this.platoPrincipal;
    }
    
}
