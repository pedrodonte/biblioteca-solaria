package cl.jsoft.solaria.seguridad.api;

import javax.ejb.Local;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.seguridad.ValidacionNegativaException;
import cl.jsoft.solaria.seguridad.modelo.IUsuario;

@Local
public interface LoginValidaCredencialEJB {
	
	public void validaCredencial(IUsuario absUsuario) throws ValidacionNegativaException, ErrorDelSistemaException;

}
