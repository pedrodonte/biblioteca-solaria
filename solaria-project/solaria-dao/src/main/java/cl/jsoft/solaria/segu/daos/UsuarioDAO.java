package cl.jsoft.solaria.segu.daos;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import cl.jsoft.solaria.daos.GenericDAO;
import cl.jsoft.solaria.segu.entities.SeguTabUsua;

@Stateless
public class UsuarioDAO extends GenericDAO<SeguTabUsua, Long> {
	
	public UsuarioDAO() {
		super(SeguTabUsua.class);
	}
	
	private static final String SQL_FILTRAR_POR_EMAIL = "select reg from SeguTabUsua reg where reg.usuaEmail =:email";
	private static final String SQL_VALIDA_EMAIL_CONTRASENA = "select reg from SeguTabUsua reg where reg.usuaEmail =:email and reg.usuaContrasena=:password";
	
	public SeguTabUsua buscaPorEmail(String email) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		return super.findOneResult(SQL_FILTRAR_POR_EMAIL, parameters);
	}

	public SeguTabUsua buscaPorEmailContrasena(String email, String password) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		parameters.put("password", password);
		return super.findOneResult(SQL_VALIDA_EMAIL_CONTRASENA, parameters);
	}

}
