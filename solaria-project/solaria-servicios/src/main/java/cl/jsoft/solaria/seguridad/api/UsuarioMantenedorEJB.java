package cl.jsoft.solaria.seguridad.api;

import java.util.List;

import javax.ejb.Local;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.seguridad.modelo.BuscaUsuarios;
import cl.jsoft.solaria.seguridad.modelo.IUsuario;

@Local
public interface UsuarioMantenedorEJB {
	
	public void guardarNuevoUsuario(IUsuario iUsuario)		throws ErrorDelSistemaException;
	public void modificarNuevoUsuario(IUsuario iUsuario)	throws ErrorDelSistemaException;
	public void desactivarUsuario(IUsuario iUsuario)		throws ErrorDelSistemaException;
	public List<IUsuario> obtenerUsuariosRegistrados(BuscaUsuarios buscaUsuarios)	throws ErrorDelSistemaException;
	
}
