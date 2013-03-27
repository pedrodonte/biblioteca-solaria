package cl.jsoft.solaria.daos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import cl.jsoft.solaria.entities.SolaTabPrestamo;

@Stateless
public class SolaTabPrestamoDAO extends GenericDAO<SolaTabPrestamo, Long> {
	
	public static final String PRESTAMO_COD_PRESTAMO = "prestamoCodPrestamo";
	public static final String PRESTAMO_COD_ESTADO = "prestamoCodEstado";
	public static final String PRESTAMO_FEC_DEV_REAL = "prestamoFecDevReal";
	public static final String PRESTAMO_FEC_INICIO = "prestamoFecInicio";
	public static final String PRESTAMO_FEC_INSERT = "prestamoFecInsert";
	public static final String PRESTAMO_FEC_PLAZO_ENTREGA = "prestamoFecPlazoEntrega";
	public static final String PRESTAMO_FEC_UPDATE = "prestamoFecUpdate";
	public static final String SOLA_TAB_CLIENTE = "solaTabCliente";
	public static final String SOLA_TAB_LIBRO = "solaTabLibro";
	
	private static final String SQL_BUSCA_ATRASADOS = "select p " +
			" from SolaTabPrestamo as p where p.prestamoFecPlazoEntrega < current_date " +
			" and p.solaTabCliente.clienteIdentificador = :clienteIdentificador " +
			" order by p.prestamoFecPlazoEntrega desc";
	
	public SolaTabPrestamoDAO() {
		super(SolaTabPrestamo.class);
	}

	public List<SolaTabPrestamo> buscaAtrasados(BigDecimal clienteIdentificador) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clienteIdentificador", clienteIdentificador);
		return super.findManyResult(SQL_BUSCA_ATRASADOS, parameters);
	}
	
	
	
}
