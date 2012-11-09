package cl.jsoft.solaria.daos;

import javax.ejb.Stateless;

import cl.jsoft.solaria.entities.SolaTabPrestamo;

@Stateless
public class SolaTabGenerolibroDAO extends GenericDAO<SolaTabPrestamo, Long> {
	
	public static final String GENEROLIBRO_COD_GENEROLIBRO = "generolibroCodGenerolibro";
	public static final String GENEROLIBRO_NOMBRE = "generolibroNombre";
	
	public SolaTabGenerolibroDAO() {
		super(SolaTabPrestamo.class);
	}
	
}
