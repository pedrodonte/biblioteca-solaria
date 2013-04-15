package cl.jsoft.solaria.servicios.api;

import javax.ejb.Local;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;

@Local
public interface ClienteUpdateRegistroEJB {
	
	public void updateRegistro(VoCliente voCliente) throws ErrorDelSistemaException;

}
