package cl.jsoft.solaria.seguridad.api;

import javax.ejb.Local;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.seguridad.CredencialSeguridad;
import cl.jsoft.solaria.seguridad.ValidacionNegativaException;

@Local
public interface LoginValidaCredencialEJB {
	
	public boolean validaCredencial(CredencialSeguridad credencialSeguridad) throws ValidacionNegativaException, ErrorDelSistemaException;

}
