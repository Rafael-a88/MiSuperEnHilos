package MiSuperEnHilos;



import java.util.concurrent.Semaphore;
import java.util.Random;

public class Super {
   
    private Semaphore cajaA = new Semaphore(1); 
    private Semaphore cajaB = new Semaphore(1); 

    // Metodo para poder pasar por caja.
    public void pasarPorCaja(String idCliente) {
        try {
            boolean atendido = false;
            while (!atendido) { 
            	
                // Intentamos adquirir la caja A primero.
                if (cajaA.tryAcquire()) { 
                    try {
                        // Si logramos adquirir la caja A, atendemos al cliente en esa caja.
                        atenderCliente(idCliente, "A");
                    } finally {
                        // Después de atender al cliente, liberamos la caja A para que otro cliente pueda usarla.
                        cajaA.release(); 
                        atendido = true; 
                    }
                } else if (cajaB.tryAcquire()) { 
                    // Si no pudimos adquirir la caja A, intentamos con la caja B.
                    try {
                        atenderCliente(idCliente, "B");
                    } finally {
                        cajaB.release(); 
                        atendido = true; 
                    }
                } else {
                    // Si las cajas no están disponible, esperamos un poco antes de volver a intentar.
                    Thread.sleep(500); 
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
    }

    // Este metodo simula el proceso de atencion del cliente en la caja.
    private void atenderCliente(String idCliente, String caja) throws InterruptedException {
    	
        // Seleccionamos algunos artículos aleatorios para el cliente.
        Articulos[] articulos = seleccionarArticulos();
        StringBuilder articulosList = new StringBuilder();

        // Recorremos una lista con los nombres de los artículos que el cliente está comprando.
        for (Articulos articulo : articulos) {
            articulosList.append(articulo.toString()).append(", ");
        }

        

        System.out.println(idCliente + " está pasando por la caja " + caja + " con los siguientes artículos: " + articulosList.toString());

        // Simulamos el tiempo que tarda la cajero/a en atender al cliente.
        if ("A".equals(caja)) {
            Thread.sleep(3000);
        } else if ("B".equals(caja)) {
            Thread.sleep(2000);
        }

        System.out.println(idCliente + " ha terminado en la caja " + caja + ".\n");
    }

    // Este método selecciona aleatoriamente 5 artículos para el cliente.
    private Articulos[] seleccionarArticulos() {
    	
        Articulos[] todosLosArticulos = Articulos.values();
        Random random = new Random();
        Articulos[] seleccionados = new Articulos[5]; // Creamos un array para almacenar los artículos seleccionados.

        // Cogemos 5 articulos aleatorios
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(todosLosArticulos.length); 
            seleccionados[i] = todosLosArticulos[index]; 
        }

        return seleccionados; 
    }
}
