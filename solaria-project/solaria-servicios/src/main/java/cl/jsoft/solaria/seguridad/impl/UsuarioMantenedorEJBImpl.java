package cl.jsoft.solaria.seguridad.impl;

import java.util.List;

import javax.ejb.Stateless;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.seguridad.api.UsuarioMantenedorEJB;
import cl.jsoft.solaria.seguridad.modelo.BuscaUsuarios;
import cl.jsoft.solaria.seguridad.modelo.IUsuario;

@Stateless
public class UsuarioMantenedorEJBImpl implements UsuarioMantenedorEJB{

	@Override
	public void guardarNuevoUsuario(IUsuario iUsuario)
			throws ErrorDelSistemaException {
		//TODO falta implementacion
	}

	@Override
	public void modificarNuevoUsuario(IUsuario iUsuario)
			throws ErrorDelSistemaException {
		//TODO falta implementacion
	}

	@Override
	public void desactivarUsuario(IUsuario iUsuario)
			throws ErrorDelSistemaException {
		//TODO falta implementacion
	}

	@Override
	public List<IUsuario> obtenerUsuariosRegistrados(BuscaUsuarios buscaUsuarios)
			throws ErrorDelSistemaException {
		//TODO falta implementacion
		return null;
	}
	
	
}
