package MiSuper;

import java.util.Random;

public class MiSuper {

    private static final int NUM_CAJAS = 2;
    private static final int NUM_CLIENTES = 5;
    private static final int PRODUCTOS_POR_CLIENTE = 5;
    private static final Object[] locks = new Object[NUM_CAJAS];

    static {
        for (int i = 0; i < NUM_CAJAS; i++) {
            locks[i] = new Object(); // Inicializa cada caja con su propio objeto de bloqueo
        }
    }

    static class Cliente implements Runnable {
        private int numeroCliente;
        private String[] productos;
        private int caja;

        public Cliente(int numeroCliente, String[] productos, int caja) {
            this.numeroCliente = numeroCliente;
            this.productos = productos;
            this.caja = caja;
        }

        @Override
        public void run() {
            long inicio = System.currentTimeMillis();

            // Sincronización para asegurar que solo un cliente procesa a la vez en su caja
            synchronized (locks[caja - 1]) {
                for (String producto : productos) {
                    try {
                        // Simula el tiempo que tarda en pasar un producto
                        Thread.sleep(new Random().nextInt(1000) + 500);
                        System.out.println("Caja " + caja + ": Cliente #" + numeroCliente + " pasando producto: " + producto);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            long fin = System.currentTimeMillis();
            System.out.println("Cliente #" + numeroCliente + " ha terminado en caja " + caja + " en " + (fin - inicio) + " ms.");
        }
    }

    public static void main(String[] args) {
        String[][] productosClientes = {
            {"Leche", "Huevo", "Yogures", "Pollo", "Pavo"},
            {"Atun", "Patata", "Limones", "Peras", "Arroz"},
            {"Cerveza", "Tinto", "Te", "Cafe", "Pasta"},
            {"Pan", "Avena", "Salsa", "Aceite", "Sal"},
            {"Te", "Frijoles", "Verduras", "Mermelada", "Mantequilla"}
        };

        // Crear y ejecutar los hilos para los clientes
        for (int i = 0; i < NUM_CLIENTES; i++) {
            int caja = (i % NUM_CAJAS) + 1; // Alterna entre caja 1 y 2
            new Thread(new Cliente(i + 1, productosClientes[i], caja)).start();
        }
    }
}
