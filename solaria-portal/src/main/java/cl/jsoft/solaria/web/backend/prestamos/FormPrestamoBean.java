package cl.jsoft.solaria.web.backend.prestamos;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;
import cl.jsoft.solaria.servicios.LibroServicesEJB;

@ManagedBean(name="prestamoBean")
public class FormPrestamoBean {
	
	@EJB
	private LibroServicesEJB libroServicesEJB;
	
	@EJB
	private ClienteServicesEJB clienteServicesEJB;

	private String cpoCodigoInterno;
	private VoLibro voLibroEncontrado = new VoLibro();
	
	private String cpoIdentificadorCliente;
	private VoCliente voCliente = new VoCliente();

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
		try {
			voLibroEncontrado = libroServicesEJB.buscarLibroCodigoInterno(cpoCodigoInterno);
			System.out.println(voLibroEncontrado);
		} catch (RegistrosNoEncontradosException e) {
			voLibroEncontrado = new VoLibro();
			mostrarMensaje(FacesMessage.SEVERITY_WARN, "Registro no encontrado");
		}
		
	}
	
	public void doBuscarClienteIdentificador(ActionEvent actionEvent) {
		try {
			voCliente = clienteServicesEJB.buscarClientePorIdentificador(cpoIdentificadorCliente);
			voCliente.setNombreCompleto(voCliente.getClienteNombres()+" "+voCliente.getClienteApellidos());
			System.out.println(voCliente);
		} catch (RegistrosNoEncontradosException e) {
			voCliente = new VoCliente();
			mostrarMensaje(FacesMessage.SEVERITY_WARN, "Registro no encontrado");
		}
		
	}

	public String getCpoCodigoInterno() {
		return cpoCodigoInterno;
	}

	public void setCpoCodigoInterno(String cpoCodigoInterno) {
		this.cpoCodigoInterno = cpoCodigoInterno;
	}

	public VoLibro getVoLibroEncontrado() {
		return voLibroEncontrado;
	}

	public void setVoLibroEncontrado(VoLibro voLibroEncontrado) {
		this.voLibroEncontrado = voLibroEncontrado;
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


	public VoCliente getVoCliente() {
		return voCliente;
	}


	public void setVoCliente(VoCliente voCliente) {
		this.voCliente = voCliente;
	}

}
