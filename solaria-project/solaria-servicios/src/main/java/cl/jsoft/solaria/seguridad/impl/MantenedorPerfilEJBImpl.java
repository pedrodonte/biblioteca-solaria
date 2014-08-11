package cl.jsoft.solaria.seguridad.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.segu.daos.PerfilDAO;
import cl.jsoft.solaria.segu.daos.SeguTabRelUsuaPerfilDAO;
import cl.jsoft.solaria.segu.entities.SeguTabPerfil;
import cl.jsoft.solaria.segu.entities.SeguTabRelUsuaPerfil;
import cl.jsoft.solaria.seguridad.api.MantenedorPerfilEJB;
import cl.jsoft.solaria.seguridad.vos.HelperVoEntity;
import cl.jsoft.solaria.seguridad.vos.VoPerfil;
import cl.jsoft.solaria.seguridad.vos.VoUsua;

@Stateless
public class MantenedorPerfilEJBImpl implements MantenedorPerfilEJB {
	
	@EJB PerfilDAO perfilDAO;
	@EJB SeguTabRelUsuaPerfilDAO relUsuaPerfilDAO;
	
	HelperVoEntity helperVoEntity = new HelperVoEntity();
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoPerfil nuevoRegistro(VoPerfil registro) {
		return registro;

	}

	@Override
	public VoPerfil actualizarRegistro(VoPerfil registro) {
		return registro;
	}

	@Override
	public void eliminarRegistro(VoPerfil registro) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<VoPerfil> obtenerRegistros() {
		List<VoPerfil> perfiles = new ArrayList<>();
		
		List<SeguTabPerfil> registros = perfilDAO.findAll();
		
		for (SeguTabPerfil seguTabPerfil : registros) {
			perfiles.add(helperVoEntity.toVO(seguTabPerfil));
		}
		
//		logger.info(perfiles.size()+" perfiles");
		return perfiles;
	}

	@Override
	public List<VoPerfil> obtenerPerfilesPorUsuario(VoUsua usuario) {
		List<VoPerfil> perfiles = new ArrayList<>();
		
		List<SeguTabRelUsuaPerfil> relUsuaPerfil = relUsuaPerfilDAO.buscaPerfilesPorUsuario(usuario.getUsuaCodUsua());
		for (SeguTabRelUsuaPerfil rel : relUsuaPerfil) {
			perfiles.add(helperVoEntity.toVO(rel.getSeguTabPerfil()));
		}
		
		return perfiles;
	}

}
