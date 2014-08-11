package cl.jsoft.solaria.segu.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the segu_tab_usua database table.
 * 
 */
@Entity
@Table(name="segu_tab_usua")
public class SeguTabUsua implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usua_cod_usua")
	private long usuaCodUsua;

	@Column(name="usua_contrasena")
	private String usuaContrasena;

	@Column(name="usua_email")
	private String usuaEmail;

	@Column(name="usua_nombre")
	private String usuaNombre;

	public SeguTabUsua() {
	}

	public long getUsuaCodUsua() {
		return this.usuaCodUsua;
	}

	public void setUsuaCodUsua(long usuaCodUsua) {
		this.usuaCodUsua = usuaCodUsua;
	}

	public String getUsuaContrasena() {
		return this.usuaContrasena;
	}

	public void setUsuaContrasena(String usuaContrasena) {
		this.usuaContrasena = usuaContrasena;
	}

	public String getUsuaEmail() {
		return this.usuaEmail;
	}

	public void setUsuaEmail(String usuaEmail) {
		this.usuaEmail = usuaEmail;
	}

	public String getUsuaNombre() {
		return this.usuaNombre;
	}

	public void setUsuaNombre(String usuaNombre) {
		this.usuaNombre = usuaNombre;
	}

}