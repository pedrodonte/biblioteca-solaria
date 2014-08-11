package cl.jsoft.solaria.seguridad.api;

import java.util.List;

import javax.ejb.Local;

import cl.jsoft.solaria.seguridad.vos.VoPerfil;
import cl.jsoft.solaria.seguridad.vos.VoUsua;
import cl.jsoft.solaria.servicios.CrudGenericServiceApi;

@Local
public interface MantenedorPerfilEJB extends CrudGenericServiceApi<VoPerfil> {
	
	public List<VoPerfil> obtenerPerfilesPorUsuario(VoUsua usuario);

}
