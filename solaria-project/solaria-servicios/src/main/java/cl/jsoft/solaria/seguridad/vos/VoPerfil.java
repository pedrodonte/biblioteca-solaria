package cl.jsoft.solaria.seguridad.vos;


/* CLASE - AUTOGENERADA
 * FECHA CREACION: Sun May 26 14:53:30 CLT 2013 */
import java.io.Serializable;

public class VoPerfil implements Serializable, Cloneable{

	private static final long serialVersionUID = 1369594410168L;
	private long perfilCodPerfil;
	private Boolean perfilBooCodVigente;
	private String perfilDescripcion;
	private String perfilIdentificadorJaas;
	private String perfilNombre;

	public VoPerfil(){
	}
	public void setPerfilCodPerfil(long perfilCodPerfil){
		this.perfilCodPerfil=perfilCodPerfil;
	}
	public long getPerfilCodPerfil(){
		return this.perfilCodPerfil;
	}
	public void setPerfilBooCodVigente(Boolean perfilBooCodVigente){
		this.perfilBooCodVigente=perfilBooCodVigente;
	}
	public Boolean getPerfilBooCodVigente(){
		return this.perfilBooCodVigente;
	}
	public void setPerfilDescripcion(String perfilDescripcion){
		this.perfilDescripcion=perfilDescripcion;
	}
	public String getPerfilDescripcion(){
		return this.perfilDescripcion;
	}
	public void setPerfilIdentificadorJaas(String perfilIdentificadorJaas){
		this.perfilIdentificadorJaas=perfilIdentificadorJaas;
	}
	public String getPerfilIdentificadorJaas(){
		return this.perfilIdentificadorJaas;
	}
	public void setPerfilNombre(String perfilNombre){
		this.perfilNombre=perfilNombre;
	}
	public String getPerfilNombre(){
		return this.perfilNombre;
	}
	@Override
	public String toString(){
		 return "VoPerfil[perfilCodPerfil="+perfilCodPerfil
		 +",perfilBooCodVigente="+perfilBooCodVigente
		 +",perfilDescripcion="+perfilDescripcion
		 +",perfilIdentificadorJaas="+perfilIdentificadorJaas
		 +",perfilNombre="+perfilNombre+"]";
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((perfilBooCodVigente == null) ? 0 : perfilBooCodVigente
						.hashCode());
		result = prime * result
				+ (int) (perfilCodPerfil ^ (perfilCodPerfil >>> 32));
		result = prime
				* result
				+ ((perfilDescripcion == null) ? 0 : perfilDescripcion
						.hashCode());
		result = prime
				* result
				+ ((perfilIdentificadorJaas == null) ? 0
						: perfilIdentificadorJaas.hashCode());
		result = prime * result
				+ ((perfilNombre == null) ? 0 : perfilNombre.hashCode());
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
		VoPerfil other = (VoPerfil) obj;
		if (perfilBooCodVigente == null) {
			if (other.perfilBooCodVigente != null)
				return false;
		} else if (!perfilBooCodVigente.equals(other.perfilBooCodVigente))
			return false;
		if (perfilCodPerfil != other.perfilCodPerfil)
			return false;
		if (perfilDescripcion == null) {
			if (other.perfilDescripcion != null)
				return false;
		} else if (!perfilDescripcion.equals(other.perfilDescripcion))
			return false;
		if (perfilIdentificadorJaas == null) {
			if (other.perfilIdentificadorJaas != null)
				return false;
		} else if (!perfilIdentificadorJaas
				.equals(other.perfilIdentificadorJaas))
			return false;
		if (perfilNombre == null) {
			if (other.perfilNombre != null)
				return false;
		} else if (!perfilNombre.equals(other.perfilNombre))
			return false;
		return true;
	}
	
	

}
