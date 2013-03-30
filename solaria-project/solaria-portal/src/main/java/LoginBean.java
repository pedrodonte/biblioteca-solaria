
import static cl.jsoft.solaria.web.controllers.SolariaController.LOGIN_FORM;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;


@ManagedBean
public class LoginBean {

	private String username = "admin";
	private String password = "";
	
	private FacesContext facesContext;
	private RequestContext requestContext;
	private HttpServletRequest httpServletRequest;

	
	@PostConstruct
	public void inicializarBean(){
		httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		facesContext = FacesContext.getCurrentInstance();
		requestContext = RequestContext.getCurrentInstance();
	}

	
	public void doLogin(ActionEvent actionEvent) {
		
		boolean isUsuarioValido = false;
		
		boolean loginValido = username.equals("admin") && password.equals("cppo2013");
		
		if(loginValido){
			isUsuarioValido  = true;
			mostrarMensaje(FacesMessage.SEVERITY_INFO, "Login Exitoso.");
		}else{
			mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Revice sus Credenciales");
		}

		requestContext.addCallbackParam("isUsuarioValido", isUsuarioValido);
		
	}
	
	public String doLogout(){
		try {
			httpServletRequest.getSession().invalidate();
			httpServletRequest.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return LOGIN_FORM;
	}
	
	private void mostrarMensaje(Severity severidad, String mensaje) {
		FacesMessage facesMessage = new FacesMessage(severidad, mensaje, null);
		facesContext.addMessage(null, facesMessage);
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
