package cl.jsoft.solaria.servicios;

import java.util.List;

import javax.ejb.Local;

import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.excepciones.LibroNoDisponibleException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;

@Local
public interface LibroServicesEJB {

	public VoLibro buscarLibroCodigoInterno(String codigoInterno) throws RegistrosNoEncontradosException;
	
	public List<VoLibro> buscarLibrosPorAutor(String strAutor) throws RegistrosNoEncontradosException;
	
	public List<VoLibro> buscarLibrosPorCodigoInterno(String codigoInterno) throws RegistrosNoEncontradosException;
	
	public List<VoLibro> buscarLibrosPorTitulo(String srtTitulo) throws RegistrosNoEncontradosException;
	
	public boolean validarDisponibilidadLibro(VoLibro voLibro) throws LibroNoDisponibleException;
	

}
