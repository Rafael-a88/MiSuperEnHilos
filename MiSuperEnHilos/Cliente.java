package MiSuperEnHilos;

public class Cliente implements Runnable{
	
	private Super supermercado;
    private String idCliente;

    public Cliente(Super supermercado, String idCliente) {
        this.supermercado = supermercado;
        this.idCliente = idCliente;
    }

	@Override
	public void run() {
		supermercado.pasarPorCaja(idCliente);
		
	}

}
