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
import cl.jsoft.solaria.dominio.vos.VoEstadisticasPrestamos;
import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.dominio.vos.VoPrestamo;
import cl.jsoft.solaria.entities.SolaTabPrestamo;
import cl.jsoft.solaria.excepciones.ClienteMorosoException;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.PrestamoNoValidoException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.api.FiltroPrestamos;
import cl.jsoft.solaria.util.HelperFechas;

@Stateless
public class PrestamoServicesEJBImpl implements PrestamoServicesEJB {

	Logger logger = Logger.getLogger(getClass());

	@EJB
	private SolaTabPrestamoDAO prestamoDAO;

	private HelperVoEntity helperVoEntity = new TransformadorDominio();
	private HelperFechas hFechas = new HelperFechas();


	

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
			voPrestamo.setPrestamoFecUpdate(hFechas.obtenerTimeStampActual());
			voPrestamo.setPrestamoFecDevReal(hFechas.obtenerTimeStampActual());
			voPrestamo.setPrestamoCodEstado(PRESTAMO_DEVUELTO);
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
			prestamosAtrazados = this.buscarPrestamosPorEstado(FiltroPrestamos.ATRASADOS, voCliente);
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
	
	@Override
	public List<VoPrestamo> buscarPrestamosPorEstado(
			FiltroPrestamos filtroPrestamos, VoCliente voCliente) throws ErrorDelSistemaException,
			RegistrosNoEncontradosException{
		List<VoPrestamo> vos = new ArrayList<>();
		List<SolaTabPrestamo> registros;
		
		switch (filtroPrestamos) {
		case ATRASADOS:
			registros = prestamoDAO.filtraPorCodigoEstado(PRESTAMO_ATRASADO, voCliente.getClienteCodCliente());
			break;
		case VIGENTES:
			registros = prestamoDAO.filtraPorCodigoEstado(PRESTAMO_VIGENTE, voCliente.getClienteCodCliente());
			break;
		case DEVUELTOS:
			registros = prestamoDAO.filtraPorCodigoEstado(PRESTAMO_DEVUELTO, voCliente.getClienteCodCliente());
			break;

		default:
			registros = new ArrayList<>();
			break;
		}
		
		for (SolaTabPrestamo p : registros) {
			VoPrestamo vo = helperVoEntity.toVO(p);
			if (filtroPrestamos.equals(FiltroPrestamos.ATRASADOS) ) {
				vo.setDiasAtraso(hFechas.diferenciaDias(vo.getPrestamoFecPlazoEntrega(), new Date()));
			}
			
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<VoPrestamo> buscarPrestamosPorEstado(FiltroPrestamos filtroPrestamos)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {

		List<SolaTabPrestamo> registros;
		
		switch (filtroPrestamos) {
		case ATRASADOS:
			registros = prestamoDAO.filtraPorCodigoEstado(PRESTAMO_ATRASADO);
			break;
		case VIGENTES:
			registros = prestamoDAO.filtraPorCodigoEstado(PRESTAMO_VIGENTE);
			break;
		case DEVUELTOS:
			registros = prestamoDAO.filtraPorCodigoEstado(PRESTAMO_DEVUELTO);
			break;

		default:
			registros = new ArrayList<>();
			break;
		}
		
		List<VoPrestamo> prestamos= new ArrayList<>();
		for (SolaTabPrestamo dto : registros) {
			VoPrestamo vo = helperVoEntity.toVO(dto);
			if (filtroPrestamos.equals(FiltroPrestamos.ATRASADOS) ) {
				vo.setDiasAtraso(hFechas.diferenciaDias(vo.getPrestamoFecPlazoEntrega(), new Date()));
			}
			prestamos.add(vo);
		}
		return prestamos;
	}

	@Override
	public VoEstadisticasPrestamos obtenerEstadisticas() {
		VoEstadisticasPrestamos estadisticas = new VoEstadisticasPrestamos();
		try {
			estadisticas.setNroAtrasados(prestamoDAO.filtraPorCodigoEstado(PRESTAMO_ATRASADO).size());
			estadisticas.setNroDevueltos(prestamoDAO.filtraPorCodigoEstado(PRESTAMO_DEVUELTO).size());
			estadisticas.setNroVigentes(prestamoDAO.filtraPorCodigoEstado(PRESTAMO_VIGENTE).size());
			estadisticas.setNroTotal(prestamoDAO.findAll().size());
		} catch (Exception e) {
			estadisticas.setTieneError(true);
		}
		return estadisticas;
	}

	@Override
	public List<VoPrestamo> buscarPendientesPorLibro(VoLibro voLibroEncontrado) {
		List<VoPrestamo> prestamos= new ArrayList<>();
		List<SolaTabPrestamo> registros = prestamoDAO.buscarDisponibilidadLibro(voLibroEncontrado.getLibroCodLibro());
		for (SolaTabPrestamo dto : registros) {
			VoPrestamo vo = helperVoEntity.toVO(dto);
			if ( vo.getPrestamoCodEstado().equals(PRESTAMO_ATRASADO) ) {
				vo.setDiasAtraso(hFechas.diferenciaDias(vo.getPrestamoFecPlazoEntrega(), new Date()));
			}
			prestamos.add(vo);
		}
		return prestamos;
	}
	
	

}
