package cl.jsoft.solaria.web.backend.prestamos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoLibro;

@ManagedBean
@SessionScoped
public class PrestamoSessionBean {
	
	Logger logger = Logger.getLogger(getClass());
	
	private VoCliente clienteEncontrado = new VoCliente();
	private VoLibro libroEncontrado = new VoLibro();
	
	
	public VoCliente getClienteEncontrado() {
		return clienteEncontrado;
	}
	public void setClienteEncontrado(VoCliente clienteEncontrado) {
		clienteEncontrado.setNombreCompleto(clienteEncontrado.getClienteNombres()+" "+clienteEncontrado.getClienteApellidos());
		logger.debug("setCli: "+clienteEncontrado);
		this.clienteEncontrado = clienteEncontrado;
	}
	public VoLibro getLibroEncontrado() {
		return libroEncontrado;
	}
	public void setLibroEncontrado(VoLibro libroEncontrado) {
		logger.debug("setLib: "+libroEncontrado);
		this.libroEncontrado = libroEncontrado;
	}
	
}
