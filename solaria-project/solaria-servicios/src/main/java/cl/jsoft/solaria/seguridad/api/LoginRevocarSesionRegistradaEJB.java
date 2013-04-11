package cl.jsoft.solaria.seguridad.api;

import javax.ejb.Local;

@Local
public interface LoginRevocarSesionRegistradaEJB {

	public void revocarSesionIniciada(String sessionWebId);
	
}
