package cl.jsoft.solaria.seguridad.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.segu.daos.PerfilDAO;
import cl.jsoft.solaria.segu.daos.UsuarioDAO;
import cl.jsoft.solaria.segu.entities.SeguTabUsua;
import cl.jsoft.solaria.seguridad.CredencialSeguridad;
import cl.jsoft.solaria.seguridad.IValidacionCredencial;
import cl.jsoft.solaria.seguridad.ValidacionNegativaException;
import cl.jsoft.solaria.seguridad.api.LoginValidaCredencialEJB;

@Stateless
public class LoginValidaCredencialEJBImpl implements LoginValidaCredencialEJB {
	
	Logger logger = Logger.getLogger(getClass());
	
	@EJB UsuarioDAO usuarioDAO;
	@EJB PerfilDAO perfilDAO;
	
	protected SeguTabUsua usuario;

	IValidacionCredencial validaExistencia = new IValidacionCredencial() {

		@Override
		public void executaValidacion(CredencialSeguridad credencialSeguridad)
				throws ValidacionNegativaException {
			
			
			usuario = usuarioDAO.buscaPorEmail(credencialSeguridad.getUsername());
			if (usuario == null) {
				throw new ValidacionNegativaException("registro no encontrado con el email ingresado.");
			}

		}
	};

	IValidacionCredencial validaPassword = new IValidacionCredencial() {

		@Override
		public void executaValidacion(CredencialSeguridad credencialSeguridad)
				throws ValidacionNegativaException {
			
			//usuario = usuarioDAO.buscaPorEmailContrasena(credencialSeguridad.getUsername(), credencialSeguridad.getPassword());
			if (usuario != null) {
				if (!usuario.getUsuaContrasena().equals(credencialSeguridad.getPassword())) {
					throw new ValidacionNegativaException("Contraseña invalida!");
				}
			}else{
				throw new ValidacionNegativaException("registro no encontrado al validar contraseña.");
			}
			
		}
	};

	IValidacionCredencial validaEstado = new IValidacionCredencial() {

		@Override
		public void executaValidacion(CredencialSeguridad credencialSeguridad)
				throws ValidacionNegativaException {
			// TODO Auto-generated method stub

		}
	};

	@Override
	public boolean validaCredencial(CredencialSeguridad credencialSeguridad)
			throws ValidacionNegativaException, ErrorDelSistemaException {

		try {
			validaExistencia.executaValidacion(credencialSeguridad);
			validaPassword.executaValidacion(credencialSeguridad);
			validaEstado.executaValidacion(credencialSeguridad);
			
			logger.info("Credencial Valida:"+credencialSeguridad.toString());
			
			return true;
		} catch (ValidacionNegativaException e) {
			throw e;
		} catch (Exception e) {
			throw new ErrorDelSistemaException("Error irecuperable del sistema");
		}
		
	}

}
