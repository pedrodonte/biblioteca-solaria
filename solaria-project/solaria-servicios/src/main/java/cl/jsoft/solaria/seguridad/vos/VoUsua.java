package cl.jsoft.solaria.seguridad.vos;


/* CLASE - AUTOGENERADA
 * FECHA CREACION: Sun May 26 14:53:30 CLT 2013 */
import java.io.Serializable;

public class VoUsua implements Serializable ,Cloneable{

	private static final long serialVersionUID = 1369594410176L;
	private long usuaCodUsua;
	private String usuaContrasena;
	private String usuaEmail;
	private String usuaNombre;

	public VoUsua(){
	}
	public void setUsuaCodUsua(long usuaCodUsua){
		this.usuaCodUsua=usuaCodUsua;
	}
	public long getUsuaCodUsua(){
		return this.usuaCodUsua;
	}
	public void setUsuaContrasena(String usuaContrasena){
		this.usuaContrasena=usuaContrasena;
	}
	public String getUsuaContrasena(){
		return this.usuaContrasena;
	}
	public void setUsuaEmail(String usuaEmail){
		this.usuaEmail=usuaEmail;
	}
	public String getUsuaEmail(){
		return this.usuaEmail;
	}
	public void setUsuaNombre(String usuaNombre){
		this.usuaNombre=usuaNombre;
	}
	public String getUsuaNombre(){
		return this.usuaNombre;
	}
	@Override
	public String toString(){
		 return "VoUsua[usuaCodUsua="+usuaCodUsua
		 +",usuaContrasena="+usuaContrasena
		 +",usuaEmail="+usuaEmail
		 +",usuaNombre="+usuaNombre+"]";
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
		result = prime * result + (int) (usuaCodUsua ^ (usuaCodUsua >>> 32));
		result = prime * result
				+ ((usuaContrasena == null) ? 0 : usuaContrasena.hashCode());
		result = prime * result
				+ ((usuaEmail == null) ? 0 : usuaEmail.hashCode());
		result = prime * result
				+ ((usuaNombre == null) ? 0 : usuaNombre.hashCode());
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
		VoUsua other = (VoUsua) obj;
		if (usuaCodUsua != other.usuaCodUsua)
			return false;
		if (usuaContrasena == null) {
			if (other.usuaContrasena != null)
				return false;
		} else if (!usuaContrasena.equals(other.usuaContrasena))
			return false;
		if (usuaEmail == null) {
			if (other.usuaEmail != null)
				return false;
		} else if (!usuaEmail.equals(other.usuaEmail))
			return false;
		if (usuaNombre == null) {
			if (other.usuaNombre != null)
				return false;
		} else if (!usuaNombre.equals(other.usuaNombre))
			return false;
		return true;
	}
	
	

}
