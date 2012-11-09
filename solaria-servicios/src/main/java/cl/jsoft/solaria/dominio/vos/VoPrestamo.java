package cl.jsoft.solaria.dominio.vos;


/* CLASE - AUTOGENERADA
 * FECHA CREACION: Fri Nov 09 16:09:53 CLST 2012 */
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.io.Serializable;

public class VoPrestamo implements Serializable{

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

	public VoPrestamo(){
	}
	public void setPrestamoCodPrestamo(long prestamoCodPrestamo){
		this.prestamoCodPrestamo=prestamoCodPrestamo;
	}
	public long getPrestamoCodPrestamo(){
		return this.prestamoCodPrestamo;
	}
	public void setPrestamoCodEstado(BigDecimal prestamoCodEstado){
		this.prestamoCodEstado=prestamoCodEstado;
	}
	public BigDecimal getPrestamoCodEstado(){
		return this.prestamoCodEstado;
	}
	public void setPrestamoFecDevReal(Timestamp prestamoFecDevReal){
		this.prestamoFecDevReal=prestamoFecDevReal;
	}
	public Timestamp getPrestamoFecDevReal(){
		return this.prestamoFecDevReal;
	}
	public void setPrestamoFecInicio(Date prestamoFecInicio){
		this.prestamoFecInicio=prestamoFecInicio;
	}
	public Date getPrestamoFecInicio(){
		return this.prestamoFecInicio;
	}
	public void setPrestamoFecInsert(Timestamp prestamoFecInsert){
		this.prestamoFecInsert=prestamoFecInsert;
	}
	public Timestamp getPrestamoFecInsert(){
		return this.prestamoFecInsert;
	}
	public void setPrestamoFecPlazoEntrega(Date prestamoFecPlazoEntrega){
		this.prestamoFecPlazoEntrega=prestamoFecPlazoEntrega;
	}
	public Date getPrestamoFecPlazoEntrega(){
		return this.prestamoFecPlazoEntrega;
	}
	public void setPrestamoFecUpdate(Timestamp prestamoFecUpdate){
		this.prestamoFecUpdate=prestamoFecUpdate;
	}
	public Timestamp getPrestamoFecUpdate(){
		return this.prestamoFecUpdate;
	}
	public void setVoCliente(VoCliente voCliente){
		this.voCliente=voCliente;
	}
	public VoCliente getVoCliente(){
		return this.voCliente;
	}
	public void setVoLibro(VoLibro voLibro){
		this.voLibro=voLibro;
	}
	public VoLibro getVoLibro(){
		return this.voLibro;
	}
	@Override
	public String toString(){
		 return "VoPrestamo[prestamoCodPrestamo="+prestamoCodPrestamo
		 +",prestamoCodEstado="+prestamoCodEstado
		 +",prestamoFecDevReal="+prestamoFecDevReal
		 +",prestamoFecInicio="+prestamoFecInicio
		 +",prestamoFecInsert="+prestamoFecInsert
		 +",prestamoFecPlazoEntrega="+prestamoFecPlazoEntrega
		 +",prestamoFecUpdate="+prestamoFecUpdate
		 +",voCliente="+voCliente
		 +",voLibro="+voLibro+"]";
	}

}
