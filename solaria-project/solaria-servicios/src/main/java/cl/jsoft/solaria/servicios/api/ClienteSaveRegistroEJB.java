package cl.jsoft.solaria.servicios.api;

import javax.ejb.Local;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;

@Local
public interface ClienteSaveRegistroEJB {
	
	public void saveRegistro(VoCliente voCliente) throws ErrorDelSistemaException;

}
