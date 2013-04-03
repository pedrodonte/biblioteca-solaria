package cl.jsoft.solaria.web.backend.prestamos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.excepciones.ClienteMorosoException;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;
import cl.jsoft.solaria.servicios.LibroServicesEJB;
import cl.jsoft.solaria.servicios.PrestamoServicesEJB;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean(name="prestamoBean")
@ViewScoped
public class FormPrestamoBean {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@EJB
	private LibroServicesEJB libroServicesEJB;
	
	@EJB
	private ClienteServicesEJB clienteServicesEJB;
	
	@EJB
	private PrestamoServicesEJB prestamoServicesEJB;
	
	@ManagedProperty(value="#{prestamoSessionBean}")
	private PrestamoSessionBean prestamoSessionBean;
	
	@ManagedProperty(value="#{mensajesBean}")
	private MensajesBean mensajesBean;

	private String cpoCodigoInterno;
	private String cpoIdentificadorCliente;
	
	private Date cpoFechaInicio = new Date();
	private Date cpoFechaFinal = new Date();
	
	private String msgResultadoPrestamo="";	
	
	private String nombres = "";
	private String apellidos = "";
	private List<VoCliente> clientesEncontrados = new ArrayList<VoCliente>();
	private VoCliente clienteBuscadoSeleccionado = new VoCliente();
	private String clienteMoroso = "SIN_MORA";
	private List<VoPrestamo> prestamosMora = new ArrayList<>();
	
	public void doBuscaXNombreApellido(ActionEvent actionEvent) {
		logger.debug("nombre: "+nombres+", apellido: "+apellidos);
		mensajesBean.msgInfo("nombre: "+nombres+", apellido: "+apellidos);
		try {
			setClientesEncontrados(clienteServicesEJB.buscarClientesPorNombresApellidos(nombres, apellidos));
		} catch (RegistrosNoEncontradosException e) {
			logger.error(e.getMessage());
		} catch (ErrorDelSistemaException e) {
			logger.error(e.getMessage());
		}
	}


	public void doBuscarLibroCodigoInterno(ActionEvent actionEvent) {
		VoLibro voLibroEncontrado;
		try {
			voLibroEncontrado = libroServicesEJB.buscarLibroCodigoInterno(cpoCodigoInterno);
			prestamoSessionBean.setLibroEncontrado(voLibroEncontrado);
			System.out.println(voLibroEncontrado);
		} catch (RegistrosNoEncontradosException e) {
			voLibroEncontrado = new VoLibro();
			mensajesBean.msgWarn("Registro no encontrado");
		}
		
	}
	
	public void doBuscarClienteIdentificador(ActionEvent actionEvent) {
		VoCliente voCliente = new VoCliente();
		String flagMoroso = "Sin_Mora";
		try {
			voCliente = clienteServicesEJB.buscarClientePorIdentificador(cpoIdentificadorCliente);
			prestamoSessionBean.setClienteEncontrado(voCliente);
			prestamoServicesEJB.verificarMorosidad(voCliente);
		} catch (RegistrosNoEncontradosException e) {
			voCliente = new VoCliente();
			prestamoSessionBean.setClienteEncontrado(voCliente);
			mensajesBean.msgWarn("Registro no encontrado");
		} catch (ErrorDelSistemaException e) {
			mensajesBean.msgError("Error al ejecutar la operación");
			voCliente = new VoCliente();
			prestamoSessionBean.setClienteEncontrado(voCliente);
		} catch (ClienteMorosoException e) {
			flagMoroso = "MOROSO";
			try {
				prestamosMora = prestamoServicesEJB.buscarPrestamosPendientes(voCliente);
			} catch (ErrorDelSistemaException e1) {
				logger.warn("Problemas al obtener los registros prestamos mora.");
			}
			logger.info("El usuario tiene prestamos atrasados!!! ");
			mensajesBean.msgWarn(e.getMessage());
		}
		setClienteMoroso(flagMoroso);
	}
	
	public void doSeleccionaCliente(ValueChangeEvent changeEvent){
		 prestamoSessionBean.setClienteEncontrado((VoCliente) changeEvent.getNewValue());
		 logger.debug("doSeleccionaCliente "+prestamoSessionBean.getClienteEncontrado());
	}
	
	public void doGuardarNuevoPrestamo(ActionEvent actionEvent) {
		boolean isActionSuccess = false;
		try {
			boolean periodoValido = cpoFechaFinal.after(cpoFechaInicio);
			logger.info("final despues de inicial "+periodoValido);
			
			VoPrestamo voPrestamo = new VoPrestamo();
			voPrestamo.setVoCliente(prestamoSessionBean.getClienteEncontrado());
			voPrestamo.setVoLibro(prestamoSessionBean.getLibroEncontrado());
			voPrestamo.setPrestamoFecPlazoEntrega(cpoFechaFinal);
			voPrestamo.setPrestamoFecInicio(cpoFechaInicio);
			
			voPrestamo = prestamoServicesEJB.nuevoPrestamo(voPrestamo);
			
			logger.debug("Registro guardado "+voPrestamo);
			
			if (voPrestamo != null) {
				msgResultadoPrestamo = "Prestamo registrado con exito [código del nuevo prestamo "+voPrestamo.getPrestamoCodPrestamo()+"]";
				mensajesBean.msgInfo(msgResultadoPrestamo);
				isActionSuccess = true;
			}else{
				mensajesBean.msgWarn("Revice los datos ingresados.");
			}
			
		} catch (Exception e) {
			mensajesBean.msgWarn("Revice los datos ingresados.");
			logger.error(e.getMessage());
		}
		RequestContext.getCurrentInstance().addCallbackParam("isActionSuccess", isActionSuccess);		
	}
	
	@PostConstruct
	public void inicializar(){
		prestamoSessionBean.setClienteEncontrado(new VoCliente());
		prestamoSessionBean.setLibroEncontrado(new VoLibro());
	}
	
	public void actualizaFechaFinal(){
		cpoFechaFinal = cpoFechaInicio;
	}

	public String getCpoCodigoInterno() {
		return cpoCodigoInterno;
	}

	public void setCpoCodigoInterno(String cpoCodigoInterno) {
		this.cpoCodigoInterno = cpoCodigoInterno;
	}

	public String getCpoIdentificadorCliente() {
		return cpoIdentificadorCliente;
	}


	public void setCpoIdentificadorCliente(String cpoIdentificadorCliente) {
		this.cpoIdentificadorCliente = cpoIdentificadorCliente;
	}

	

	public Date getCpoFechaInicio() {
		return cpoFechaInicio;
	}


	public void setCpoFechaInicio(Date cpoFechaInicio) {
		this.cpoFechaInicio = cpoFechaInicio;
	}


	public Date getCpoFechaFinal() {
		return cpoFechaFinal;
	}


	public void setCpoFechaFinal(Date cpoFechaFinal) {
		this.cpoFechaFinal = cpoFechaFinal;
	}


	public PrestamoSessionBean getPrestamoSessionBean() {
		return prestamoSessionBean;
	}


	public void setPrestamoSessionBean(PrestamoSessionBean prestamoSessionBean) {
		this.prestamoSessionBean = prestamoSessionBean;
	}


	public String getMsgResultadoPrestamo() {
		return msgResultadoPrestamo;
	}


	public void setMsgResultadoPrestamo(String msgResultadoPrestamo) {
		this.msgResultadoPrestamo = msgResultadoPrestamo;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
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


	public String getClienteMoroso() {
		return clienteMoroso;
	}


	public void setClienteMoroso(String clienteMoroso) {
		this.clienteMoroso = clienteMoroso;
	}


	public List<VoPrestamo> getPrestamosMora() {
		return prestamosMora;
	}


	public void setPrestamosMora(List<VoPrestamo> prestamosMora) {
		this.prestamosMora = prestamosMora;
	}

}
