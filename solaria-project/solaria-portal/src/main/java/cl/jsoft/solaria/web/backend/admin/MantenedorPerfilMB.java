package cl.jsoft.solaria.web.backend.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.seguridad.api.MantenedorPerfilEJB;
import cl.jsoft.solaria.seguridad.api.MantenedorUsuarioEJB;
import cl.jsoft.solaria.seguridad.vos.VoPerfil;
import cl.jsoft.solaria.seguridad.vos.VoUsua;
import cl.jsoft.solaria.web.backend.AbsMantenedorMB;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean
@ViewScoped
public class MantenedorPerfilMB extends AbsMantenedorMB<VoPerfil> {

	private static final long serialVersionUID = 406800695867876511L;

	Logger logger = Logger.getLogger(getClass());

	@EJB
	MantenedorPerfilEJB mantenedorPerfilEJB;

	@EJB
	MantenedorUsuarioEJB mantenedorUsuarioEJB;

	private List<VoPerfil> perfilesSeleccionados;

	private VoUsua usuarioSeleccionado;
	private List<VoUsua> usuarios;
	
	public void doAsociar(ActionEvent actionEvent){
		mantenedorUsuarioEJB.asociarPerfiles(usuarioSeleccionado, perfilesSeleccionados);
	}

	public void seleccionaUsuario(AjaxBehaviorEvent ajaxBehaviorEvent) {

		if (usuarioSeleccionado != null) {
			logger.info(usuarioSeleccionado.getUsuaEmail() + " Buscando...");
			perfilesSeleccionados = mantenedorPerfilEJB
					.obtenerPerfilesPorUsuario(usuarioSeleccionado);
		} else {
			super.mensajesMB.msgWarn("No se selecciona ningun curso.");
			perfilesSeleccionados = new ArrayList<>();
		}

	}

	@Override
	public void controlarExcepcionAlGuardarRegistro(Exception e) {

	}

	@Override
	public void ejecutarActualizarRegistro(VoPerfil registroEnEdicion) {
		try {
			mantenedorPerfilEJB.actualizarRegistro(registroEnEdicion);
		} catch (ErrorDelSistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void ejecutarNuevoRegistro(VoPerfil registroEnEdicion) {
		try {
			mantenedorPerfilEJB.nuevoRegistro(registroEnEdicion);
		} catch (ErrorDelSistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public VoPerfil registroEnBlanco() {
		return new VoPerfil();
	}

	@Override
	public VoPerfil clonarRegistro(VoPerfil registroSeleccionado) {
		try {
			return (VoPerfil) registroSeleccionado.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VoPerfil> llenarRegistros() {
		//usuarios = mantenedorUsuarioEJB.obtenerRegistros();
		try {
			return mantenedorPerfilEJB.obtenerRegistros();
		} catch (ErrorDelSistemaException | RegistrosNoEncontradosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<VoPerfil> getPerfilesSeleccionados() {
		return perfilesSeleccionados;
	}

	public void setPerfilesSeleccionados(List<VoPerfil> perfilesSeleccionados) {
		this.perfilesSeleccionados = perfilesSeleccionados;
	}

	public VoUsua getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(VoUsua usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<VoUsua> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<VoUsua> usuarios) {
		this.usuarios = usuarios;
	}

}
