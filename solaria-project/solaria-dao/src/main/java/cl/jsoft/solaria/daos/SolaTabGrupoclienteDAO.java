package cl.jsoft.solaria.daos;

import javax.ejb.Stateless;

import cl.jsoft.solaria.entities.SolaTabGrupocliente;

@Stateless
public class SolaTabGrupoclienteDAO extends GenericDAO<SolaTabGrupocliente, Long> {
	
	public static final String GRUPOCLIENTE_COD_GRUPOCLIENTE = "grupoclienteCodGrupocliente";
	public static final String GRUPOCLIENTE_NOMBRE = "grupoclienteNombre";
	
	public SolaTabGrupoclienteDAO() {
		super(SolaTabGrupocliente.class);
	}
	
}
