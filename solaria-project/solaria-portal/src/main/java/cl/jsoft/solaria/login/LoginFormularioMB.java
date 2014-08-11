package cl.jsoft.solaria.login;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.seguridad.CredencialSeguridad;
import cl.jsoft.solaria.seguridad.ValidacionNegativaException;
import cl.jsoft.solaria.seguridad.api.LoginValidaCredencialEJB;
import cl.jsoft.solaria.web.controllers.MensajesBean;
import cl.jsoft.solaria.web.jsf.TimeOutListener;

@ManagedBean
@SessionScoped
public class LoginFormularioMB implements Serializable {

	private static final long serialVersionUID = 11L;

	Logger logger = Logger.getLogger("LOGIN");
	MensajesBean mensajesBean = new MensajesBean();

	private String username = "rebecacurin@hotmail.com";
	private String password;
	
	TimeOutListener timeOutListener = new TimeOutListener() {
		
		@Override
		public void cerrarSesion() {
			System.out.println("Cerrando Sesion desde Listener.");
			throw new ViewExpiredException();		
		}
	};
	
	@PostConstruct
	public void init(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute("TO", timeOutListener);
	}

	@EJB
	LoginValidaCredencialEJB validaCredencialEJB;

	public void doLogin(ActionEvent actionEvent) {

		logger.info("[" + obtenerIpConexion() + "] Intentando login...");

		try {

			validaCredencialEJB.validaCredencial(new CredencialSeguridad(
					username, password));

			logger.info("Credencial valida!");

			iniciarLoginJaas();

			redireccionarAutorizado();

		} catch (ServletException e) {
			mensajesBean
					.msgError("Error en los datos ingresados, revice e intente de nuevo.");
		} catch (ValidacionNegativaException e) {
			mensajesBean.msgError(e.getMessage());
		} catch (ErrorDelSistemaException e) {
			mensajesBean
					.msgFatal("Error inesperado al iniciar la sesión de usuario.");
		}

	}

	private void iniciarLoginJaas() throws ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		httpServletRequest.login(username, password);

	}

	private void redireccionarAutorizado() {
		logger.info("redireccionando a dashboard...");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nh = facesContext.getApplication()
				.getNavigationHandler();
		nh.handleNavigation(facesContext, null,
				"/dashboard.xhtml?faces-redirect=true");
	}

	public void doLogout(ActionEvent actionEvent) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		try {
			logger.info("cierra session.");
			httpServletRequest.getSession().invalidate();
			httpServletRequest.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginBean [username=" + username + ", password=" + password
				+ "]";
	}

	private String obtenerIpConexion() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String ip = httpServletRequest.getRemoteAddr();
		return ip;
	}

}
