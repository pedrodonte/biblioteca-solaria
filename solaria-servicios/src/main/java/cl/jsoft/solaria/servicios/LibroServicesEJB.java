package cl.jsoft.solaria.servicios;

import cl.jsoft.solaria.dominio.vos.VoLibro;

public interface LibroServicesEJB {

	public VoLibro buscarLibroCodigoInterno(String codigoInterno);
	
	public boolean validarDisponibilidadLibro(VoLibro voLibro);

}
