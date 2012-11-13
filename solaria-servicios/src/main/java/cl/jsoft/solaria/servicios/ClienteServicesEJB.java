package cl.jsoft.solaria.servicios;

import java.util.List;

import javax.ejb.Local;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;

@Local
public interface ClienteServicesEJB {
	
	public VoCliente buscarClientePorIdentificador(String nroRun) throws RegistrosNoEncontradosException;
	
	public List<VoCliente> buscarClientesPorGrupo(VoGrupocliente grupo) throws RegistrosNoEncontradosException;
	
	public List<VoCliente> buscarTodosLosClientes(VoGrupocliente grupo) throws RegistrosNoEncontradosException;

}
