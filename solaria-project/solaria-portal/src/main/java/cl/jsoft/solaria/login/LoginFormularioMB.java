package cl.jsoft.solaria.login;

import static cl.jsoft.solaria.web.controllers.SolariaController.LOGIN_FORM;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.seguridad.CredencialSeguridad;
import cl.jsoft.solaria.seguridad.ValidacionNegativaException;
import cl.jsoft.solaria.seguridad.api.LoginValidaCredencialEJB;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean
@ViewScoped
public class LoginFormularioMB implements Serializable {

	private static final long serialVersionUID = 11L;

	Logger logger = Logger.getLogger("LOGIN");
	MensajesBean mensajesBean = new MensajesBean();

	private String username = "rebecacurin@hotmail.com";
	private String password;

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
				"/protected/dashboard.xhtml?faces-redirect=true");
	}

	public String doLogout() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		try {
			httpServletRequest.getSession().invalidate();
			httpServletRequest.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return LOGIN_FORM;
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
