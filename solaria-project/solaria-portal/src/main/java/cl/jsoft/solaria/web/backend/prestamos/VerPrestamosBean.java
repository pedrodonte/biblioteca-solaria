package cl.jsoft.solaria.web.backend.prestamos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.PrestamoServicesEJB;
import cl.jsoft.solaria.servicios.api.FiltroPrestamos;
import cl.jsoft.solaria.util.HelperFechas;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean
@ViewScoped
public class VerPrestamosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());

	public enum ModosEdicion {
		OFF, EDIT, NEW
	};

	private ModosEdicion modoEdicion = ModosEdicion.OFF;

	private boolean mostrarFormulario;

	@EJB
	PrestamoServicesEJB prestamoServicesEJB;

	private List<VoPrestamo> registros = new ArrayList<>();
	private VoPrestamo registroSeleccionado = new VoPrestamo();
	private VoPrestamo registroEnEdicion = new VoPrestamo();
	
	MensajesBean mensajesBean = new MensajesBean();

	/**
	 * ESTE METODO NO APLICA PARA EL MANTENEDOR DE PERFILES, YA QUE NO SE
	 * PERMITE CREAR NUEVOS.
	 */
	public void doNuevoRegistroFormulario(ActionEvent event) {
//		setRegistroEnEdicion(new VoPrestamo());
//		setMostrarFormulario(true);
//		setModo(ModosEdicion.NEW);
	}

	public void doEditarRegistroFormulario(ActionEvent event) {
		try {
			setRegistroEnEdicion((VoPrestamo) registroSeleccionado.clone());
			registroEnEdicion.setPrestamoFecDevReal(HelperFechas.getInstancia().obtenerTimeStampActual());
			logger.debug("Editando " + getRegistroEnEdicion());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		setMostrarFormulario(true);
		setModoEdicion(ModosEdicion.EDIT);
	}

	public void doGuardarRegistroFormulario(ActionEvent event) {
		boolean codExitoOperacion = false;
		try {
			logger.debug("Guardano Registro: " + registroEnEdicion);
			if (modoEdicion.equals(ModosEdicion.EDIT)) {
				prestamoServicesEJB.devolverPrestamo(registroEnEdicion);
			} else {
				logger.debug("Nada con Registro: " + registroEnEdicion);
			}
			setMostrarFormulario(false);
			setModoEdicion(ModosEdicion.OFF);
			inicializarVariablesInstancia();
			codExitoOperacion = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		mensajesBean.devolverParametro("codExitoOperacion", codExitoOperacion);
	}

	public void doCancelarRegistroFormulario(ActionEvent event) {
		setMostrarFormulario(false);
		setModoEdicion(ModosEdicion.OFF);
	}

	@PostConstruct
	public void inicializarVariablesInstancia() {
		try {
			setRegistros(prestamoServicesEJB.buscarPrestamosPorEstado(FiltroPrestamos.ATRASADOS));
			logger.debug(registros.size()+" Prestamos encontrados y mostrando.");
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		} catch (RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
	}

	public List<VoPrestamo> getRegistros() {
		return registros;
	}

	public void setRegistros(List<VoPrestamo> registros) {
		this.registros = registros;
	}

	public VoPrestamo getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(VoPrestamo registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public boolean isMostrarFormulario() {
		return mostrarFormulario;
	}

	public void setMostrarFormulario(boolean mostrarFormulario) {
		this.mostrarFormulario = mostrarFormulario;
	}

	public VoPrestamo getRegistroEnEdicion() {
		return registroEnEdicion;
	}

	public void setRegistroEnEdicion(VoPrestamo registroEnEdicion) {
		this.registroEnEdicion = registroEnEdicion;
	}

	public ModosEdicion getModoEdicion() {
		return modoEdicion;
	}

	public void setModoEdicion(ModosEdicion modoEdicion) {
		this.modoEdicion = modoEdicion;
	}

}
