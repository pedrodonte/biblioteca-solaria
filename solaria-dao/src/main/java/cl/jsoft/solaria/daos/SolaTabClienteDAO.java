package cl.jsoft.solaria.daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import cl.jsoft.solaria.entities.SolaTabCliente;

@Stateless
public class SolaTabClienteDAO extends GenericDAO<SolaTabCliente, Long> {
	
	public static final String CLIENTE_COD_CLIENTE = "clienteCodCliente";
	public static final String CLIENTE_APELLIDOS = "clienteApellidos";
	public static final String CLIENTE_DIRECCION = "clienteDireccion";
	public static final String CLIENTE_FEC_INSERT = "clienteFecInsert";
	public static final String CLIENTE_FEC_NACIMIENTO = "clienteFecNacimiento";
	public static final String CLIENTE_FEC_UPDATE = "clienteFecUpdate";
	public static final String CLIENTE_IDENTIFICADOR = "clienteIdentificador";
	public static final String CLIENTE_IMG = "clienteImg";
	public static final String CLIENTE_NOMBRES = "clienteNombres";
	public static final String SOLA_TAB_GRUPOCLIENTE = "solaTabGrupocliente";
	
	
	private static final String SQL_FILTRAR_POR_IDENTIFICADOR_ONE = "select model from SolaTabCliente model where model.clienteIdentificador = :clienteIdentificador";
	private static final String SQL_FILTRAR_POR_NOMBRE_APELLIDO = "select m from SolaTabCliente as m " +
			" where lower( concat(m.clienteNombres,' ',m.clienteApellidos) ) like :nombre_apellido order by m.clienteNombres";
	
	public SolaTabClienteDAO() {
		super(SolaTabCliente.class);
	}
	
	public SolaTabCliente buscaRegistroPorIdentificador(BigDecimal runCliente) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(CLIENTE_IDENTIFICADOR, runCliente);
		return super.findOneResult(SQL_FILTRAR_POR_IDENTIFICADOR_ONE, parameters);
	}
	
	public List<SolaTabCliente> buscaRegistroPorNombreApellido(String nombres, String apellidos) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre_apellido", "%"+nombres+"%"+apellidos+"%");
		return super.findManyResult(SQL_FILTRAR_POR_NOMBRE_APELLIDO, parameters);
	}

	@Override
	public SolaTabCliente save(SolaTabCliente entity) {
		entity.setClienteFecInsert(new Date());
		return super.save(entity);
	}

	@Override
	public SolaTabCliente update(SolaTabCliente entity) {
		entity.setClienteFecUpdate(new Date());
		return super.update(entity);
	}
	
	
}
