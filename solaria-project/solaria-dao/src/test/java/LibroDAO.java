import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cl.jsoft.solaria.daos.SolaTabLibroDAO;
import cl.jsoft.solaria.entities.SolaTabLibro;

public class LibroDAO {
	
	SolaTabLibroDAO servicio;
	
	
	@Test
	public void buscarlibrosporCodigoInterno(){
		System.out.println("Buscando los libro");
		String codigoInterno="778";
		List<SolaTabLibro> libros = servicio.buscaRegistrosPorIdInterno(codigoInterno);
		System.out.println(libros.size()+" Registros encontrados.");
		for (SolaTabLibro libro : libros) {
			System.out.println(libro);
		}
		
	}
	
	@Test
	public void buscarlibroporCodigoInterno(){
		System.out.println("Buscando un libro");
		String codigoInterno="000778";
		SolaTabLibro libro = servicio.buscaRegistroPorIdInterno(codigoInterno);
		System.out.println(libro);
	}

	/* *********************************************** *
	 * METODOS DE CONFIGURACION DE LA CLASE DE PRUEBAS *
	 * *********************************************** */
	
	private static Context ctx;
	private static EJBContainer contenedor;

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
			servicio = lookupBy(SolaTabLibroDAO.class);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked"})
	protected <T> T lookupBy(Class<T> interfaceType)
			throws NamingException {
		String ejbLookUpString = "java:global/classes/"+ interfaceType.getSimpleName();
		System.out.println("BuscandoEJB "+ ejbLookUpString);
		return (T) ctx.lookup(ejbLookUpString);
	}
	
//	private final String FORMATO_NRO_REGISTRO = "000000";
//	public void arreglarFormatoIDInterno(){
//		List<SolaTabLibro> libros = servicio.findAll();
//		for (SolaTabLibro libro : libros) {
//			NumberFormat nf = new DecimalFormat(FORMATO_NRO_REGISTRO);
//			libro.setLibroIdInterno(nf.format(Integer.parseInt(libro.getLibroIdInterno())));
//			servicio.update(libro);
//		}
//		System.out.println(libros.size()+" Registros encontrados.");
//	}
}
