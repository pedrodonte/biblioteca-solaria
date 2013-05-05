package cl.jsoft.solaria.web.backend.clientes;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;
import cl.jsoft.solaria.servicios.api.ClienteObtenerRegistrosEJB;
import cl.jsoft.solaria.servicios.api.ClienteSaveRegistroEJB;
import cl.jsoft.solaria.servicios.api.ClienteUpdateRegistroEJB;
import cl.jsoft.solaria.web.backend.AbsMantenedorMB;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean
@ViewScoped
public class MantenedorClientesMB extends AbsMantenedorMB<VoCliente> {

	private static final long serialVersionUID = 6559543381444084405L;

	MensajesBean mensajesBean = new MensajesBean();

	@EJB
	ClienteObtenerRegistrosEJB clienteobtenerRegistrosEJB;
	@EJB
	ClienteSaveRegistroEJB clienteSaveRegistroEJB;
	@EJB
	ClienteUpdateRegistroEJB clienteUpdateRegistroEJB;

	private List<VoGrupocliente> gruposCliente;
	private VoGrupocliente grupoclienteSeleccionado;

	@EJB
	ClienteServicesEJB clienteServicesEJB;

	Logger logger = Logger.getLogger(getClass());

	@Override
	public void controlarExcepcionAlGuardarRegistro(Exception e) {
		mensajesBean.msgError("Error al guardar los cambio en el registro.");
	}

	@Override
	public void ejecutarActualizarRegistro(VoCliente registroEnEdicion) {
		try {
			clienteUpdateRegistroEJB.updateRegistro(registroEnEdicion);
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ejecutarNuevoRegistro(VoCliente registroEnEdicion) {
		try {
			clienteSaveRegistroEJB.saveRegistro(registroEnEdicion);
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		}

	}

	@Override
	public VoCliente registroEnBlanco() {
		VoGrupocliente grupocliente = new VoGrupocliente();
		VoCliente voCliente = new VoCliente();
		voCliente.setVoGrupocliente(grupocliente);
		return voCliente;
	}

	@Override
	public VoCliente clonarRegistro(VoCliente registroSeleccionado) {
		try {
			return (VoCliente) registroSeleccionado.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return registroSeleccionado;
	}

	@Override
	public List<VoCliente> llenarRegistros() {
		try {
			gruposCliente = clienteServicesEJB.buscarTodosGruposCliente();
		} catch (RegistrosNoEncontradosException | ErrorDelSistemaException e) {
			logger.info(e);
		}
		List<VoCliente> registros = new ArrayList<>();
		return registros;
	}

	public void doSeleccionaGrupo(AjaxBehaviorEvent ajaxBehaviorEvent) {

		try {
			logger.debug(grupoclienteSeleccionado + " Buscando GC.");
			if (grupoclienteSeleccionado != null) {
				setRegistros(clienteServicesEJB
						.buscarClientesPorGrupo(grupoclienteSeleccionado));
			}else{
				mensajesBean.msgWarn("No se selecciona ningun curso.");
				setRegistros(new ArrayList<VoCliente>());
			}
		} catch (RegistrosNoEncontradosException e) {
			mensajesBean.msgWarn(e.getMessage());
		} catch (ErrorDelSistemaException e) {
			mensajesBean.msgWarn(e.getMessage());
			setRegistros(new ArrayList<VoCliente>());
		}
	}

	public List<VoGrupocliente> getGruposCliente() {
		return gruposCliente;
	}

	public void setGruposCliente(List<VoGrupocliente> gruposCliente) {
		this.gruposCliente = gruposCliente;
	}

	public VoGrupocliente getGrupoclienteSeleccionado() {
		return grupoclienteSeleccionado;
	}

	public void setGrupoclienteSeleccionado(
			VoGrupocliente grupoclienteSeleccionado) {
		this.grupoclienteSeleccionado = grupoclienteSeleccionado;
	}

}
