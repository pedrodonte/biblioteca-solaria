package cl.jsoft.solaria.seguridad.impl;

import javax.ejb.Stateless;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.seguridad.IValidacionCredencial;
import cl.jsoft.solaria.seguridad.ValidacionNegativaException;
import cl.jsoft.solaria.seguridad.api.LoginValidaCredencialEJB;
import cl.jsoft.solaria.seguridad.modelo.IUsuario;

@Stateless
public class LoginValidaCredencialEJBImpl implements LoginValidaCredencialEJB {

	IValidacionCredencial validaExistencia = new IValidacionCredencial() {

		@Override
		public void executaValidacion(Object objetoValidacion)
				throws ValidacionNegativaException {
			// TODO Auto-generated method stub

		}
	};

	IValidacionCredencial validaPassword = new IValidacionCredencial() {

		@Override
		public void executaValidacion(Object objetoValidacion)
				throws ValidacionNegativaException {
			// TODO Auto-generated method stub

		}
	};

	IValidacionCredencial validaEstado = new IValidacionCredencial() {

		@Override
		public void executaValidacion(Object objetoValidacion)
				throws ValidacionNegativaException {
			// TODO Auto-generated method stub

		}
	};

	@Override
	public void validaCredencial(IUsuario absUsuario)
			throws ValidacionNegativaException, ErrorDelSistemaException {

		try {
			validaExistencia.executaValidacion(absUsuario);
			validaPassword.executaValidacion(absUsuario);
			validaEstado.executaValidacion(absUsuario);
		} catch (ValidacionNegativaException e) {
			throw e;
		} catch (Exception e) {
			throw new ErrorDelSistemaException("Error irecuperable del sistema");
		}

	}

}
