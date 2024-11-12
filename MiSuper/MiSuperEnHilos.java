package MiSuper;

import java.util.Random;

public class MiSuperEnHilos {

    class Cliente implements Runnable {
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
            
            for (String producto : productos) {
                try {
                    Thread.sleep(new Random().nextInt(1000) + 500); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            long fin = System.currentTimeMillis(); 
            System.out.println("Cliente #" + numeroCliente + ", Productos: " + String.join(", ", productos) + ", Caja: " + caja + ", Tiempo total: " + (fin - inicio) + " ms");
        }
    }

    public static void main(String[] args) {
        String[][] productosClientes = {
            {"Leche", "Huevo", "Yogures", "Pollo", "Pavo"},
            {"Atún", "Patata", "Limones", "Peras", "Arroz"},
            {"Cerveza", "Tinto", "Té", "Café", "Pasta"},
            {"Pan", "Avena", "Salsa", "Aceite", "Sal"},
            {"Té", "Frijoles", "Verduras", "Mermelada", "Mantequilla"}
        };

        Thread[] clientes = new Thread[5];

        for (int i = 0; i < 5; i++) {
            int caja = (i % 2) + 1; 
            clientes[i] = new Thread(new MiSuperEnHilos().new Cliente(i + 1, productosClientes[i], caja)); 
            clientes[i].start(); 
        }

        for (Thread cliente : clientes) {
            try {
                cliente.join(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }
        }
    }
}
