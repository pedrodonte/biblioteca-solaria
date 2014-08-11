package cl.jsoft.solaria.web.backend.admin;

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

import cl.jsoft.solaria.seguridad.vos.VoPerfil;
import cl.jsoft.solaria.seguridad.api.MantenedorPerfilEJB;

//@FacesConverter("clienteConverter")
@ManagedBean
@RequestScoped
public class PerfilConverter implements Converter {

	private List<VoPerfil> clientes;
	
	Logger logger = Logger.getLogger(getClass());
	@EJB MantenedorPerfilEJB perfilEJB;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String textoEnviado) {
		
		if (textoEnviado.trim().equals("")) {  
            return null;  
        }else{
			try {
				
				//logger.info("toObject recibiendo: " + textoEnviado);
				
				clientes = perfilEJB.obtenerRegistros();
				for (VoPerfil cliente : clientes) {
					if (cliente.getPerfilNombre().equals(textoEnviado) ) {
						//logger.info("toObject retornando: "+cliente);
						return cliente;
					}
				}
			}catch(ArrayIndexOutOfBoundsException ioe){
				//logger.info(textoEnviado+" : no contiene @");
				return null;
			}catch (Exception e) {
				logger.warn("Problemas a convertir al objeto : "+textoEnviado);
				e.printStackTrace();
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid string"));  
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		try{
			if(object == null  || object.equals("")){
				return null;
			}else{
				//logger.info("toString Recibiendo: "+object);
				VoPerfil cliente = (VoPerfil) object;
				//logger.info("toString Retornando: "+cliente.getPerfilNombre());
				return cliente.getPerfilNombre();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

}
