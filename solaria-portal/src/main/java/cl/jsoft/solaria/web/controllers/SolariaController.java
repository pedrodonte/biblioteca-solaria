package cl.jsoft.solaria.web.controllers;
import javax.faces.bean.ManagedBean;


@ManagedBean(name="solariaBean")
public class SolariaController {

	public static final String DASHBOARD= "dashboard";
	public static final String LOGIN_FORM= "login";
	public static final String FORM_FOTOGRAFIA = "form_foto";
	public static final String CATALOGO = "catalogo";
	public static final String CLIENTES = "clientes";
	public static final String CREDENCIALES = "credenciales";
	
	public static final String PRES_FORM_PRESTAMO= "pres_form_prestamo";
	
	
	public String forwardDashboard(){
		return DASHBOARD;
	}
	
	public String fwdPresFormPrestamo(){
		return PRES_FORM_PRESTAMO;
	}
	
	public String fwdFormFotografia(){
		return FORM_FOTOGRAFIA;
	}
	
	public String fwdCatalogo(){
		return CATALOGO;
	}
	
	public String fwdClientes(){
		return CLIENTES;
	}
	
	public String fwdCredenciales(){
		return CREDENCIALES;
	}

}
