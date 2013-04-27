package cl.jsoft.solaria.servicios;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.excepciones.ClienteMorosoException;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.PrestamoNoValidoException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;

@Local
public interface PrestamoServicesEJB {
	
	public static final BigDecimal PRESTAMO_VIGENTE = new BigDecimal(1);
	public static final BigDecimal PRESTAMO_ATRASADO = new BigDecimal(10);
	public static final BigDecimal PRESTAMO_DEVUELTO = new BigDecimal(2);
		
	public List<VoPrestamo> buscarPrestamosPendientes(VoCliente voCliente) throws ErrorDelSistemaException;
	
	public List<VoPrestamo> buscarTodosPrestamosHistoricos() throws ErrorDelSistemaException;
	
	public List<VoPrestamo> buscarTodosPrestamos() throws ErrorDelSistemaException,RegistrosNoEncontradosException;
	
	public List<VoPrestamo> buscarTodosPrestamosPendientes() throws ErrorDelSistemaException, RegistrosNoEncontradosException;
	
	public VoPrestamo nuevoPrestamo(VoPrestamo voPrestamo) throws PrestamoNoValidoException, ErrorDelSistemaException;
	
	public void devolverPrestamo(VoPrestamo voPrestamo) throws PrestamoNoValidoException, ErrorDelSistemaException;
	
	public boolean validarPrestamo(VoPrestamo voPrestamo) throws PrestamoNoValidoException;
	
	public boolean verificarMorosidad(VoCliente voCliente) throws ClienteMorosoException, ErrorDelSistemaException;

}
