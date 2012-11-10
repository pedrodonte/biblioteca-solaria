package cl.jsoft.solaria.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cl.jsoft.solaria.daos.SolaTabPrestamoDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.entities.SolaTabPrestamo;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.PrestamoNoValidoException;

@Stateless
public class PrestamoServicesEJBImpl implements PrestamoServicesEJB{
	
	@EJB private SolaTabPrestamoDAO prestamoDAO;
	
	private HelperVoEntity helperVoEntity = new HelperVoEntity();
	
	@Override
	public List<VoPrestamo> buscarPrestamosHistoricos(VoCliente voCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoPrestamo> buscarPrestamosPendientes(VoCliente voCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoPrestamo> buscarTodosPrestamosHistoricos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoPrestamo> buscarTodosPrestamosPendientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoPrestamo nuevoPrestamo(VoPrestamo voPrestamo) throws PrestamoNoValidoException, ErrorDelSistemaException {
		SolaTabPrestamo prestamo = null;
		try {
			if(validarPrestamo(voPrestamo)){
				prestamo = prestamoDAO.save(helperVoEntity.toEntity(voPrestamo));
			}else{
				throw new PrestamoNoValidoException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorDelSistemaException();
		}
		return helperVoEntity.toVO(prestamo);
	}

	@Override
	public boolean validarPrestamo(VoPrestamo voPrestamo) {
		// TODO Auto-generated method stub
		return false;
	}


}
