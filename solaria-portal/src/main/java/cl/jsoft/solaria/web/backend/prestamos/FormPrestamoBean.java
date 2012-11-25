package cl.jsoft.solaria.web.backend.prestamos;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;
import cl.jsoft.solaria.servicios.LibroServicesEJB;
import cl.jsoft.solaria.servicios.PrestamoServicesEJB;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean(name="prestamoBean")
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
		VoCliente voCliente;
		try {
			voCliente = clienteServicesEJB.buscarClientePorIdentificador(cpoIdentificadorCliente);
			voCliente.setNombreCompleto(voCliente.getClienteNombres()+" "+voCliente.getClienteApellidos());
			prestamoSessionBean.setClienteEncontrado(voCliente);
		} catch (RegistrosNoEncontradosException e) {
			voCliente = new VoCliente();
			mensajesBean.msgWarn("Registro no encontrado");
		}
		
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
	
	


}
