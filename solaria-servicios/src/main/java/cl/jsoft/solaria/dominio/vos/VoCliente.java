package cl.jsoft.solaria.dominio.vos;


/* CLASE - AUTOGENERADA
 * FECHA CREACION: Fri Nov 09 16:09:53 CLST 2012 */
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;

public class VoCliente implements Serializable{

	private static final long serialVersionUID = 1352488193025L;
	private long clienteCodCliente;
	private String clienteApellidos;
	private String clienteDireccion;
	private Date clienteFecInsert;
	private Date clienteFecNacimiento;
	private Date clienteFecUpdate;
	private BigDecimal clienteIdentificador;
	private String clienteImg;
	private String clienteNombres;
	private VoGrupocliente voGrupocliente;

	public VoCliente(){
	}
	public void setClienteCodCliente(long clienteCodCliente){
		this.clienteCodCliente=clienteCodCliente;
	}
	public long getClienteCodCliente(){
		return this.clienteCodCliente;
	}
	public void setClienteApellidos(String clienteApellidos){
		this.clienteApellidos=clienteApellidos;
	}
	public String getClienteApellidos(){
		return this.clienteApellidos;
	}
	public void setClienteDireccion(String clienteDireccion){
		this.clienteDireccion=clienteDireccion;
	}
	public String getClienteDireccion(){
		return this.clienteDireccion;
	}
	public void setClienteFecInsert(Date clienteFecInsert){
		this.clienteFecInsert=clienteFecInsert;
	}
	public Date getClienteFecInsert(){
		return this.clienteFecInsert;
	}
	public void setClienteFecNacimiento(Date clienteFecNacimiento){
		this.clienteFecNacimiento=clienteFecNacimiento;
	}
	public Date getClienteFecNacimiento(){
		return this.clienteFecNacimiento;
	}
	public void setClienteFecUpdate(Date clienteFecUpdate){
		this.clienteFecUpdate=clienteFecUpdate;
	}
	public Date getClienteFecUpdate(){
		return this.clienteFecUpdate;
	}
	public void setClienteIdentificador(BigDecimal clienteIdentificador){
		this.clienteIdentificador=clienteIdentificador;
	}
	public BigDecimal getClienteIdentificador(){
		return this.clienteIdentificador;
	}
	public void setClienteImg(String clienteImg){
		this.clienteImg=clienteImg;
	}
	public String getClienteImg(){
		return this.clienteImg;
	}
	public void setClienteNombres(String clienteNombres){
		this.clienteNombres=clienteNombres;
	}
	public String getClienteNombres(){
		return this.clienteNombres;
	}
	public void setVoGrupocliente(VoGrupocliente voGrupocliente){
		this.voGrupocliente=voGrupocliente;
	}
	public VoGrupocliente getVoGrupocliente(){
		return this.voGrupocliente;
	}
	@Override
	public String toString(){
		 return "VoCliente[clienteCodCliente="+clienteCodCliente
		 +",clienteApellidos="+clienteApellidos
		 +",clienteDireccion="+clienteDireccion
		 +",clienteFecInsert="+clienteFecInsert
		 +",clienteFecNacimiento="+clienteFecNacimiento
		 +",clienteFecUpdate="+clienteFecUpdate
		 +",clienteIdentificador="+clienteIdentificador
		 +",clienteImg="+clienteImg
		 +",clienteNombres="+clienteNombres
		 +",voGrupocliente="+voGrupocliente+"]";
	}

}
