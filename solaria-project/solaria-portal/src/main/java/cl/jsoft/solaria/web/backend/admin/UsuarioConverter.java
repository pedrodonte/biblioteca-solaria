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

import cl.jsoft.solaria.seguridad.api.MantenedorUsuarioEJB;
import cl.jsoft.solaria.seguridad.vos.VoUsua;

//@FacesConverter("clienteConverter")
@ManagedBean
@RequestScoped
public class UsuarioConverter implements Converter {

	private List<VoUsua> registros;
	
	Logger logger = Logger.getLogger(getClass());
	@EJB MantenedorUsuarioEJB mantenedorUsuarioEJB;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String textoEnviado) {
		
		if (textoEnviado.trim().equals("")) {  
            return null;  
        }else{
			try {
				
				//logger.info("toObject recibiendo: " + textoEnviado);
				
				registros = mantenedorUsuarioEJB.obtenerRegistros();
				for (VoUsua item : registros) {
					if (item.getUsuaEmail().equals(textoEnviado) ) {
						//logger.info("toObject retornando: "+cliente);
						return item;
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
				VoUsua item = (VoUsua) object;
				//logger.info("toString Retornando: "+cliente.getPerfilNombre());
				return item.getUsuaEmail();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

}
