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
	
	private String nombreCompleto;
	private String selectOneMenu;

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
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getSelectOneMenu() {
		return selectOneMenu;
	}
	public void setSelectOneMenu() {
		this.selectOneMenu = nombreCompleto+" - "+voGrupocliente.getGrupoclienteNombre()+" @"+clienteCodCliente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((clienteApellidos == null) ? 0 : clienteApellidos.hashCode());
		result = prime * result
				+ (int) (clienteCodCliente ^ (clienteCodCliente >>> 32));
		result = prime
				* result
				+ ((clienteDireccion == null) ? 0 : clienteDireccion.hashCode());
		result = prime
				* result
				+ ((clienteFecInsert == null) ? 0 : clienteFecInsert.hashCode());
		result = prime
				* result
				+ ((clienteFecNacimiento == null) ? 0 : clienteFecNacimiento
						.hashCode());
		result = prime
				* result
				+ ((clienteFecUpdate == null) ? 0 : clienteFecUpdate.hashCode());
		result = prime
				* result
				+ ((clienteIdentificador == null) ? 0 : clienteIdentificador
						.hashCode());
		result = prime * result
				+ ((clienteImg == null) ? 0 : clienteImg.hashCode());
		result = prime * result
				+ ((clienteNombres == null) ? 0 : clienteNombres.hashCode());
		result = prime * result
				+ ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result
				+ ((selectOneMenu == null) ? 0 : selectOneMenu.hashCode());
		result = prime * result
				+ ((voGrupocliente == null) ? 0 : voGrupocliente.hashCode());
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
		VoCliente other = (VoCliente) obj;
		if (clienteApellidos == null) {
			if (other.clienteApellidos != null)
				return false;
		} else if (!clienteApellidos.equals(other.clienteApellidos))
			return false;
		if (clienteCodCliente != other.clienteCodCliente)
			return false;
		if (clienteDireccion == null) {
			if (other.clienteDireccion != null)
				return false;
		} else if (!clienteDireccion.equals(other.clienteDireccion))
			return false;
		if (clienteFecInsert == null) {
			if (other.clienteFecInsert != null)
				return false;
		} else if (!clienteFecInsert.equals(other.clienteFecInsert))
			return false;
		if (clienteFecNacimiento == null) {
			if (other.clienteFecNacimiento != null)
				return false;
		} else if (!clienteFecNacimiento.equals(other.clienteFecNacimiento))
			return false;
		if (clienteFecUpdate == null) {
			if (other.clienteFecUpdate != null)
				return false;
		} else if (!clienteFecUpdate.equals(other.clienteFecUpdate))
			return false;
		if (clienteIdentificador == null) {
			if (other.clienteIdentificador != null)
				return false;
		} else if (!clienteIdentificador.equals(other.clienteIdentificador))
			return false;
		if (clienteImg == null) {
			if (other.clienteImg != null)
				return false;
		} else if (!clienteImg.equals(other.clienteImg))
			return false;
		if (clienteNombres == null) {
			if (other.clienteNombres != null)
				return false;
		} else if (!clienteNombres.equals(other.clienteNombres))
			return false;
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (selectOneMenu == null) {
			if (other.selectOneMenu != null)
				return false;
		} else if (!selectOneMenu.equals(other.selectOneMenu))
			return false;
		if (voGrupocliente == null) {
			if (other.voGrupocliente != null)
				return false;
		} else if (!voGrupocliente.equals(other.voGrupocliente))
			return false;
		return true;
	}
	
	
	

}
