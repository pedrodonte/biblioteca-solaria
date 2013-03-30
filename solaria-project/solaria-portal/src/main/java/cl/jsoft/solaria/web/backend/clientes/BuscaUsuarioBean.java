package cl.jsoft.solaria.web.backend.clientes;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;

@ManagedBean
@ViewScoped
public class BuscaUsuarioBean implements java.io.Serializable{

	private static final long serialVersionUID = -1353524060108332255L;
	Logger logger = Logger.getLogger(getClass());
	
	@EJB ClienteServicesEJB clienteServicesEJB;

	private List<VoGrupocliente> gruposCliente;
	private VoGrupocliente grupoclienteSeleccionado;
	private List<VoCliente> clientesEncontrados;
	
	public void doSeleccionaGrupo(ValueChangeEvent changeEvent){
		setGrupoclienteSeleccionado((VoGrupocliente) changeEvent.getNewValue());
		try {
			logger.debug(grupoclienteSeleccionado+" Buscando GC.");
			
			setClientesEncontrados(clienteServicesEJB.buscarClientesPorGrupo(grupoclienteSeleccionado));
			
			logger.debug(clientesEncontrados.size()+" Presentando Lista de Clientes.");
		} catch (RegistrosNoEncontradosException | ErrorDelSistemaException e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void inicializa(){
		try {
			logger.debug("Inicializa Busca Usuario Bean");
			setGruposCliente(clienteServicesEJB.buscarTodosGruposCliente());
			logger.debug(gruposCliente.size()+" Grupos cliente.");
		} catch (RegistrosNoEncontradosException | ErrorDelSistemaException e) {
			e.printStackTrace();
		}
	}

	public List<VoGrupocliente> getGruposCliente() {
		return gruposCliente;
	}

	public void setGruposCliente(List<VoGrupocliente> gruposCliente) {
		this.gruposCliente = gruposCliente;
	}

	public VoGrupocliente getGrupoclienteSeleccionado() {
		return grupoclienteSeleccionado;
	}

	public void setGrupoclienteSeleccionado(VoGrupocliente grupoclienteSeleccionado) {
		this.grupoclienteSeleccionado = grupoclienteSeleccionado;
	}

	public List<VoCliente> getClientesEncontrados() {
		return clientesEncontrados;
	}

	public void setClientesEncontrados(List<VoCliente> clientesEncontrados) {
		this.clientesEncontrados = clientesEncontrados;
	}

	
}
