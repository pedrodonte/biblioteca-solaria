package cl.jsoft.solaria.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.daos.SolaTabPrestamoDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.TransformadorDominio;
import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.entities.SolaTabPrestamo;
import cl.jsoft.solaria.excepciones.ClienteMorosoException;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.PrestamoNoValidoException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.util.HelperFechas;

@Stateless
public class PrestamoServicesEJBImpl implements PrestamoServicesEJB {

	Logger logger = Logger.getLogger(getClass());

	@EJB
	private SolaTabPrestamoDAO prestamoDAO;

	private HelperVoEntity helperVoEntity = new TransformadorDominio();
	private HelperFechas hFechas = new HelperFechas();

	@Override
	public List<VoPrestamo> buscarPrestamosPendientes(VoCliente voCliente) {

		List<SolaTabPrestamo> prestamosJPA = prestamoDAO
				.buscaAtrasados(voCliente.getClienteIdentificador());
		List<VoPrestamo> respuesta = new ArrayList<>();

		for (SolaTabPrestamo prestamo : prestamosJPA) {
			respuesta.add(helperVoEntity.toVO(prestamo));
		}
		return respuesta;
	}

	@Override
	public List<VoPrestamo> buscarTodosPrestamosHistoricos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoPrestamo> buscarTodosPrestamosPendientes()
			throws RegistrosNoEncontradosException, ErrorDelSistemaException {

		List<VoPrestamo> respuesta = new ArrayList<>();

		try {
			List<SolaTabPrestamo> prestamosJPA = prestamoDAO
					.filtraPorCodigoEstado(PRESTAMO_ATRASADO);
			
			if (prestamosJPA != null) {
				logger.debug(prestamosJPA.size()+" Prestamos Morosos");
				for (SolaTabPrestamo prestamo : prestamosJPA) {
					respuesta.add(helperVoEntity.toVO(prestamo));
				}
			}else{
				throw new RegistrosNoEncontradosException("No se encuentran registros morosos.");
			}

		} catch (Exception e) {
			throw new ErrorDelSistemaException("Problemas al obtener los registros morosos.");
		}

		return respuesta;
	}

	@Override
	public List<VoPrestamo> buscarTodosPrestamos()
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		List<VoPrestamo> respuesta = new ArrayList<>();

		try {
			List<SolaTabPrestamo> prestamosJPA = prestamoDAO.findAll();
			
			if (prestamosJPA != null) {
				for (SolaTabPrestamo prestamo : prestamosJPA) {
					respuesta.add(helperVoEntity.toVO(prestamo));
				}
			}else{
				throw new RegistrosNoEncontradosException("No se encuentran registros morosos.");
			}

		} catch (Exception e) {
			throw new ErrorDelSistemaException("Problemas al obtener los registros morosos.");
		}

		return respuesta;
	}

	@Override
	public VoPrestamo nuevoPrestamo(VoPrestamo voPrestamo)
			throws PrestamoNoValidoException, ErrorDelSistemaException {
		SolaTabPrestamo prestamo = null;
		try {
			if (validarPrestamo(voPrestamo)) {
				voPrestamo.setPrestamoFecInsert(HelperFechas.getInstancia()
						.obtenerTimeStampActual());
				voPrestamo.setPrestamoCodEstado(PRESTAMO_VIGENTE);
				logger.debug("Input " + voPrestamo);
				prestamo = prestamoDAO
						.save(helperVoEntity.toEntity(voPrestamo));
			}
		} catch (PrestamoNoValidoException noValidoException) {
			throw noValidoException;
		} catch (NullPointerException e) {
			throw new PrestamoNoValidoException("Hay valores nulos en "
					+ voPrestamo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorDelSistemaException("Ocurre un error inesperado");
		}
		logger.debug("Retornando " + prestamo);
		return helperVoEntity.toVO(prestamo);
	}

	@Override
	public boolean validarPrestamo(VoPrestamo voPrestamo)
			throws PrestamoNoValidoException {
		Date fechaInicio = voPrestamo.getPrestamoFecInicio();
		Date fechaFinal = voPrestamo.getPrestamoFecPlazoEntrega();

		boolean periodoValido = (fechaFinal.after(fechaInicio) || fechaFinal
				.equals(fechaInicio));

		boolean tieneLibro = voPrestamo.getVoLibro().getLibroCodLibro() > 0;
		boolean tieneCliente = voPrestamo.getVoCliente().getClienteCodCliente() > 0;

		boolean isPrestamoValido = false;
		String msgException = "Prestamo NO Valido: ";

		if (logger.isDebugEnabled()) {
			logger.debug("tieneLibro: " + tieneLibro);
			logger.debug("tieneCliente: " + tieneCliente);
			logger.debug("periodoValido: " + periodoValido);
		}

		// Setear el mensaje de
		if (!tieneCliente) {
			msgException += "Cliente no Valido";
		} else if (!tieneLibro) {
			msgException += "Libro no Valido";
		} else if (!periodoValido) {
			msgException += "Periodo no Valido ( "
					+ hFechas.fechaFormateadaGuion(fechaInicio)
					+ " debe ser menor ó igual que "
					+ hFechas.fechaFormateadaGuion(fechaFinal) + " )";
		}

		if (tieneLibro && tieneCliente && periodoValido) {
			isPrestamoValido = true;
		} else {
			throw new PrestamoNoValidoException(msgException);
		}

		return isPrestamoValido;
	}

	public void setPrestamoDAO(SolaTabPrestamoDAO prestamoDAO) {
		this.prestamoDAO = prestamoDAO;
	}

	@Override
	public void devolverPrestamo(VoPrestamo voPrestamo)
			throws PrestamoNoValidoException, ErrorDelSistemaException {

		try {
			voPrestamo.setPrestamoFecUpdate(HelperFechas.getInstancia()
					.obtenerTimeStampActual());
			prestamoDAO.update(helperVoEntity.toEntity(voPrestamo));
		} catch (Exception e) {
			throw new ErrorDelSistemaException(
					"Error al actualizar el estado del prestamo");
		}

	}

	@Override
	public boolean verificarMorosidad(VoCliente voCliente)
			throws ClienteMorosoException, ErrorDelSistemaException {

		List<VoPrestamo> prestamosAtrazados = null;

		try {
			prestamosAtrazados = this.buscarPrestamosPendientes(voCliente);
			logger.debug("Prestamos pendientes" + prestamosAtrazados.size());
		} catch (Exception e) {
			throw new ErrorDelSistemaException(
					"Problemas al buscar los prestamos pendientes del usuario "
							+ voCliente);
		}

		if (prestamosAtrazados.size() > 0) {

			String exeMsg = prestamosAtrazados.size()
					+ " prestamos pendientes: <br/>";
			for (VoPrestamo p : prestamosAtrazados) {
				String fechaInicio = HelperFechas.getInstancia()
						.fechaFormateadaGuion(p.getPrestamoFecInicio());
				String fechaFin = HelperFechas.getInstancia()
						.fechaFormateadaGuion(p.getPrestamoFecPlazoEntrega());
				exeMsg += p.getVoLibro().getLibroTitulo() + " ( " + fechaInicio
						+ " al " + fechaFin + " ) <br/>";
			}
			throw new ClienteMorosoException(exeMsg);
		}

		return true;
	}

}
