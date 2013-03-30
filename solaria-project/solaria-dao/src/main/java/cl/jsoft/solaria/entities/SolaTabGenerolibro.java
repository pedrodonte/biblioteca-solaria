package cl.jsoft.solaria.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sola_tab_generolibro database table.
 * 
 */
@Entity
@Table(schema="sola",name="sola_tab_generolibro")
public class SolaTabGenerolibro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOLA_TAB_GENEROLIBRO_GENEROLIBROCODGENEROLIBRO_GENERATOR", allocationSize=1, sequenceName="SOLA.SOLA_SEQ_GENEROLIBRO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLA_TAB_GENEROLIBRO_GENEROLIBROCODGENEROLIBRO_GENERATOR")
	@Column(name="generolibro_cod_generolibro")
	private long generolibroCodGenerolibro;

	@Column(name="generolibro_nombre")
	private String generolibroNombre;

	public SolaTabGenerolibro() {
	}

	public long getGenerolibroCodGenerolibro() {
		return this.generolibroCodGenerolibro;
	}

	public void setGenerolibroCodGenerolibro(long generolibroCodGenerolibro) {
		this.generolibroCodGenerolibro = generolibroCodGenerolibro;
	}

	public String getGenerolibroNombre() {
		return this.generolibroNombre;
	}

	public void setGenerolibroNombre(String generolibroNombre) {
		this.generolibroNombre = generolibroNombre;
	}

}