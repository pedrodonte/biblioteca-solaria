package cl.jsoft.solaria.dominio.vos;

/* CLASE - AUTOGENERADA
 * FECHA CREACION: Fri Nov 09 16:09:53 CLST 2012 */
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.io.Serializable;

public class VoPrestamo implements Serializable, Cloneable {

	private static final long serialVersionUID = 1352488193056L;
	private long prestamoCodPrestamo;
	private BigDecimal prestamoCodEstado;
	private Timestamp prestamoFecDevReal;
	private Date prestamoFecInicio;
	private Timestamp prestamoFecInsert;
	private Date prestamoFecPlazoEntrega;
	private Timestamp prestamoFecUpdate;
	private VoCliente voCliente;
	private VoLibro voLibro;

	public VoPrestamo() {
	}

	public void setPrestamoCodPrestamo(long prestamoCodPrestamo) {
		this.prestamoCodPrestamo = prestamoCodPrestamo;
	}

	public long getPrestamoCodPrestamo() {
		return this.prestamoCodPrestamo;
	}

	public void setPrestamoCodEstado(BigDecimal prestamoCodEstado) {
		this.prestamoCodEstado = prestamoCodEstado;
	}

	public BigDecimal getPrestamoCodEstado() {
		return this.prestamoCodEstado;
	}

	public void setPrestamoFecDevReal(Timestamp prestamoFecDevReal) {
		this.prestamoFecDevReal = prestamoFecDevReal;
	}

	public Timestamp getPrestamoFecDevReal() {
		return this.prestamoFecDevReal;
	}

	public void setPrestamoFecInicio(Date prestamoFecInicio) {
		this.prestamoFecInicio = prestamoFecInicio;
	}

	public Date getPrestamoFecInicio() {
		return this.prestamoFecInicio;
	}

	public void setPrestamoFecInsert(Timestamp prestamoFecInsert) {
		this.prestamoFecInsert = prestamoFecInsert;
	}

	public Timestamp getPrestamoFecInsert() {
		return this.prestamoFecInsert;
	}

	public void setPrestamoFecPlazoEntrega(Date prestamoFecPlazoEntrega) {
		this.prestamoFecPlazoEntrega = prestamoFecPlazoEntrega;
	}

	public Date getPrestamoFecPlazoEntrega() {
		return this.prestamoFecPlazoEntrega;
	}

	public void setPrestamoFecUpdate(Timestamp prestamoFecUpdate) {
		this.prestamoFecUpdate = prestamoFecUpdate;
	}

	public Timestamp getPrestamoFecUpdate() {
		return this.prestamoFecUpdate;
	}

	public void setVoCliente(VoCliente voCliente) {
		this.voCliente = voCliente;
	}

	public VoCliente getVoCliente() {
		return this.voCliente;
	}

	public void setVoLibro(VoLibro voLibro) {
		this.voLibro = voLibro;
	}

	public VoLibro getVoLibro() {
		return this.voLibro;
	}

	@Override
	public String toString() {
		return "VoPrestamo[prestamoCodPrestamo=" + prestamoCodPrestamo
				+ ",prestamoCodEstado=" + prestamoCodEstado
				+ ",prestamoFecDevReal=" + prestamoFecDevReal
				+ ",prestamoFecInicio=" + prestamoFecInicio
				+ ",prestamoFecInsert=" + prestamoFecInsert
				+ ",prestamoFecPlazoEntrega=" + prestamoFecPlazoEntrega
				+ ",prestamoFecUpdate=" + prestamoFecUpdate + ",voCliente="
				+ voCliente + ",voLibro=" + voLibro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((prestamoCodEstado == null) ? 0 : prestamoCodEstado
						.hashCode());
		result = prime * result
				+ (int) (prestamoCodPrestamo ^ (prestamoCodPrestamo >>> 32));
		result = prime
				* result
				+ ((prestamoFecDevReal == null) ? 0 : prestamoFecDevReal
						.hashCode());
		result = prime
				* result
				+ ((prestamoFecInicio == null) ? 0 : prestamoFecInicio
						.hashCode());
		result = prime
				* result
				+ ((prestamoFecInsert == null) ? 0 : prestamoFecInsert
						.hashCode());
		result = prime
				* result
				+ ((prestamoFecPlazoEntrega == null) ? 0
						: prestamoFecPlazoEntrega.hashCode());
		result = prime
				* result
				+ ((prestamoFecUpdate == null) ? 0 : prestamoFecUpdate
						.hashCode());
		result = prime * result
				+ ((voCliente == null) ? 0 : voCliente.hashCode());
		result = prime * result + ((voLibro == null) ? 0 : voLibro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoPrestamo other = (VoPrestamo) obj;
		if (prestamoCodEstado == null) {
			if (other.prestamoCodEstado != null)
				return false;
		} else if (!prestamoCodEstado.equals(other.prestamoCodEstado))
			return false;
		if (prestamoCodPrestamo != other.prestamoCodPrestamo)
			return false;
		if (prestamoFecDevReal == null) {
			if (other.prestamoFecDevReal != null)
				return false;
		} else if (!prestamoFecDevReal.equals(other.prestamoFecDevReal))
			return false;
		if (prestamoFecInicio == null) {
			if (other.prestamoFecInicio != null)
				return false;
		} else if (!prestamoFecInicio.equals(other.prestamoFecInicio))
			return false;
		if (prestamoFecInsert == null) {
			if (other.prestamoFecInsert != null)
				return false;
		} else if (!prestamoFecInsert.equals(other.prestamoFecInsert))
			return false;
		if (prestamoFecPlazoEntrega == null) {
			if (other.prestamoFecPlazoEntrega != null)
				return false;
		} else if (!prestamoFecPlazoEntrega
				.equals(other.prestamoFecPlazoEntrega))
			return false;
		if (prestamoFecUpdate == null) {
			if (other.prestamoFecUpdate != null)
				return false;
		} else if (!prestamoFecUpdate.equals(other.prestamoFecUpdate))
			return false;
		if (voCliente == null) {
			if (other.voCliente != null)
				return false;
		} else if (!voCliente.equals(other.voCliente))
			return false;
		if (voLibro == null) {
			if (other.voLibro != null)
				return false;
		} else if (!voLibro.equals(other.voLibro))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
