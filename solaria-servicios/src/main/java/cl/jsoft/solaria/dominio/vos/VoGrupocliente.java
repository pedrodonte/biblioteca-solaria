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

}
