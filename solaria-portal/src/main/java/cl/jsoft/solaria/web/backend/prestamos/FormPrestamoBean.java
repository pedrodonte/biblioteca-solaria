package cl.jsoft.solaria.web.backend.prestamos;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;
import cl.jsoft.solaria.servicios.LibroServicesEJB;
import cl.jsoft.solaria.servicios.PrestamoServicesEJB;

@ManagedBean(name="prestamoBean")
public class FormPrestamoBean {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@EJB
	private LibroServicesEJB libroServicesEJB;
	
	@EJB
	private ClienteServicesEJB clienteServicesEJB;
	
	@EJB
	private PrestamoServicesEJB prestamoServicesEJB;

	private String cpoCodigoInterno;
	
	private String cpoIdentificadorCliente;
	
	private Date cpoFechaInicio = new Date();
	private Date cpoFechaFinal = new Date();
	
	@ManagedProperty(value="#{prestamoSessionBean}")
	private PrestamoSessionBean prestamoSessionBean;

	private HttpServletRequest httpServletRequest;
	private FacesContext facesContext;
	private RequestContext requestContext;

	@PostConstruct
	public void inicializarBean(){
	        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        facesContext = FacesContext.getCurrentInstance();
	        requestContext = RequestContext.getCurrentInstance();
	}

	
	public void doBuscarLibroCodigoInterno(ActionEvent actionEvent) {
		VoLibro voLibroEncontrado;
		try {
			voLibroEncontrado = libroServicesEJB.buscarLibroCodigoInterno(cpoCodigoInterno);
			prestamoSessionBean.setLibroEncontrado(voLibroEncontrado);
			System.out.println(voLibroEncontrado);
		} catch (RegistrosNoEncontradosException e) {
			voLibroEncontrado = new VoLibro();
			mostrarMensaje(FacesMessage.SEVERITY_WARN, "Registro no encontrado");
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
			mostrarMensaje(FacesMessage.SEVERITY_WARN, "Registro no encontrado");
		}
		
	}
	
	public void doGuardarNuevoPrestamo(ActionEvent actionEvent) {
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
				mostrarMensaje(FacesMessage.SEVERITY_INFO, "Prestamo registrado con exito [cod="+voPrestamo.getPrestamoCodPrestamo()+"]");
			}else{
				mostrarMensaje(FacesMessage.SEVERITY_WARN, "Revice los datos ingresados.");
			}
			
		} catch (Exception e) {
			mostrarMensaje(FacesMessage.SEVERITY_WARN, "Revice los datos ingresados.");
			logger.error(e.getMessage());
		}
		
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

	private void mostrarMensaje(Severity severidad, String mensaje) {
        FacesMessage facesMessage = new FacesMessage(severidad, mensaje, null);
        facesContext.addMessage(null, facesMessage);
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
	
	


}
