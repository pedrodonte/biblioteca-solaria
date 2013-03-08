import static org.junit.Assert.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cl.jsoft.solaria.jasper.GeneradorReporteEJB;
import cl.jsoft.solaria.jasper.GeneradorReporteEJBImpl;

public class GenerarReporte {
	private static Context ctx;
	private static EJBContainer contenedor;

	
	GeneradorReporteEJB servicio;
	
	@Test
	public void comprobarEJB(){
		assertNotNull(servicio);
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
			servicio = lookupBy(GeneradorReporteEJB.class,
					GeneradorReporteEJBImpl.class);
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
