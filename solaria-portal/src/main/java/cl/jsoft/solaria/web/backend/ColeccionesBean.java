package cl.jsoft.solaria.web.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;

@ManagedBean
@ApplicationScoped
public class ColeccionesBean implements java.io.Serializable {

	private static final long serialVersionUID = 161848263L;
	
	@EJB
	private ClienteServicesEJB clienteServicesEJB;
	
	
	private List<VoCliente> clientesRegistrados = new ArrayList<VoCliente>();
	
	private List<VoGrupocliente> gruposClienteRegistrados = new ArrayList<VoGrupocliente>();
	
	@PostConstruct
	public void inicializa(){
		cargarClientes();
	}
	
	// Getters and Settes del Bean

	private void cargarClientes() {
		try {
			clientesRegistrados = clienteServicesEJB.buscarTodosLosClientes();
		} catch (RegistrosNoEncontradosException e) {
			e.printStackTrace();
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		}
	}

	public List<VoCliente> getClientesRegistrados() {
		return clientesRegistrados;
	}

	public void setClientesRegistrados(List<VoCliente> clientesRegistrados) {
		this.clientesRegistrados = clientesRegistrados;
	}
}
