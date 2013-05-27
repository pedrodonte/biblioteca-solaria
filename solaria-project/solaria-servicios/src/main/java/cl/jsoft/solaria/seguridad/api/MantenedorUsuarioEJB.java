package cl.jsoft.solaria.seguridad.api;

import java.util.List;

import javax.ejb.Local;

import cl.jsoft.solaria.seguridad.vos.VoPerfil;
import cl.jsoft.solaria.seguridad.vos.VoUsua;
import cl.jsoft.solaria.servicios.CrudGenericServiceApi;

@Local
public interface MantenedorUsuarioEJB extends CrudGenericServiceApi<VoUsua>{
	
	public void asociarPerfiles(VoUsua voUsua, List<VoPerfil> perfiles);
	public void desasociarPerfiles(VoUsua voUsua, List<VoPerfil> perfiles);
	
}
