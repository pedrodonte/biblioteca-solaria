package cl.jsoft.solaria.servicios;


import java.util.List;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;

public interface CrudGenericServiceApi<VO> {
	
	public VO nuevoRegistro(VO registro) throws ErrorDelSistemaException;
	public VO actualizarRegistro(VO registro) throws ErrorDelSistemaException;
	public void eliminarRegistro(VO registro) throws ErrorDelSistemaException;
	public List<VO> obtenerRegistros() throws ErrorDelSistemaException, RegistrosNoEncontradosException;

}
