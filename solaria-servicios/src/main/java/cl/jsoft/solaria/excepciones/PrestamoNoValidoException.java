package cl.jsoft.solaria.excepciones;

public class PrestamoNoValidoException extends Exception {

	private static final long serialVersionUID = -8897720213580897746L;

	public PrestamoNoValidoException() {
		super();
	}

	public PrestamoNoValidoException(String message) {
		super(message);
	}

}
