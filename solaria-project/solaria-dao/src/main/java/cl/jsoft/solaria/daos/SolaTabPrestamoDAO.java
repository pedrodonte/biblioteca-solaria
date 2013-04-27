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
			" from SolaTabPrestamo as p where p.prestamoCodEstado = 10" +
			" and p.solaTabCliente.clienteIdentificador = :clienteIdentificador " +
			" order by p.prestamoFecPlazoEntrega desc";
	
	private static final String SQL_POR_ESTADO = "select p from SolaTabPrestamo p where " +
			" p.prestamoCodEstado = :prestamoCodEstado"+
			" order by p.prestamoFecPlazoEntrega desc";
	
	private static final String SQL_POR_ESTADO_CLIENTE = "select p from SolaTabPrestamo p " +
			" where p.prestamoCodEstado = :codEstado " +
			" and p.solaTabCliente.clienteCodCliente = :codCliente ";
	
	public SolaTabPrestamoDAO() {
		super(SolaTabPrestamo.class);
	}

	public List<SolaTabPrestamo> buscaAtrasados(BigDecimal clienteIdentificador) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clienteIdentificador", clienteIdentificador);
		return super.findManyResult(SQL_BUSCA_ATRASADOS, parameters);
	}
	
	public List<SolaTabPrestamo> filtraPorCodigoEstado(BigDecimal prestamoCodEstado) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("prestamoCodEstado", prestamoCodEstado);
		return super.findManyResult(SQL_POR_ESTADO, parameters);
	}
	
	public List<SolaTabPrestamo> buscaPorEstadoUsuario(Integer codEstado, BigDecimal codCliente) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("codEstado", codEstado);
		parameters.put("codCliente", codCliente);
		return super.findManyResult(SQL_POR_ESTADO_CLIENTE, parameters);
	}

}
