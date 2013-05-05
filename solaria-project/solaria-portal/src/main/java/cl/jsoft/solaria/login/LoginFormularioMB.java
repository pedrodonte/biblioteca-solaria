package cl.jsoft.solaria.login;

import static cl.jsoft.solaria.web.controllers.SolariaController.LOGIN_FORM;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean
public class LoginFormularioMB {

	Logger logger = Logger.getLogger(getClass());
	MensajesBean mensajesBean = new MensajesBean();

	private String username;
	private String password;

	public void doLogin(ActionEvent actionEvent) {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

			try {
				httpServletRequest.login(username, password);
				redireccionarAutorizado();
			} catch (ServletException e) {
				mensajesBean.msgError("Error en los datos ingresados, revice e intente de nuevo.");
			} catch (Exception e) {
				mensajesBean.msgFatal("Error inesperado al iniciar la sesión de usuario.");
			}

	}

	private void redireccionarAutorizado() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
		nh.handleNavigation(facesContext, null, "/protected/dashboard.xhtml?faces-redirect=true");
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

}
