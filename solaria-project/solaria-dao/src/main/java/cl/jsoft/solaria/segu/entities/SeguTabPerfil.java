package cl.jsoft.solaria.segu.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the segu_tab_perfil database table.
 * 
 */
@Entity
@Table(name="segu_tab_perfil")
public class SeguTabPerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="perfil_cod_perfil")
	private long perfilCodPerfil;

	@Column(name="perfil_boo_cod_vigente")
	private Boolean perfilBooCodVigente;

	@Column(name="perfil_descripcion")
	private String perfilDescripcion;

	@Column(name="perfil_identificador_jaas")
	private String perfilIdentificadorJaas;

	@Column(name="perfil_nombre")
	private String perfilNombre;

	public SeguTabPerfil() {
	}

	public long getPerfilCodPerfil() {
		return this.perfilCodPerfil;
	}

	public void setPerfilCodPerfil(long perfilCodPerfil) {
		this.perfilCodPerfil = perfilCodPerfil;
	}

	public Boolean getPerfilBooCodVigente() {
		return this.perfilBooCodVigente;
	}

	public void setPerfilBooCodVigente(Boolean perfilBooCodVigente) {
		this.perfilBooCodVigente = perfilBooCodVigente;
	}

	public String getPerfilDescripcion() {
		return this.perfilDescripcion;
	}

	public void setPerfilDescripcion(String perfilDescripcion) {
		this.perfilDescripcion = perfilDescripcion;
	}

	public String getPerfilIdentificadorJaas() {
		return this.perfilIdentificadorJaas;
	}

	public void setPerfilIdentificadorJaas(String perfilIdentificadorJaas) {
		this.perfilIdentificadorJaas = perfilIdentificadorJaas;
	}

	public String getPerfilNombre() {
		return this.perfilNombre;
	}

	public void setPerfilNombre(String perfilNombre) {
		this.perfilNombre = perfilNombre;
	}

}