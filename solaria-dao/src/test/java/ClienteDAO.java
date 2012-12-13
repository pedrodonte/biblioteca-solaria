import java.math.BigDecimal;
import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cl.jsoft.solaria.daos.SolaTabClienteDAO;
import cl.jsoft.solaria.entities.SolaTabCliente;

public class ClienteDAO {
	
	SolaTabClienteDAO servicio;
	
	@Test
	public void buscarClientesRegistrados(){
		List<SolaTabCliente> clientes = servicio.findAll();
		for (SolaTabCliente cliente : clientes) {
			System.out.println(cliente);
		}
		System.out.println(clientes.size()+" Registros encontrados.");
	}
	
	@Test
	public void buscarCliente(){
		BigDecimal runCliente = new BigDecimal(16184826);
		SolaTabCliente cliente = servicio.buscaRegistroPorIdentificador(runCliente);
		System.out.println(cliente);
	}
	
	@Test
	public void buscarClientesPorNombresApellidos(){
		
		List<SolaTabCliente> items = servicio.buscaRegistroPorNombreApellido("pedro", "");
		for(SolaTabCliente solaTabCliente : items){
			System.out.println(solaTabCliente.getClienteNombres()+" "+solaTabCliente.getClienteApellidos());
		}
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
			servicio = lookupBy(SolaTabClienteDAO.class);
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
}
