package cl.jsoft.solaria.servicios.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cl.jsoft.solaria.daos.SolaTabClienteDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.servicios.api.ClienteUpdateRegistroEJB;
import cl.jsoft.solaria.util.HelperFechas;

@Stateless
public class ClienteUpdateRegistroEJBImpl implements ClienteUpdateRegistroEJB {

	@EJB
	SolaTabClienteDAO clienteDAO;

	HelperFechas helperFechas = HelperFechas.getInstancia();
	HelperVoEntity helperVoEntity = new HelperVoEntity();

	@Override
	public void updateRegistro(VoCliente voCliente)
			throws ErrorDelSistemaException {
		try {
			voCliente.setClienteFecUpdate(helperFechas.obtenerDateActual());
			clienteDAO.update(helperVoEntity.toEntity(voCliente));
		} catch (Exception e) {
			throw new ErrorDelSistemaException("Error al guardar el registro.");
		}

	}

}
