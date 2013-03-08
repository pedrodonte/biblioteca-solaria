package cl.jsoft.solaria.servicios;

import java.util.List;

import javax.ejb.Local;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;

@Local
public interface ClienteServicesEJB {
	
	public VoCliente buscarClientePorIdentificador(String nroRun) throws RegistrosNoEncontradosException, ErrorDelSistemaException;
	
	public List<VoCliente> buscarClientesPorGrupo(VoGrupocliente grupo) throws RegistrosNoEncontradosException, ErrorDelSistemaException;
	
	public List<VoCliente> buscarTodosLosClientes() throws RegistrosNoEncontradosException, ErrorDelSistemaException;
	
	public List<VoCliente> buscarClientesPorNombresApellidos(String nombres, String apellidos) throws RegistrosNoEncontradosException, ErrorDelSistemaException;
	
	
	public List<VoGrupocliente> buscarTodosGruposCliente() throws RegistrosNoEncontradosException, ErrorDelSistemaException;
	
	public String generarCredenciales(long codigoGrupoCliente) throws RegistrosNoEncontradosException, ErrorDelSistemaException;
	
	public void actualizarRegistro(VoCliente cliente) throws ErrorDelSistemaException;

}
