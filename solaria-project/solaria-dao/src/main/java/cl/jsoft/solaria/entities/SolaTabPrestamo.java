package cl.jsoft.solaria.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the sola_tab_prestamo database table.
 * 
 */
@Entity
@Table(schema="sola",name="sola_tab_prestamo")
public class SolaTabPrestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOLA_TAB_PRESTAMO_PRESTAMOCODPRESTAMO_GENERATOR", allocationSize=1, sequenceName="SOLA.SOLA_SEQ_PRESTAMO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLA_TAB_PRESTAMO_PRESTAMOCODPRESTAMO_GENERATOR")
	@Column(name="prestamo_cod_prestamo")
	private long prestamoCodPrestamo;

	@Column(name="prestamo_cod_estado")
	private BigDecimal prestamoCodEstado;

	@Column(name="prestamo_fec_dev_real")
	private Timestamp prestamoFecDevReal;

	@Temporal(TemporalType.DATE)
	@Column(name="prestamo_fec_inicio")
	private Date prestamoFecInicio;

	@Column(name="prestamo_fec_insert")
	private Timestamp prestamoFecInsert;

	@Temporal(TemporalType.DATE)
	@Column(name="prestamo_fec_plazo_entrega")
	private Date prestamoFecPlazoEntrega;

	@Column(name="prestamo_fec_update")
	private Timestamp prestamoFecUpdate;

	//uni-directional many-to-one association to SolaTabCliente
	@ManyToOne
	@JoinColumn(name="cliente_cod_cliente")
	private SolaTabCliente solaTabCliente;

	//uni-directional many-to-one association to SolaTabLibro
	@ManyToOne
	@JoinColumn(name="libro_cod_libro")
	private SolaTabLibro solaTabLibro;

	public SolaTabPrestamo() {
	}

	public long getPrestamoCodPrestamo() {
		return this.prestamoCodPrestamo;
	}

	public void setPrestamoCodPrestamo(long prestamoCodPrestamo) {
		this.prestamoCodPrestamo = prestamoCodPrestamo;
	}

	public BigDecimal getPrestamoCodEstado() {
		return this.prestamoCodEstado;
	}

	public void setPrestamoCodEstado(BigDecimal prestamoCodEstado) {
		this.prestamoCodEstado = prestamoCodEstado;
	}

	public Timestamp getPrestamoFecDevReal() {
		return this.prestamoFecDevReal;
	}

	public void setPrestamoFecDevReal(Timestamp prestamoFecDevReal) {
		this.prestamoFecDevReal = prestamoFecDevReal;
	}

	public Date getPrestamoFecInicio() {
		return this.prestamoFecInicio;
	}

	public void setPrestamoFecInicio(Date prestamoFecInicio) {
		this.prestamoFecInicio = prestamoFecInicio;
	}

	public Timestamp getPrestamoFecInsert() {
		return this.prestamoFecInsert;
	}

	public void setPrestamoFecInsert(Timestamp prestamoFecInsert) {
		this.prestamoFecInsert = prestamoFecInsert;
	}

	public Date getPrestamoFecPlazoEntrega() {
		return this.prestamoFecPlazoEntrega;
	}

	public void setPrestamoFecPlazoEntrega(Date prestamoFecPlazoEntrega) {
		this.prestamoFecPlazoEntrega = prestamoFecPlazoEntrega;
	}

	public Timestamp getPrestamoFecUpdate() {
		return this.prestamoFecUpdate;
	}

	public void setPrestamoFecUpdate(Timestamp prestamoFecUpdate) {
		this.prestamoFecUpdate = prestamoFecUpdate;
	}

	public SolaTabCliente getSolaTabCliente() {
		return this.solaTabCliente;
	}

	public void setSolaTabCliente(SolaTabCliente solaTabCliente) {
		this.solaTabCliente = solaTabCliente;
	}

	public SolaTabLibro getSolaTabLibro() {
		return this.solaTabLibro;
	}

	public void setSolaTabLibro(SolaTabLibro solaTabLibro) {
		this.solaTabLibro = solaTabLibro;
	}

	@Override
	public String toString() {
		return "SolaTabPrestamo [prestamoCodPrestamo=" + prestamoCodPrestamo
				+ ", prestamoCodEstado=" + prestamoCodEstado
				+ ", prestamoFecDevReal=" + prestamoFecDevReal
				+ ", prestamoFecInicio=" + prestamoFecInicio
				+ ", prestamoFecInsert=" + prestamoFecInsert
				+ ", prestamoFecPlazoEntrega=" + prestamoFecPlazoEntrega
				+ ", prestamoFecUpdate=" + prestamoFecUpdate
				+ ", solaTabCliente=" + solaTabCliente + ", solaTabLibro="
				+ solaTabLibro + "]";
	}
	
	

}