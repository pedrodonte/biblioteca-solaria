package solaria.prestamos;

import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoGenerolibro;
import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.entities.SolaTabPrestamo;
import cl.jsoft.solaria.servicios.PrestamoServicesEJB;
import cl.jsoft.solaria.util.HelperFechas;

public class DataTest {

	static VoPrestamo presValido = new VoPrestamo();
	static VoCliente clieValido = new VoCliente();
	static VoLibro librValido = new VoLibro();

	static SolaTabPrestamo solaTabPrestamo = new SolaTabPrestamo();

	static HelperFechas fechas = new HelperFechas();
	static HelperVoEntity helperVoEntity = new HelperVoEntity();

	public static SolaTabPrestamo getSTPrestamoOK() {
		presValido.setPrestamoFecInsert(fechas.obtenerTimeStampActual());
		presValido.setPrestamoCodEstado(PrestamoServicesEJB.PRESTAMO_VIGENTE);
		solaTabPrestamo = helperVoEntity.toEntity(getPrestamoValido());
		solaTabPrestamo.setPrestamoCodPrestamo(9999);
		return solaTabPrestamo;
	}

	public static VoLibro getLibroValido() {
		librValido.setLibroCodLibro(555);
		librValido.setLibroTitulo("Los Robots del Amanecer");
		librValido.setLibroNombreAutor("Isaac Asimov");
		librValido.setVoGenerolibro(getGeneroLibroValido());
		return librValido;
	}

	public static VoGenerolibro getGeneroLibroValido() {
		VoGenerolibro voGenerolibro = new VoGenerolibro();
		voGenerolibro.setGenerolibroCodGenerolibro(111);
		voGenerolibro.setGenerolibroNombre("Ciencia Ficción");
		return voGenerolibro;
	}

	public static VoCliente getClienteValido() {
		clieValido.setClienteCodCliente(777);
		clieValido.setClienteNombres("Pedro");
		clieValido.setClienteApellidos("Carrasco");
		clieValido.setVoGrupocliente(getGrupoClienteValido());
		return clieValido;
	}

	public static VoGrupocliente getGrupoClienteValido() {
		VoGrupocliente voGrupocliente = new VoGrupocliente();
		voGrupocliente.setGrupoclienteCodGrupocliente(123);
		voGrupocliente.setGrupoclienteNombre("Grupo Testing");
		return voGrupocliente;
	}

	public static VoPrestamo getPrestamoValido() {
		presValido.setPrestamoFecInicio(fechas.obtenerDateActual());
		presValido.setPrestamoFecPlazoEntrega(fechas.fechaFutura(3));
		presValido.setVoCliente(getClienteValido());
		presValido.setVoLibro(getLibroValido());
		presValido.setPrestamoFecInsert(fechas.obtenerTimeStampActual());
		presValido.setPrestamoCodEstado(PrestamoServicesEJB.PRESTAMO_VIGENTE);
		return presValido;
	}

	public static VoPrestamo getPrestamoFechasIgual() {
		presValido.setPrestamoFecInicio(fechas.obtenerDateActual());
		presValido.setPrestamoFecPlazoEntrega(fechas.obtenerDateActual());
		presValido.setVoCliente(getClienteValido());
		presValido.setVoLibro(getLibroValido());
		presValido.setPrestamoFecInsert(fechas.obtenerTimeStampActual());
		presValido.setPrestamoCodEstado(PrestamoServicesEJB.PRESTAMO_VIGENTE);
		return presValido;
	}
	
	public static VoPrestamo getPrestamoFechasIniDespuesQueFinal() {
		presValido.setPrestamoFecInicio(fechas.fechaFutura(5));
		presValido.setPrestamoFecPlazoEntrega(fechas.obtenerDateActual());
		presValido.setVoCliente(getClienteValido());
		presValido.setVoLibro(getLibroValido());
		presValido.setPrestamoFecInsert(fechas.obtenerTimeStampActual());
		presValido.setPrestamoCodEstado(PrestamoServicesEJB.PRESTAMO_VIGENTE);
		return presValido;
	}
	
	public static VoPrestamo getPrestamoClienteNull() {
		presValido = getPrestamoValido();
		presValido.setVoCliente(null);
		return presValido;
	}
	
	public static VoPrestamo getPrestamoClienteVacio() {
		presValido = getPrestamoValido();
		presValido.setVoCliente(new VoCliente());
		return presValido;
	}
	
	public static VoPrestamo getPrestamoLibroNull() {
		presValido = getPrestamoValido();
		presValido.setVoLibro(null);
		return presValido;
	}
	
	public static VoPrestamo getPrestamoLibroVacio() {
		presValido = getPrestamoValido();
		presValido.setVoLibro(new VoLibro());
		return presValido;
	}

}
