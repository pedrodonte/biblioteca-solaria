package cl.jsoft.solaria.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sola_tab_grupocliente database table.
 * 
 */
@Entity
@Table(schema="sola",name="sola_tab_grupocliente")
public class SolaTabGrupocliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOLA_TAB_GRUPOCLIENTE_GRUPOCLIENTECODGRUPOCLIENTE_GENERATOR", allocationSize=1, sequenceName="SOLA.SOLA_SEQ_GRUPOCLIENTE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLA_TAB_GRUPOCLIENTE_GRUPOCLIENTECODGRUPOCLIENTE_GENERATOR")
	@Column(name="grupocliente_cod_grupocliente")
	private long grupoclienteCodGrupocliente;

	@Column(name="grupocliente_nombre")
	private String grupoclienteNombre;

	public SolaTabGrupocliente() {
	}

	public long getGrupoclienteCodGrupocliente() {
		return this.grupoclienteCodGrupocliente;
	}

	public void setGrupoclienteCodGrupocliente(long grupoclienteCodGrupocliente) {
		this.grupoclienteCodGrupocliente = grupoclienteCodGrupocliente;
	}

	public String getGrupoclienteNombre() {
		return this.grupoclienteNombre;
	}

	public void setGrupoclienteNombre(String grupoclienteNombre) {
		this.grupoclienteNombre = grupoclienteNombre;
	}

}