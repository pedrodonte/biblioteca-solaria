package cl.jsoft.solaria.web.backend.prestamos;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;

//@FacesConverter("clienteRunConverter")
@ManagedBean
@RequestScoped
public class ClienteRUNConverter implements Converter {

	private List<VoCliente> clientes;
	
	Logger logger = Logger.getLogger(getClass());
	@EJB ClienteServicesEJB clienteServicesEJB;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String textoEnviado) {
		
		if (textoEnviado.trim().equals("")) {  
            return null;  
        }else{
			try {
				
				long codCliente = obtenerCodigoCliente(textoEnviado);
				
				logger.debug("ClienteConverter.getAsObject " + textoEnviado);
				
				clientes = clienteServicesEJB.buscarTodosLosClientes();
				
				for (VoCliente cliente : clientes) {
					if (cliente.getClienteCodCliente() == codCliente) {
						return cliente;
					}
				}
			}catch(ArrayIndexOutOfBoundsException ioe){
				logger.debug(textoEnviado+" : no contiene @");
				return null;
			}catch (Exception e) {
				logger.warn("Problemas a convertir al objeto : "+textoEnviado);
				e.printStackTrace();
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid string"));  
			}
		}
		return null;
	}

	private long obtenerCodigoCliente(String textoEnviado) throws ArrayIndexOutOfBoundsException {
		//TODO gestionar las excepciones que se puedan generar aqui.
		String[] contenido = textoEnviado.split("@");
		long codigo = Long.parseLong(contenido[1]);
		return codigo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		try{
			if(object == null  || object.equals("")){
				return null;
			}else{
				logger.debug("ClienteConverter.getAsString "+object.toString());
				VoCliente cliente = (VoCliente) object;
				return cliente.getSelectOneMenu();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

}
