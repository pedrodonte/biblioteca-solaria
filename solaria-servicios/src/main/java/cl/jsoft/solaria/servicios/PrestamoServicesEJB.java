package cl.jsoft.solaria.servicios;

import java.util.List;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;

public interface PrestamoServicesEJB {
		
	public List<VoPrestamo> buscarPrestamosHistoricos(VoCliente voCliente);
	
	public List<VoPrestamo> buscarPrestamosPendientes(VoCliente voCliente);
	
	public List<VoPrestamo> buscarTodosPrestamosHistoricos();
	
	public List<VoPrestamo> buscarTodosPrestamosPendientes();
	
	public VoPrestamo nuevoPrestamo(VoPrestamo voPrestamo);
	
	public boolean validarPrestamo(VoPrestamo voPrestamo);

}
