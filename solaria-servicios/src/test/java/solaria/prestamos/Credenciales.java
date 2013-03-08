package solaria.prestamos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


import cl.jsoft.solaria.daos.SolaTabPrestamoDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.entities.SolaTabPrestamo;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.PrestamoNoValidoException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.jasper.GeneradorReporteEJB;
import cl.jsoft.solaria.jasper.GeneradorReporteEJBImpl;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;
import cl.jsoft.solaria.servicios.ClienteServicesEJBImpl;
import cl.jsoft.solaria.servicios.PrestamoServicesEJBImpl;
import cl.jsoft.solaria.util.HelperFechas;

public class Credenciales {
	
	private static Context ctx;
	private static EJBContainer contenedor;

	ClienteServicesEJB servicio;
	
	@Test
	public void comprobarEJB(){
		assertNotNull(servicio);
		for (int i = 1; i <= 24; i++) {
			try {
				servicio.generarCredenciales((long)i);
			} catch (RegistrosNoEncontradosException | ErrorDelSistemaException e) {
				e.printStackTrace();
			}
		}
	}
	

	

	/* *********************************************** *
	 * METODOS DE CONFIGURACION DE LA CLASE DE PRUEBAS *
	 * ***********************************************
	 */

	@BeforeClass
	public static void setUpClass() throws Exception {
		System.out.println(">>> CARGANDO CLASE DE PRUEBAS <<<");
		contenedor = EJBContainer.createEJBContainer();
		ctx = contenedor.getContext();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		System.out.println("<<< CERRANDO CLASE DE PRUEBAS >>>");
		contenedor.close();
	}

	@Before
	public void setUp() throws Exception {
		try {
			servicio = lookupBy(ClienteServicesEJB.class,
					ClienteServicesEJBImpl.class);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T> T lookupBy(Class<T> interfaceType, Class classImpl)
			throws NamingException {
		String ejbLookUpString = "java:global/ejb-app/classesejb/"
				+ classImpl.getSimpleName() + "!" + interfaceType.getName();
		System.out.println("\n\n");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("BuscandoEJB[ " + ejbLookUpString + " ]");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		return (T) ctx.lookup(ejbLookUpString);
	}

	

}
