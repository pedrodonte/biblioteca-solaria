package cl.jsoft.solaria.seguridad;

public interface IValidacionCredencial {
	
	public void executaValidacion(CredencialSeguridad credencialSeguridad) throws ValidacionNegativaException;

}
