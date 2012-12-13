package cl.jsoft.solaria.dominio.vos;


/* CLASE - AUTOGENERADA
 * FECHA CREACION: Fri Nov 09 16:09:53 CLST 2012 */
import java.io.Serializable;

public class VoGrupocliente implements Serializable{

	private static final long serialVersionUID = 1352488193034L;
	private long grupoclienteCodGrupocliente;
	private String grupoclienteNombre;

	public VoGrupocliente(){
	}
	public void setGrupoclienteCodGrupocliente(long grupoclienteCodGrupocliente){
		this.grupoclienteCodGrupocliente=grupoclienteCodGrupocliente;
	}
	public long getGrupoclienteCodGrupocliente(){
		return this.grupoclienteCodGrupocliente;
	}
	public void setGrupoclienteNombre(String grupoclienteNombre){
		this.grupoclienteNombre=grupoclienteNombre;
	}
	public String getGrupoclienteNombre(){
		return this.grupoclienteNombre;
	}
	@Override
	public String toString(){
		 return "VoGrupocliente[grupoclienteCodGrupocliente="+grupoclienteCodGrupocliente
		 +",grupoclienteNombre="+grupoclienteNombre+"]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (grupoclienteCodGrupocliente ^ (grupoclienteCodGrupocliente >>> 32));
		result = prime
				* result
				+ ((grupoclienteNombre == null) ? 0 : grupoclienteNombre
						.hashCode());
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
		VoGrupocliente other = (VoGrupocliente) obj;
		if (grupoclienteCodGrupocliente != other.grupoclienteCodGrupocliente)
			return false;
		if (grupoclienteNombre == null) {
			if (other.grupoclienteNombre != null)
				return false;
		} else if (!grupoclienteNombre.equals(other.grupoclienteNombre))
			return false;
		return true;
	}
	
	

}
