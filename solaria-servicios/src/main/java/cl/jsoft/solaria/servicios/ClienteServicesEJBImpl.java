package cl.jsoft.solaria.servicios;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cl.jsoft.solaria.daos.SolaTabClienteDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.entities.SolaTabCliente;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;

@Stateless
public class ClienteServicesEJBImpl implements ClienteServicesEJB {
	
	HelperVoEntity helperVoEntity = new HelperVoEntity();

	@EJB SolaTabClienteDAO clienteDAO;

	@Override
	public VoCliente buscarClientePorIdentificador(String nroRun)
			throws RegistrosNoEncontradosException {
		VoCliente respCliente = null;
		try {
			BigDecimal runCliente = getRUNChileno(nroRun);
			SolaTabCliente eCliente = clienteDAO.buscaRegistroPorIdentificador(runCliente);
			if (eCliente != null) {
				respCliente = helperVoEntity.toVO(eCliente);
			}else{
				throw new RegistrosNoEncontradosException("No existe un registro para el parametro:[ "+nroRun+" ]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegistrosNoEncontradosException("El registro no pudo ser encontrado debido a un error.");
		}
		return respCliente;
	}

	private BigDecimal getRUNChileno(String nroRun) {
		String[] runDV = nroRun.split("-");
		return new BigDecimal(runDV[0]);
	}

	@Override
	public List<VoCliente> buscarClientesPorGrupo(VoGrupocliente grupo)
			throws RegistrosNoEncontradosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoCliente> buscarTodosLosClientes(VoGrupocliente grupo)
			throws RegistrosNoEncontradosException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
