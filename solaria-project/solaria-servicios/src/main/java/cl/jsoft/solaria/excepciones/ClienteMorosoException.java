package cl.jsoft.solaria.excepciones;

public class ClienteMorosoException extends Exception {

	private static final long serialVersionUID = 12312332L;

	public ClienteMorosoException() {
	}

	public ClienteMorosoException(String message) {
		super(message);
	}

	public ClienteMorosoException(Throwable cause) {
		super(cause);
	}

	public ClienteMorosoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClienteMorosoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
