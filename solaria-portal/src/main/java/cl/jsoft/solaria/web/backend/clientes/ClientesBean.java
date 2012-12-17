package cl.jsoft.solaria.web.backend.clientes;

import cl.jsoft.solaria.dominio.vos.VoCliente;

public class ClientesBean implements java.io.Serializable{

	private static final long serialVersionUID = -8021060341178437882L;
	
	private VoCliente clienteSeleccionado = new VoCliente();

	public VoCliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(VoCliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

}
