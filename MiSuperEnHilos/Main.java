package MiSuperEnHilos;

public class Main {
    public static void main(String[] args) {
        Super supermercado = new Super();

        // Crear hilos para los clientes
        Thread[] clientes = new Thread[5];
        for (int i = 0; i < 5; i++) {
            String idCliente = "Cliente " + (i + 1);
            clientes[i] = new Thread(new Cliente(supermercado, idCliente));
        }

        // Iniciar los hilos de los clientes
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
    }
}
