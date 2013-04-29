package cl.jsoft.solaria.web.backend.prestamos;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.PrestamoServicesEJB;
import cl.jsoft.solaria.web.backend.AbsMantenedorMB;

@ManagedBean
@ViewScoped
public class PrestamosMantenedorMB extends AbsMantenedorMB<VoPrestamo> {
	
	private static final long serialVersionUID = 1L;
	
	@EJB PrestamoServicesEJB prestamoServicesEJB;
	
	private List<String> opcionesEstados; 

	@Override
	public void controlarExcepcionAlGuardarRegistro(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejecutarActualizarRegistro(VoPrestamo registroEnEdicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejecutarNuevoRegistro(VoPrestamo registroEnEdicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VoPrestamo registroEnBlanco() {
		VoPrestamo blanco = new VoPrestamo();
		blanco.setVoCliente(new VoCliente());
		blanco.setVoLibro(new VoLibro());
		return blanco;
	}

	@Override
	public VoPrestamo clonarRegistro(VoPrestamo registroSeleccionado) {
		VoPrestamo clon = null;
		try {
			clon = (VoPrestamo) registroSeleccionado.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clon;
	}

	@Override
	public List<VoPrestamo> llenarRegistros() {
		try {
			return prestamoServicesEJB.buscarTodosPrestamosPendientes();
		} catch (ErrorDelSistemaException | RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getOpcionesEstados() {
		return opcionesEstados;
	}

	public void setOpcionesEstados(List<String> opcionesEstados) {
		this.opcionesEstados = opcionesEstados;
	}

}
