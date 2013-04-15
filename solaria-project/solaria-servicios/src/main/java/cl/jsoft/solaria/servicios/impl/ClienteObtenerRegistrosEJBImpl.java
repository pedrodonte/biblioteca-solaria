package cl.jsoft.solaria.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cl.jsoft.solaria.daos.SolaTabClienteDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.TransformadorDominio;
import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.entities.SolaTabCliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.api.ClienteObtenerRegistrosEJB;
import cl.jsoft.solaria.servicios.api.FiltroCliente;
import cl.jsoft.solaria.util.HelperFechas;

@Stateless
public class ClienteObtenerRegistrosEJBImpl implements
		ClienteObtenerRegistrosEJB {

	@EJB
	SolaTabClienteDAO clienteDAO;

	HelperFechas helperFechas = HelperFechas.getInstancia();
	HelperVoEntity helperVoEntity = new TransformadorDominio();

	@Override
	public List<VoCliente> obtenerRegistros(FiltroCliente filtroCliente)
			throws RegistrosNoEncontradosException, ErrorDelSistemaException {
		
		List<SolaTabCliente> entities = null;

		switch (filtroCliente) {
		case TODOS:
			entities = clienteDAO.findAll();
			break;

		case MOROSOS:
			//TODO FALTA IMPLEMENTAR
			break;

		default:
			throw new ErrorDelSistemaException("Buscar clientes filtro no valido.");
		}
		
		List<VoCliente> vos = new ArrayList<>();
		
		if (entities != null) {
			for(SolaTabCliente cli : entities){
				VoCliente voCliente = helperVoEntity.toVO(cli);
				
				vos.add(voCliente);
			}
		}else{
			throw new RegistrosNoEncontradosException("No existen registros de clientes.");
		}
		
		return vos;
	}

}
