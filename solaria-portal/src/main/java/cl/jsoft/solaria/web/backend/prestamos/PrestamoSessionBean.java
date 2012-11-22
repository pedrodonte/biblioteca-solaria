package cl.jsoft.solaria.web.backend.prestamos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoLibro;

@ManagedBean
@SessionScoped
public class PrestamoSessionBean {
	
	private VoCliente clienteEncontrado = new VoCliente();
	private VoLibro libroEncontrado = new VoLibro();
	
	
	public VoCliente getClienteEncontrado() {
		return clienteEncontrado;
	}
	public void setClienteEncontrado(VoCliente clienteEncontrado) {
		this.clienteEncontrado = clienteEncontrado;
	}
	public VoLibro getLibroEncontrado() {
		return libroEncontrado;
	}
	public void setLibroEncontrado(VoLibro libroEncontrado) {
		this.libroEncontrado = libroEncontrado;
	}
	
	
	
}
