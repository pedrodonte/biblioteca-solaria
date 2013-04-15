package cl.jsoft.solaria.web.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.web.controllers.MensajesBean;

@SuppressWarnings("serial")
public abstract class AbsMantenedorMB<TIPO_DTO> implements Serializable {

	public enum ModosEdicion {
		OFF, EDIT, NEW
	};

	private Logger logger = Logger.getLogger(getClass());

	private boolean mostrarFormulario = false;
	private ModosEdicion modoFormulario = ModosEdicion.OFF;
	private List<TIPO_DTO> registros = new ArrayList<TIPO_DTO>();
	private TIPO_DTO registroSeleccionado;
	private TIPO_DTO registroEnEdicion;
	private int registrosCantidad;

	MensajesBean mensajesMB = new MensajesBean();

	@PostConstruct
	public void inicializarVariablesInstancia() {
		try {
			registros = llenarRegistros();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doHabilitarEdicion(ActionEvent event) {
		logger.info("Habilitando edición");
		setMostrarFormulario(true);
		setModoFormulario(ModosEdicion.EDIT);
	}

	public void doVerRegistroFormulario(ActionEvent event) {
		logger.info("Ver Registro formulario");
		setRegistroEnEdicion(clonarRegistro(registroSeleccionado));
		setMostrarFormulario(true);
		setModoFormulario(ModosEdicion.OFF);
	}

	public void doNuevoRegistroFormulario(ActionEvent event) {
		logger.info("Creando nuevo registro.");
		setRegistroEnEdicion(registroEnBlanco());
		setMostrarFormulario(true);
		setModoFormulario(ModosEdicion.NEW);
	}

	public void doEditarRegistroFormulario(ActionEvent event) {
		logger.info("Editar registro formulario");
		setRegistroEnEdicion(clonarRegistro(registroSeleccionado));
		setMostrarFormulario(true);
		setModoFormulario(ModosEdicion.EDIT);
	}


	public void doGuardarRegistroFormulario(ActionEvent event) {
		boolean codExitoOperacion = false;
		logger.info("Guardando cambios en registro:" + registroEnEdicion);
		logger.info("Modo Formulario:" + modoFormulario);
		try {

			if (modoFormulario.equals(ModosEdicion.EDIT)) {
				ejecutarActualizarRegistro(registroEnEdicion);
			} else if (modoFormulario.equals(ModosEdicion.NEW)) {
				ejecutarNuevoRegistro(registroEnEdicion);
			}
			registros = llenarRegistros();
			setMostrarFormulario(false);
			setModoFormulario(ModosEdicion.OFF);
			inicializarVariablesInstancia();
			codExitoOperacion = true;
		} catch (Exception e) {
			controlarExcepcionAlGuardarRegistro(e);
		}
		mensajesMB.devolverParametro("codExitoOperacion", codExitoOperacion);
	}

	public void doCancelarRegistroFormulario(ActionEvent event) {
		setMostrarFormulario(false);
		setModoFormulario(ModosEdicion.OFF);
	}

	public abstract void controlarExcepcionAlGuardarRegistro(Exception e);

	public abstract void ejecutarActualizarRegistro(TIPO_DTO registroEnEdicion);

	public abstract void ejecutarNuevoRegistro(TIPO_DTO registroEnEdicion);

	public abstract TIPO_DTO registroEnBlanco();

	public abstract TIPO_DTO clonarRegistro(TIPO_DTO registroSeleccionado);

	public abstract List<TIPO_DTO> llenarRegistros();

	public boolean isMostrarFormulario() {
		return mostrarFormulario;
	}

	public void setMostrarFormulario(boolean mostrarFormulario) {
		this.mostrarFormulario = mostrarFormulario;
	}

	public ModosEdicion getModoFormulario() {
		return modoFormulario;
	}

	public void setModoFormulario(ModosEdicion modoFormulario) {
		this.modoFormulario = modoFormulario;
	}

	public List<TIPO_DTO> getRegistros() {
		return registros;
	}

	public void setRegistros(List<TIPO_DTO> registros) {
		this.registros = registros;
	}

	public TIPO_DTO getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(TIPO_DTO registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public TIPO_DTO getRegistroEnEdicion() {
		return registroEnEdicion;
	}

	public void setRegistroEnEdicion(TIPO_DTO registroEnEdicion) {
		this.registroEnEdicion = registroEnEdicion;
	}

	public int getRegistrosCantidad() {
		registrosCantidad = registros.size();
		return registrosCantidad;
	}

	public void setRegistrosCantidad(int registrosCantidad) {
		this.registrosCantidad = registrosCantidad;
	}

}
