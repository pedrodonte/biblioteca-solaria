package cl.jsoft.solaria.web.controllers;
import javax.faces.bean.ManagedBean;


@ManagedBean(name="solariaBean")
public class SolariaController {

	public static final String DASHBOARD= "dashboard";
	public static final String LOGIN_FORM= "login";
	
	public static final String PRES_FORM_PRESTAMO= "pres_form_prestamo";
	
	
	public String forwardDashboard(){
		return DASHBOARD;
	}
	
	public String fwdPresFormPrestamo(){
		return PRES_FORM_PRESTAMO;
	}

}
