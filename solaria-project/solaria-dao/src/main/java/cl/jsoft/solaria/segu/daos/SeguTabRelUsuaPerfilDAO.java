package cl.jsoft.solaria.segu.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import cl.jsoft.solaria.daos.GenericDAO;
import cl.jsoft.solaria.segu.entities.SeguTabRelUsuaPerfil;

@Stateless
public class SeguTabRelUsuaPerfilDAO extends
		GenericDAO<SeguTabRelUsuaPerfil, Long> {

	private static final String BUSCA_PERFILES_USUARIO = "select rel from SeguTabRelUsuaPerfil rel " +
			" where rel.seguTabUsua.usuaCodUsua = :usuaCodUsua and " +
			" rel.relVigente = true";

	public SeguTabRelUsuaPerfilDAO() {
		super(SeguTabRelUsuaPerfil.class);
	}

	public List<SeguTabRelUsuaPerfil> buscaPerfilesPorUsuario(long usuaCodUsua) {
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("usuaCodUsua", usuaCodUsua);
		return super.findManyResult(BUSCA_PERFILES_USUARIO, parameters);
		
	}

}
