package cl.jsoft.solaria.dominio.vos;


/* CLASE - AUTOGENERADA
 * FECHA CREACION: Fri Nov 09 16:09:53 CLST 2012 */
import java.io.Serializable;

public class VoGenerolibro implements Serializable{

	private static final long serialVersionUID = 1352488193031L;
	private long generolibroCodGenerolibro;
	private String generolibroNombre;

	public VoGenerolibro(){
	}
	public void setGenerolibroCodGenerolibro(long generolibroCodGenerolibro){
		this.generolibroCodGenerolibro=generolibroCodGenerolibro;
	}
	public long getGenerolibroCodGenerolibro(){
		return this.generolibroCodGenerolibro;
	}
	public void setGenerolibroNombre(String generolibroNombre){
		this.generolibroNombre=generolibroNombre;
	}
	public String getGenerolibroNombre(){
		return this.generolibroNombre;
	}
	@Override
	public String toString(){
		 return "VoGenerolibro[generolibroCodGenerolibro="+generolibroCodGenerolibro
		 +",generolibroNombre="+generolibroNombre+"]";
	}

}
