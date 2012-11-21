package cl.jsoft.solaria.servicios;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.daos.SolaTabPrestamoDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.entities.SolaTabPrestamo;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.PrestamoNoValidoException;
import cl.jsoft.solaria.util.HelperFechas;

@Stateless
public class PrestamoServicesEJBImpl implements PrestamoServicesEJB{
	
	Logger logger = Logger.getLogger(getClass());
	
	@EJB private SolaTabPrestamoDAO prestamoDAO;
	
	private HelperVoEntity helperVoEntity = new HelperVoEntity();
	private HelperFechas hFechas = new HelperFechas();
	
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
				voPrestamo.setPrestamoFecInsert(getFechaActual());
				voPrestamo.setPrestamoCodEstado(PRESTAMO_VIGENTE);
				logger.debug("Input "+voPrestamo);
				prestamo = prestamoDAO.save(helperVoEntity.toEntity(voPrestamo));
			}
		}catch (PrestamoNoValidoException noValidoException){
			throw noValidoException;
		}catch (NullPointerException e) {
			throw new PrestamoNoValidoException("Hay valores nulos en "+voPrestamo);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ErrorDelSistemaException("Ocurre un error inesperado");
		}
		logger.debug("Retornando "+prestamo);
		return helperVoEntity.toVO(prestamo);
	}

	private Timestamp getFechaActual() {
		Date dateActual = new Date();
		return new Timestamp(dateActual.getTime());
	}

	@Override
	public boolean validarPrestamo(VoPrestamo voPrestamo) throws PrestamoNoValidoException {
		Date fechaInicio = voPrestamo.getPrestamoFecInicio();
		Date fechaFinal = voPrestamo.getPrestamoFecPlazoEntrega();
		
		boolean periodoValido = ( fechaFinal.after(fechaInicio) || fechaFinal.equals(fechaInicio) );
		
		boolean tieneLibro = voPrestamo.getVoLibro().getLibroCodLibro() > 0 ;
		boolean tieneCliente = voPrestamo.getVoCliente().getClienteCodCliente() > 0;
		
		boolean isPrestamoValido = false;
		String msgException = "Prestamo NO Valido: ";
		
		if (logger.isDebugEnabled()) {
			logger.debug("tieneLibro: "+tieneLibro);
			logger.debug("tieneCliente: "+tieneCliente);
			logger.debug("periodoValido: "+periodoValido);	
		}
		
		//Setear el mensaje de 
		if (!tieneCliente) {
			msgException += "Cliente no Valido";
		}else if(!tieneLibro){
			msgException += "Libro no Valido";
		}else if(!periodoValido){
			msgException += "Periodo no Valido ( "+hFechas.fechaFormateadaGuion(fechaInicio) +" debe ser menor ó igual que "+hFechas.fechaFormateadaGuion(fechaFinal)+" )";
		}
		
		if(tieneLibro && tieneCliente && periodoValido){
			isPrestamoValido = true;
		}else{
			throw new PrestamoNoValidoException(msgException);
		}
		
		return isPrestamoValido;
	}

	public void setPrestamoDAO(SolaTabPrestamoDAO prestamoDAO) {
		this.prestamoDAO = prestamoDAO;
	}
	
	

}
