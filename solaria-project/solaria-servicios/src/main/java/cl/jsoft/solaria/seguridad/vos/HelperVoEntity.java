package cl.jsoft.solaria.seguridad.vos;

/* CLASE - AUTOGENERADA

* FECHA CREACION: Sun May 26 14:53:30 CLT 2013 */

/* En caso de cambiar el modelo de datos, esta clase debe ser modificada o volver a generar. */

import cl.jsoft.solaria.segu.entities.SeguTabPerfil;
import cl.jsoft.solaria.segu.entities.SeguTabRelUsuaPerfil;
import cl.jsoft.solaria.segu.entities.SeguTabUsua;

public class HelperVoEntity{


	/** Transforma un objeto tipo SeguTabPerfil en uno nuevo de tipo VoPerfil */
	public VoPerfil toVO(SeguTabPerfil seguTabPerfil){
		VoPerfil voPerfil = new VoPerfil();
			voPerfil.setPerfilCodPerfil(seguTabPerfil.getPerfilCodPerfil());
			voPerfil.setPerfilBooCodVigente(seguTabPerfil.getPerfilBooCodVigente());
			voPerfil.setPerfilDescripcion(seguTabPerfil.getPerfilDescripcion());
			voPerfil.setPerfilIdentificadorJaas(seguTabPerfil.getPerfilIdentificadorJaas());
			voPerfil.setPerfilNombre(seguTabPerfil.getPerfilNombre());
		return voPerfil;
	}

	/** Transforma un objeto tipo VoPerfil en uno nuevo de tipo SeguTabPerfil */
	public SeguTabPerfil toEntity(VoPerfil voPerfil){
		SeguTabPerfil seguTabPerfil = new SeguTabPerfil();
		seguTabPerfil.setPerfilCodPerfil(voPerfil.getPerfilCodPerfil());
		seguTabPerfil.setPerfilBooCodVigente(voPerfil.getPerfilBooCodVigente());
		seguTabPerfil.setPerfilDescripcion(voPerfil.getPerfilDescripcion());
		seguTabPerfil.setPerfilIdentificadorJaas(voPerfil.getPerfilIdentificadorJaas());
		seguTabPerfil.setPerfilNombre(voPerfil.getPerfilNombre());
		return seguTabPerfil;
	}

	/** Transforma un objeto tipo SeguTabRelUsuaPerfil en uno nuevo de tipo VoRelUsuaPerfil */
	public VoRelUsuaPerfil toVO(SeguTabRelUsuaPerfil seguTabRelUsuaPerfil){
		VoRelUsuaPerfil voRelUsuaPerfil = new VoRelUsuaPerfil();
			voRelUsuaPerfil.setRelCodRel(seguTabRelUsuaPerfil.getRelCodRel());
			voRelUsuaPerfil.setRegFechaInsert(seguTabRelUsuaPerfil.getRegFechaInsert());
			voRelUsuaPerfil.setRegFechaUpdate(seguTabRelUsuaPerfil.getRegFechaUpdate());
			voRelUsuaPerfil.setRelVigente(seguTabRelUsuaPerfil.getRelVigente());
			voRelUsuaPerfil.setVoPerfil( toVO(seguTabRelUsuaPerfil.getSeguTabPerfil()) );
			voRelUsuaPerfil.setVoUsua( toVO(seguTabRelUsuaPerfil.getSeguTabUsua()) );
		return voRelUsuaPerfil;
	}

	/** Transforma un objeto tipo VoRelUsuaPerfil en uno nuevo de tipo SeguTabRelUsuaPerfil */
	public SeguTabRelUsuaPerfil toEntity(VoRelUsuaPerfil voRelUsuaPerfil){
		SeguTabRelUsuaPerfil seguTabRelUsuaPerfil = new SeguTabRelUsuaPerfil();
		seguTabRelUsuaPerfil.setRelCodRel(voRelUsuaPerfil.getRelCodRel());
		seguTabRelUsuaPerfil.setRegFechaInsert(voRelUsuaPerfil.getRegFechaInsert());
		seguTabRelUsuaPerfil.setRegFechaUpdate(voRelUsuaPerfil.getRegFechaUpdate());
		seguTabRelUsuaPerfil.setRelVigente(voRelUsuaPerfil.getRelVigente());
		seguTabRelUsuaPerfil.setSeguTabPerfil( toEntity(voRelUsuaPerfil.getVoPerfil()) );
		seguTabRelUsuaPerfil.setSeguTabUsua( toEntity(voRelUsuaPerfil.getVoUsua()) );
		return seguTabRelUsuaPerfil;
	}

	/** Transforma un objeto tipo SeguTabUsua en uno nuevo de tipo VoUsua */
	public VoUsua toVO(SeguTabUsua seguTabUsua){
		VoUsua voUsua = new VoUsua();
			voUsua.setUsuaCodUsua(seguTabUsua.getUsuaCodUsua());
			voUsua.setUsuaContrasena(seguTabUsua.getUsuaContrasena());
			voUsua.setUsuaEmail(seguTabUsua.getUsuaEmail());
			voUsua.setUsuaNombre(seguTabUsua.getUsuaNombre());
		return voUsua;
	}

	/** Transforma un objeto tipo VoUsua en uno nuevo de tipo SeguTabUsua */
	public SeguTabUsua toEntity(VoUsua voUsua){
		SeguTabUsua seguTabUsua = new SeguTabUsua();
		seguTabUsua.setUsuaCodUsua(voUsua.getUsuaCodUsua());
		seguTabUsua.setUsuaContrasena(voUsua.getUsuaContrasena());
		seguTabUsua.setUsuaEmail(voUsua.getUsuaEmail());
		seguTabUsua.setUsuaNombre(voUsua.getUsuaNombre());
		return seguTabUsua;
	}

}
