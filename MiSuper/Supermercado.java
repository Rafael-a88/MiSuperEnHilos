package MiSuper;

import java.util.concurrent.Semaphore;

public class Supermercado {

    public static void main(String[] args) {

        Semaphore caja = new Semaphore(2);

        // Creamos 5 clientes
        Thread[] clientes = new Thread[5];
        for (int i = 0; i < 5  ; i++) {
            int clienteId = i + 1;
            clientes[i] = new Thread(new Cliente(clienteId, caja));
        }

        // Iniciar todos los hilos de clientes
        for (Thread cliente : clientes) {
            cliente.start();
        }

        // Esperar a que todos los clientes terminen
        for (Thread cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ya no quedan clientes.");
    }
}
