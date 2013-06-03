package cl.jsoft.solaria.web.backend.prestamos;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoEstadisticasPrestamos;
import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.PrestamoNoValidoException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.PrestamoServicesEJB;
import cl.jsoft.solaria.servicios.api.FiltroPrestamos;
import cl.jsoft.solaria.web.backend.AbsMantenedorMB;

@ManagedBean
@ViewScoped
public class PrestamosMantenedorMB extends AbsMantenedorMB<VoPrestamo> {
	
	private static final long serialVersionUID = 1L;
	
	
	@EJB PrestamoServicesEJB prestamoServicesEJB;
	private String estadoSeleccionado = FiltroPrestamos.ATRASADOS.toString();
	
	public void verTipo(AjaxBehaviorEvent ajaxBehaviorEvent) {
		try {
			super.setRegistros(prestamoServicesEJB.buscarPrestamosPorEstado(FiltroPrestamos.valueOf( estadoSeleccionado)));
		} catch (ErrorDelSistemaException | RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void controlarExcepcionAlGuardarRegistro(Exception e) {
		super.mensajesMB.msgError(e.getMessage());	
	}

	@Override
	public void ejecutarActualizarRegistro(VoPrestamo registroEnEdicion) {
		try {
			prestamoServicesEJB.devolverPrestamo(registroEnEdicion);
			llenarRegistros();
		} catch (PrestamoNoValidoException | ErrorDelSistemaException e) {
			super.mensajesMB.msgError(e.getMessage());	
		}
	}

	@Override
	public void ejecutarNuevoRegistro(VoPrestamo registroEnEdicion) {}

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
			super.mensajesMB.msgError(e.getMessage());
		}
		return clon;
	}

	@Override
	public List<VoPrestamo> llenarRegistros() {
		try {
			return prestamoServicesEJB.buscarPrestamosPorEstado(FiltroPrestamos.ATRASADOS);
		} catch (ErrorDelSistemaException | RegistrosNoEncontradosException e) {
			super.mensajesMB.msgError(e.getMessage());
		}
		return null;
	}

	public String getEstadoSeleccionado() {
		return estadoSeleccionado;
	}

	public void setEstadoSeleccionado(String estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}


}
