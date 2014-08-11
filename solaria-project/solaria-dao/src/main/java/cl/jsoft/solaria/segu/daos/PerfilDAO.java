package cl.jsoft.solaria.segu.daos;

import javax.ejb.Stateless;

import cl.jsoft.solaria.daos.GenericDAO;
import cl.jsoft.solaria.segu.entities.SeguTabPerfil;

@Stateless
public class PerfilDAO extends GenericDAO<SeguTabPerfil, Long> {
	
	public PerfilDAO() {
		super(SeguTabPerfil.class);
	}

}
