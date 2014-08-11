package cl.jsoft.solaria.dominio.vos;

import java.io.Serializable;

public class VoEstadisticasPrestamos implements Serializable {

	private static final long serialVersionUID = 1L;

	private long nroAtrasados;
	private long nroVigentes;
	private long nroDevueltos;
	private long nroTotal;

	/**
	 * en caso de ocurrir errores que impidan mostrar la informacion este campo
	 * se torna verdadero y la vista reacciona a este.
	 */
	private boolean tieneError;

	public long getNroAtrasados() {
		return nroAtrasados;
	}

	public void setNroAtrasados(long nroAtrasados) {
		this.nroAtrasados = nroAtrasados;
	}

	public long getNroVigentes() {
		return nroVigentes;
	}

	public void setNroVigentes(long nroVigentes) {
		this.nroVigentes = nroVigentes;
	}

	public long getNroDevueltos() {
		return nroDevueltos;
	}

	public void setNroDevueltos(long nroDevueltos) {
		this.nroDevueltos = nroDevueltos;
	}

	public long getNroTotal() {
		return nroTotal;
	}

	public void setNroTotal(long nroTotal) {
		this.nroTotal = nroTotal;
	}

	public boolean isTieneError() {
		return tieneError;
	}

	public void setTieneError(boolean tieneError) {
		this.tieneError = tieneError;
	}

}
