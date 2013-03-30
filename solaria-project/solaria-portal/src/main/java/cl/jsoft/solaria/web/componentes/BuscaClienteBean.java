package cl.jsoft.solaria.web.componentes;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean
@ViewScoped
public class BuscaClienteBean implements java.io.Serializable{

	private static final long serialVersionUID = 161848263L;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@EJB
	private ClienteServicesEJB clienteServicesEJB;
	
	@ManagedProperty(value="#{mensajesBean}")
	private MensajesBean mensajesBean;
	
	private String nombres = "";
	private String apellidos = "";
	private List<VoCliente> clientesEncontrados = new ArrayList<VoCliente>();
	private VoCliente clienteBuscadoSeleccionado = new VoCliente();
	private List<IBuscaCliente> escuchadores = new ArrayList<IBuscaCliente>();
	
	public void doBuscaXNombreApellido(ActionEvent actionEvent) {
		
		logger.debug("doBuscaXNombreApellido nombre: "+nombres+", apellido: "+apellidos);
		
		mensajesBean.msgInfo("nombre: "+nombres+", apellido: "+apellidos);
		
		try {
			setClientesEncontrados(clienteServicesEJB.buscarClientesPorNombresApellidos(nombres, apellidos));
		} catch (RegistrosNoEncontradosException | ErrorDelSistemaException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void doSeleccionaCliente(ValueChangeEvent changeEvent){
		
		try {
			clienteBuscadoSeleccionado = (VoCliente) changeEvent.getNewValue();
			logger.debug("Item Seleccionado "+clienteBuscadoSeleccionado);
			
			for(IBuscaCliente escuchador:escuchadores){
				escuchador.setClienteEncontrado(clienteBuscadoSeleccionado);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addBuscaListener(IBuscaCliente iBuscaCliente){
		escuchadores.add(iBuscaCliente);
	}
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public List<VoCliente> getClientesEncontrados() {
		return clientesEncontrados;
	}
	public void setClientesEncontrados(List<VoCliente> clientesEncontrados) {
		this.clientesEncontrados = clientesEncontrados;
	}
	public VoCliente getClienteBuscadoSeleccionado() {
		return clienteBuscadoSeleccionado;
	}
	public void setClienteBuscadoSeleccionado(VoCliente clienteBuscadoSeleccionado) {
		this.clienteBuscadoSeleccionado = clienteBuscadoSeleccionado;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public List<IBuscaCliente> getEscuchadores() {
		return escuchadores;
	}

	public void setEscuchadores(List<IBuscaCliente> escuchadores) {
		this.escuchadores = escuchadores;
	}


	

}
