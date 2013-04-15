package cl.jsoft.solaria.servicios.api;

import java.util.List;

import javax.ejb.Local;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;

@Local
public interface ClienteObtenerRegistrosEJB {
	
	public List<VoCliente> obtenerRegistros(FiltroCliente filtroCliente) throws RegistrosNoEncontradosException, ErrorDelSistemaException;

}
