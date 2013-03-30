package cl.jsoft.solaria.jasper;

import javax.ejb.Local;

@Local
public interface GeneradorReporteEJB {
	
	public String generarCredencialesBiblioteca(long codGrupo);

}
