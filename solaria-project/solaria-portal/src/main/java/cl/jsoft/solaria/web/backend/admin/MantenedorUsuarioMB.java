package cl.jsoft.solaria.web.backend.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.seguridad.api.MantenedorPerfilEJB;
import cl.jsoft.solaria.seguridad.api.MantenedorUsuarioEJB;
import cl.jsoft.solaria.seguridad.vos.VoPerfil;
import cl.jsoft.solaria.seguridad.vos.VoUsua;
import cl.jsoft.solaria.web.backend.AbsMantenedorMB;

@ManagedBean
@ViewScoped
public class MantenedorUsuarioMB extends AbsMantenedorMB<VoUsua> {

	private static final long serialVersionUID = 406800695867876511L;
	
	@EJB
	MantenedorUsuarioEJB mantenedorUsuarioEJB;
	@EJB MantenedorPerfilEJB mantenedorPerfilEJB;
	
	private List<VoPerfil> perfilesSeleccionados;
	
	@Override
	public void controlarExcepcionAlGuardarRegistro(Exception e) {
		super.mensajesMB.msgError(e.getMessage());
	}

	@Override
	public void ejecutarActualizarRegistro(VoUsua registroEnEdicion) {
		try {
			registroEnEdicion = mantenedorUsuarioEJB.actualizarRegistro(registroEnEdicion);
			mantenedorUsuarioEJB.asociarPerfiles(registroEnEdicion, perfilesSeleccionados);
		} catch (ErrorDelSistemaException e) {
			super.mensajesMB.msgInfo(e.getMessage());
		}
	}

	@Override
	public void ejecutarNuevoRegistro(VoUsua registroEnEdicion) {
		try {
			registroEnEdicion = mantenedorUsuarioEJB.nuevoRegistro(registroEnEdicion);
			mantenedorUsuarioEJB.asociarPerfiles(registroEnEdicion, perfilesSeleccionados);
		} catch (ErrorDelSistemaException e) {
			super.mensajesMB.msgInfo(e.getMessage());
		}
	}

	@Override
	public VoUsua registroEnBlanco() {
		perfilesSeleccionados = new ArrayList<>();
		return new VoUsua();
	}

	@Override
	public VoUsua clonarRegistro(VoUsua registroSeleccionado) {
		try {
			perfilesSeleccionados = mantenedorPerfilEJB
					.obtenerPerfilesPorUsuario(registroSeleccionado);
			return (VoUsua) registroSeleccionado.clone();
		} catch (CloneNotSupportedException e) {
			super.mensajesMB.msgFatal("Error al clonar registro para edición.");
		}
		return null;
	}

	@Override
	public List<VoUsua> llenarRegistros() {
		try {
			return mantenedorUsuarioEJB.obtenerRegistros();
		} catch (ErrorDelSistemaException e) {
			super.mensajesMB.msgInfo(e.getMessage());
		} catch (RegistrosNoEncontradosException e) {
			super.mensajesMB.msgInfo(e.getMessage());
		}
		return new ArrayList<>();
	}

	public List<VoPerfil> getPerfilesSeleccionados() {
		return perfilesSeleccionados;
	}

	public void setPerfilesSeleccionados(List<VoPerfil> perfilesSeleccionados) {
		this.perfilesSeleccionados = perfilesSeleccionados;
	} 

}
