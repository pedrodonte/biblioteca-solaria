

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

import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;

//@FacesConverter(value="grupoClienteConverter")
@ManagedBean
@RequestScoped
public class GrupoClienteConverter implements Converter {

	private List<VoGrupocliente> gruposCliente;
	
	Logger logger = Logger.getLogger(getClass());
	@EJB ClienteServicesEJB clienteServicesEJB;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String textoEnviado) {
		
		if (textoEnviado.trim().equals("")) {  
            return null;  
        }else{
			try {
				
				gruposCliente = clienteServicesEJB.buscarTodosGruposCliente();
				
				logger.debug("toObject recibiendo: " + textoEnviado);
				for (VoGrupocliente grupo : gruposCliente) {
					if (grupo.getGrupoclienteNombre().equals(textoEnviado) ) {
						
						logger.debug("toObject retornando: " + grupo);
						return grupo;
					}
				}
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
				logger.debug("toString Recibiendo: "+object);
				VoGrupocliente grupo = (VoGrupocliente) object;
				logger.debug("toString Retornando: "+grupo.getGrupoclienteNombre());
				return grupo.getGrupoclienteNombre();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

}
