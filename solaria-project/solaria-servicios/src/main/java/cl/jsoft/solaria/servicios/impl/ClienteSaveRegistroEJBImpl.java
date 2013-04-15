package cl.jsoft.solaria.servicios.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cl.jsoft.solaria.daos.SolaTabClienteDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.servicios.api.ClienteSaveRegistroEJB;
import cl.jsoft.solaria.util.HelperFechas;

@Stateless
public class ClienteSaveRegistroEJBImpl implements ClienteSaveRegistroEJB {

	@EJB
	SolaTabClienteDAO clienteDAO;

	HelperFechas helperFechas = HelperFechas.getInstancia();
	HelperVoEntity helperVoEntity = new HelperVoEntity();

	@Override
	public void saveRegistro(VoCliente voCliente)
			throws ErrorDelSistemaException {
		try {
			voCliente.setClienteFecInsert(helperFechas.obtenerDateActual());
			clienteDAO.save(helperVoEntity.toEntity(voCliente));
		} catch (Exception e) {
			throw new ErrorDelSistemaException("Error al guardar el registro.");
		}

	}

}
