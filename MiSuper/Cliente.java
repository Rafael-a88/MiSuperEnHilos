package MiSuper;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente implements Runnable {
    private int id;
    private Semaphore caja;
    private static final Random random = new Random();

    public Cliente(int id, Semaphore caja) {
        this.id = id;
        this.caja = caja;
    }

    @Override
    public void run() {
        try {
            // Se intenta acceder a la caja
            caja.acquire();
            String cajaNombre;
            int tiempo;

            // Determinar la caja y el tiempo que tarda
            if (caja.availablePermits() == 1) { // Indica que hay exactamente un permiso disponible. Esto podría significar que solo queda una caja libre.
                cajaNombre = "A";
                tiempo = 1000;
            } else {
                cajaNombre = "B";
                tiempo = 1500;
            }

            System.out.println("Cliente " + id + " está siendo atendido en la caja " + cajaNombre);

            // Simular el procesamiento de 5 productos aleatorios del enum
            for (int i = 1; i <= 5; i++) {
                Producto producto = Producto.values()[random.nextInt(Producto.values().length)];
                System.out.println("Cliente " + id + " está pasando " + producto.getNombre() + " en la caja " + cajaNombre);
                Thread.sleep(tiempo);
            }

            System.out.println("Cliente " + id + " ha terminado en la caja " + cajaNombre);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Liberar la caja
            caja.release();
        }
    }
}