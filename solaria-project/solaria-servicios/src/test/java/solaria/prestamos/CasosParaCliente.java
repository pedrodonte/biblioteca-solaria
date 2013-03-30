package solaria.prestamos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


import cl.jsoft.solaria.daos.SolaTabPrestamoDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.entities.SolaTabPrestamo;
import cl.jsoft.solaria.excepciones.PrestamoNoValidoException;
import cl.jsoft.solaria.servicios.PrestamoServicesEJBImpl;
import cl.jsoft.solaria.util.HelperFechas;

public class CasosParaCliente {
	
	Logger logger = Logger.getLogger("Testing");
		
	HelperFechas fechas = new HelperFechas();
	HelperVoEntity helperVoEntity = new HelperVoEntity();
		
	PrestamoServicesEJBImpl servicio = new PrestamoServicesEJBImpl();

	SolaTabPrestamoDAO prestamoDAO = mock(SolaTabPrestamoDAO.class);
	SolaTabPrestamo daoResp;
	
	@Before
	public void setUp() throws Exception {
		daoResp = DataTest.getSTPrestamoOK();
		//when(prestamoDAO.save(helperVoEntity.toEntity(DataTest.getPrestamoValido()))).thenReturn(daoResp);
		when( prestamoDAO.save( (SolaTabPrestamo) anyObject() ) ).thenReturn(daoResp);
		servicio.setPrestamoDAO(prestamoDAO);
	}

	@Test
	public void nuevoPrestamoValidoOK() {
		try {
			
			ArgumentCaptor<SolaTabPrestamo> argumentCaptor = ArgumentCaptor.forClass(SolaTabPrestamo.class);
			
			VoPrestamo prestamo = DataTest.getPrestamoValido();
			
			logger.info(">>>> "+prestamo);
			prestamo = servicio.nuevoPrestamo(prestamo);
			verify(prestamoDAO).save(argumentCaptor.capture());
			logger.info("<<<< "+prestamo);
			assertTrue(prestamo.getPrestamoCodPrestamo() > 0);
		} catch (Exception e) {
			
			e.printStackTrace();
			assertTrue(false);
		}
		logger.info(" \n - \n ");
	}
	
	@Test
	public void nuevoPrestamoFechasIgualOK() {
		try {
			
			VoPrestamo prestamo = DataTest.getPrestamoFechasIgual();
			
			logger.info(">>>> "+prestamo);
			prestamo = servicio.nuevoPrestamo(prestamo);
			verify(prestamoDAO).save(any(SolaTabPrestamo.class));
			logger.info("<<<< "+prestamo);
			
			assertTrue(prestamo.getPrestamoCodPrestamo() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		logger.info(" \n - \n ");
	}
	
	@Test
	public void nuevoPrestamoFecIniMayorQueFecFinalFAIL() {
		try {
			
			VoPrestamo prestamo = DataTest.getPrestamoFechasIniDespuesQueFinal();
			
			logger.info(">>>> "+prestamo);
			prestamo = servicio.nuevoPrestamo(prestamo);
			logger.info("<<<< "+prestamo);
			
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			assertTrue(e instanceof PrestamoNoValidoException );
		}
		logger.info(" \n - \n ");
	}
	
	@Test
	public void nuevoPrestamoClienteNuloFAIL() {
		try {
			
			VoPrestamo prestamo = DataTest.getPrestamoClienteNull();
			
			logger.info(">>>> "+prestamo);
			prestamo = servicio.nuevoPrestamo(prestamo);
			logger.info("<<<< "+prestamo);
			
			assertTrue(prestamo.getPrestamoCodPrestamo() > 0);
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			assertTrue(e instanceof PrestamoNoValidoException );
		}
		logger.info(" \n - \n ");
	}
	
	@Test
	public void nuevoPrestamoClienteVacioFAIL() {
		try {
			
			VoPrestamo prestamo = DataTest.getPrestamoClienteVacio();
			
			logger.info(">>>> "+prestamo);
			prestamo = servicio.nuevoPrestamo(prestamo);
			
			logger.info("<<<< "+prestamo);
			
			assertTrue(prestamo.getPrestamoCodPrestamo() > 0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertTrue(e instanceof PrestamoNoValidoException );
		}
		logger.info(" \n - \n ");
	}
	
	@Test
	public void nuevoPrestamoLibroNuloFAIL() {
		try {
			
			VoPrestamo prestamo = DataTest.getPrestamoLibroNull();
			
			logger.info(">>>> "+prestamo);
			prestamo = servicio.nuevoPrestamo(prestamo);
			
			logger.info("<<<< "+prestamo);
			
			assertTrue(prestamo.getPrestamoCodPrestamo() > 0);
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			assertTrue(e instanceof PrestamoNoValidoException );
		}
		logger.info(" \n - \n ");
	}
	
	@Test
	public void nuevoPrestamoLibroVacioFAIL() {
		try {
			
			VoPrestamo prestamo = DataTest.getPrestamoLibroVacio();
			
			logger.info(">>>> "+prestamo);
			prestamo = servicio.nuevoPrestamo(prestamo);
			
			logger.info("<<<< "+prestamo);
			
			assertTrue(prestamo.getPrestamoCodPrestamo() > 0);
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			assertTrue(e instanceof PrestamoNoValidoException );
		}
		logger.info(" \n - \n ");
	}


}
