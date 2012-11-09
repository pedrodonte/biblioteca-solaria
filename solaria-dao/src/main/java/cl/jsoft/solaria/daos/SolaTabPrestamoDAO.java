package cl.jsoft.solaria.daos;

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
	
	public SolaTabPrestamoDAO() {
		super(SolaTabPrestamo.class);
	}
	
}
