package cl.jsoft.solaria.daos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import cl.jsoft.solaria.entities.SolaTabPrestamo;

@Stateless
public class SolaTabPrestamoDAO extends GenericDAO<SolaTabPrestamo, Long> {
	
	private static final String SQL_BUSCA_ATRASADOS = "select p " +
			" from SolaTabPrestamo as p where p.prestamoCodEstado = 10" +
			" and p.solaTabCliente.clienteIdentificador = :clienteIdentificador " +
			" order by p.prestamoFecPlazoEntrega desc";
	
	private static final String SQL_POR_ESTADO = "select p from SolaTabPrestamo p where " +
			" p.prestamoCodEstado = :prestamoCodEstado"+
			" order by p.prestamoFecPlazoEntrega desc";
	
	private static final String SQL_POR_ESTADO_CLIENTE = "select p from SolaTabPrestamo p " +
			" where  p.prestamoCodEstado = :prestamoCodEstado " +
			" and p.solaTabCliente.clienteCodCliente = :clienteCodCliente";
	
	private static final String SQL_CANDIDATOS_MOROSOS = "select p from SolaTabPrestamo p " +
			" where p.prestamoFecPlazoEntrega < current_date and p.prestamoCodEstado = 1 order by p.prestamoFecPlazoEntrega ASC";
	
	public static final String SQL_DISPONIBILIDAD = "select p from SolaTabPrestamo p " +
			" where p.prestamoCodEstado in (1,10) " +
			" and p.solaTabLibro.libroCodLibro = :libroCodLibro " +
			" order by p.prestamoCodEstado";
	
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
	
	public List<SolaTabPrestamo> buscaMorososCandidatos(){
		Map<String, Object> parameters = new HashMap<String, Object>();
		return super.findManyResult(SQL_CANDIDATOS_MOROSOS, parameters);
	}

	public List<SolaTabPrestamo> filtraPorCodigoEstado(
			BigDecimal prestamoCodEstado, long clienteCodCliente) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("prestamoCodEstado", prestamoCodEstado);
		parameters.put("clienteCodCliente", clienteCodCliente);
		return super.findManyResult(SQL_POR_ESTADO_CLIENTE, parameters);
	}
	
	public List<SolaTabPrestamo> buscarDisponibilidadLibro(long libroCodLibro) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("libroCodLibro", libroCodLibro);
		return super.findManyResult(SQL_DISPONIBILIDAD, parameters);
	}

}
