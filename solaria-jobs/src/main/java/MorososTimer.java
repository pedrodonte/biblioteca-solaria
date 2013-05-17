import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.daos.SolaTabPrestamoDAO;
import cl.jsoft.solaria.entities.SolaTabPrestamo;

@Singleton
public class MorososTimer {

	Logger logger = Logger.getLogger(getClass());

	@Inject
	SolaTabPrestamoDAO prestamoDAO;

	List<SolaTabPrestamo> morosos;

	@Schedule(second = "00", minute = "01", hour = "01")
	public void identificarMorosos(Timer timer) {
		logger.info("############################");
		logger.info("# IDENTIFICANDO MOROSOS... #");
		logger.info("############################");
		buscaRegistros();
		if (morosos != null) {
			logger.info(morosos.size() + " candidatos a morosos encontrados.");
			aplicaCambiosEnLosRegistros();
		} else {
			logger.info("Ya no quedan candidatos a morosos para hoy.");
		}
	}

	private void aplicaCambiosEnLosRegistros() {
		for (SolaTabPrestamo prestamo : morosos) {
			marcarComoMoroso(prestamo);
		}
	}

	private void buscaRegistros() {
		morosos = prestamoDAO.buscaMorososCandidatos();
	}

	private void marcarComoMoroso(SolaTabPrestamo prestamo) {
		if (prestamo.getPrestamoCodEstado().equals(BigDecimal.ONE)) {
			prestamo.setPrestamoCodEstado(BigDecimal.TEN);
			prestamo.setPrestamoFecUpdate(new Timestamp(new Date().getTime()));
			prestamoDAO.update(prestamo);
		} else {
			logger.error("SQL de candidatos a morosos trajo registro erroneo:"
					+ prestamo);
		}
	}

	@PostConstruct
	public void init() {
		logger.info("IDENTIFICADOR DE MOROSOSO");
	}

}
