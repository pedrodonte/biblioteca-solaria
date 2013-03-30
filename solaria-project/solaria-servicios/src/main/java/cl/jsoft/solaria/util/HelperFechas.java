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

	public HelperFechas() {	}

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
	
	public Date obtenerDateActual(){
		return new Date();
	}
	
	public Timestamp obtenerTimeStampActual(){
		return new Timestamp( obtenerDateActual().getTime() );
	}
	
	public String fechaFormateadaSlash(){
		return formaDDMMAAAAslash.format(new Date());
	}
	
	public String fechaFormateadaGuion(){
		return formaDDMMAAAAguion.format(new Date());
	}
	
	public String fechaFormateadaSlash(Date fecha){
		return formaDDMMAAAAslash.format(fecha);
	}
	
	public String fechaFormateadaGuion(Date fecha){
		return formaDDMMAAAAguion.format(fecha);
	}
	
	public Date fechaFutura(int cantidadDias){
		Calendar fechaPosterior = Calendar.getInstance();
		fechaPosterior.add(Calendar.DAY_OF_MONTH, cantidadDias);
		return fechaPosterior.getTime();
	}

}
