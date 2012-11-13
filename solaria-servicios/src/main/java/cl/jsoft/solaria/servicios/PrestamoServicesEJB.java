package cl.jsoft.solaria.servicios;

import java.util.List;

import javax.ejb.Local;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.PrestamoNoValidoException;

@Local
public interface PrestamoServicesEJB {
		
	public List<VoPrestamo> buscarPrestamosHistoricos(VoCliente voCliente) throws ErrorDelSistemaException;
	
	public List<VoPrestamo> buscarPrestamosPendientes(VoCliente voCliente) throws ErrorDelSistemaException;
	
	public List<VoPrestamo> buscarTodosPrestamosHistoricos() throws ErrorDelSistemaException;
	
	public List<VoPrestamo> buscarTodosPrestamosPendientes() throws ErrorDelSistemaException;
	
	public VoPrestamo nuevoPrestamo(VoPrestamo voPrestamo) throws PrestamoNoValidoException, ErrorDelSistemaException;
	
	public boolean validarPrestamo(VoPrestamo voPrestamo);

}
