package cl.jsoft.solaria.util;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HelperFechas {

	static HelperFechas INSTANCIA = null;

	Format formaDDMMAAAAslash = new SimpleDateFormat("dd/MM/yyyy");
	Format formaDDMMAAAAguion = new SimpleDateFormat("dd-MM-yyyy");

	public HelperFechas() {
	}

	private static void crearInstancia() {
		if (INSTANCIA == null) {
			if (INSTANCIA == null) {
				INSTANCIA = new HelperFechas();
			}
		}
	}

	public static HelperFechas getInstancia() {
		crearInstancia();
		return INSTANCIA;
	}

	public Date obtenerDateActual() {
		return new Date();
	}

	public Timestamp obtenerTimeStampActual() {
		return new Timestamp(obtenerDateActual().getTime());
	}

	public String fechaFormateadaSlash() {
		return formaDDMMAAAAslash.format(new Date());
	}

	public String fechaFormateadaGuion() {
		return formaDDMMAAAAguion.format(new Date());
	}

	public String fechaFormateadaSlash(Date fecha) {
		return formaDDMMAAAAslash.format(fecha);
	}

	public String fechaFormateadaGuion(Date fecha) {
		return formaDDMMAAAAguion.format(fecha);
	}

	public Date fechaFutura(int cantidadDias) {
		Calendar fechaPosterior = Calendar.getInstance();
		fechaPosterior.add(Calendar.DAY_OF_MONTH, cantidadDias);
		return fechaPosterior.getTime();
	}

	public long diferenciaDias(Date fecha1, Date fecha2) {
		// Crear 2 instancias de Calendar
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		// Establecer las fechas
		 cal1.setTime(fecha1);
		 cal2.setTime(fecha2);
//		cal1.set(2006, 12, 30);
//		cal2.set(2007, 5, 3);

		// conseguir la representacion de la fecha en milisegundos
		long milis1 = cal1.getTimeInMillis();
		long milis2 = cal2.getTimeInMillis();

		// calcular la diferencia en milisengundos
		long diff = milis2 - milis1;
//
//		// calcular la diferencia en segundos
//		long diffSeconds = diff / 1000;
//
//		// calcular la diferencia en minutos
//		long diffMinutes = diff / (60 * 1000);
//
//		// calcular la diferencia en horas
//		long diffHours = diff / (60 * 60 * 1000);

		// calcular la diferencia en dias
		long diffDays = diff / (24 * 60 * 60 * 1000);

//		System.out.println("En milisegundos: " + diff + " milisegundos.");
//		System.out.println("En segundos: " + diffSeconds + " segundos.");
//		System.out.println("En minutos: " + diffMinutes + " minutos.");
//		System.out.println("En horas: " + diffHours + " horas.");
//		System.out.println("En dias: " + diffDays + " dias.");
		return diffDays;
	}

	public static void main(String[] args) {
		HelperFechas helperFechas = new HelperFechas();
		helperFechas.diferenciaDias(null, null);
	}

}
