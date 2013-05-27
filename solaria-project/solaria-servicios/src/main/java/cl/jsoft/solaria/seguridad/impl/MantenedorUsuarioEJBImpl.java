package cl.jsoft.solaria.seguridad.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.segu.daos.PerfilDAO;
import cl.jsoft.solaria.segu.daos.SeguTabRelUsuaPerfilDAO;
import cl.jsoft.solaria.segu.daos.UsuarioDAO;
import cl.jsoft.solaria.segu.entities.SeguTabRelUsuaPerfil;
import cl.jsoft.solaria.segu.entities.SeguTabUsua;
import cl.jsoft.solaria.seguridad.api.MantenedorPerfilEJB;
import cl.jsoft.solaria.seguridad.api.MantenedorUsuarioEJB;
import cl.jsoft.solaria.seguridad.vos.HelperVoEntity;
import cl.jsoft.solaria.seguridad.vos.VoPerfil;
import cl.jsoft.solaria.seguridad.vos.VoUsua;
import cl.jsoft.solaria.util.HelperFechas;

@Stateless
public class MantenedorUsuarioEJBImpl implements MantenedorUsuarioEJB {

	@EJB
	PerfilDAO perfilDAO;
	@EJB
	UsuarioDAO usuarioDAO;
	@EJB
	SeguTabRelUsuaPerfilDAO relUsuaPerfilDAO;
	@EJB
	MantenedorPerfilEJB mantenedorPerfilEJB;
	HelperVoEntity helperVoEntity = new HelperVoEntity();
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoUsua nuevoRegistro(VoUsua registro) throws ErrorDelSistemaException {
		SeguTabUsua entity = usuarioDAO.save(helperVoEntity.toEntity(registro));
		return helperVoEntity.toVO(entity);
	}

	@Override
	public VoUsua actualizarRegistro(VoUsua registro) throws ErrorDelSistemaException {
		SeguTabUsua entity = usuarioDAO.update(helperVoEntity.toEntity(registro));
		return helperVoEntity.toVO(entity);
	}

	@Override
	public void eliminarRegistro(VoUsua registro) throws ErrorDelSistemaException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<VoUsua> obtenerRegistros() throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		List<VoUsua> usuarios = new ArrayList<>();

		List<SeguTabUsua> registros = usuarioDAO.findAll();

		for (SeguTabUsua tabUsua : registros) {
			usuarios.add(helperVoEntity.toVO(tabUsua));
		}

		return usuarios;
	}

	@Override
	public void asociarPerfiles(VoUsua usuario,
			List<VoPerfil> perfilesPropuestos) {

		Timestamp tiempoActual = HelperFechas.getInstancia()
				.obtenerTimeStampActual();

		List<SeguTabRelUsuaPerfil> asociacionesActuales = relUsuaPerfilDAO
				.buscaPerfilesPorUsuario(usuario.getUsuaCodUsua());
		
		for (SeguTabRelUsuaPerfil rel : asociacionesActuales) {
			rel.setRegFechaUpdate(tiempoActual);
			rel.setRelVigente(false);
			relUsuaPerfilDAO.update(rel);
		}
		
		for (VoPerfil perfil : perfilesPropuestos) {
			SeguTabRelUsuaPerfil rel = new SeguTabRelUsuaPerfil();
			rel.setRelVigente(true);
			rel.setRegFechaInsert(tiempoActual);
			rel.setSeguTabUsua(helperVoEntity.toEntity(usuario));
			rel.setSeguTabPerfil(helperVoEntity.toEntity(perfil));
			relUsuaPerfilDAO.save(rel);
		}

	}

	@Override
	public void desasociarPerfiles(VoUsua voUsua, List<VoPerfil> perfiles) {
		// TODO Auto-generated method stub

	}

}
