package cl.jsoft.solaria.seguridad.vos;


/* CLASE - AUTOGENERADA
 * FECHA CREACION: Sun May 26 15:21:31 CLT 2013 */
import java.sql.Timestamp;
import java.io.Serializable;

public class VoRelUsuaPerfil implements Serializable{

	private static final long serialVersionUID = 1369596091287L;
	private long relCodRel;
	private Timestamp regFechaInsert;
	private Timestamp regFechaUpdate;
	private Boolean relVigente;
	private VoPerfil voPerfil;
	private VoUsua voUsua;

	public VoRelUsuaPerfil(){
	}
	public void setRelCodRel(long relCodRel){
		this.relCodRel=relCodRel;
	}
	public long getRelCodRel(){
		return this.relCodRel;
	}
	public void setRegFechaInsert(Timestamp regFechaInsert){
		this.regFechaInsert=regFechaInsert;
	}
	public Timestamp getRegFechaInsert(){
		return this.regFechaInsert;
	}
	public void setRegFechaUpdate(Timestamp regFechaUpdate){
		this.regFechaUpdate=regFechaUpdate;
	}
	public Timestamp getRegFechaUpdate(){
		return this.regFechaUpdate;
	}
	public void setRelVigente(Boolean relVigente){
		this.relVigente=relVigente;
	}
	public Boolean getRelVigente(){
		return this.relVigente;
	}
	public void setVoPerfil(VoPerfil voPerfil){
		this.voPerfil=voPerfil;
	}
	public VoPerfil getVoPerfil(){
		return this.voPerfil;
	}
	public void setVoUsua(VoUsua voUsua){
		this.voUsua=voUsua;
	}
	public VoUsua getVoUsua(){
		return this.voUsua;
	}
	@Override
	public String toString(){
		 return "VoRelUsuaPerfil[relCodRel="+relCodRel
		 +",regFechaInsert="+regFechaInsert
		 +",regFechaUpdate="+regFechaUpdate
		 +",relVigente="+relVigente
		 +",voPerfil="+voPerfil
		 +",voUsua="+voUsua+"]";
	}

}
