package cl.jsoft.solaria.seguridad;

public interface IValidacionCredencial {
	
	public void executaValidacion(Object objetoValidacion) throws ValidacionNegativaException;

}
