package cl.jsoft.solaria.web.backend.credenciales;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;

import cl.jsoft.solaria.servicios.ClienteServicesEJB;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean
@ViewScoped
public class CredencialesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());

	@EJB ClienteServicesEJB clienteServicesEJB;

	@ManagedProperty(value="#{mensajesBean}")
	private MensajesBean mensajesBean;
	
	private VoGrupocliente grupoSeleccionado = new VoGrupocliente();
	private String archivoDescargar = "";



	public void doSeleccionaGrupo(ValueChangeEvent changeEvent){
		grupoSeleccionado = (VoGrupocliente) changeEvent.getNewValue();
		logger.debug("Grupo Seleccionado: " + grupoSeleccionado);
	}
	
	public void doGenerarCredenciales(ActionEvent actionEvent){
		boolean isActionSuccess = false;
		try {
			long codGrupo = grupoSeleccionado.getGrupoclienteCodGrupocliente();
			archivoDescargar = clienteServicesEJB.generarCredenciales(codGrupo);
			mensajesBean.msgInfo("Reporte Generado "+archivoDescargar);
			isActionSuccess = true;
		} catch (RegistrosNoEncontradosException | ErrorDelSistemaException e) {
			e.printStackTrace();
		}
		mensajesBean.devolverParametro("isActionSuccess", isActionSuccess);
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}
	
	
	public String getArchivoDescargar() {
		return archivoDescargar;
	}

	public void setArchivoDescargar(String archivoDescargar) {
		this.archivoDescargar = archivoDescargar;
	}

	public VoGrupocliente getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(VoGrupocliente grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}
}
