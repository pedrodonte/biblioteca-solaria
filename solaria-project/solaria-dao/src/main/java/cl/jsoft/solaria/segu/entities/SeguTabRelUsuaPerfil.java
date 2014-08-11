package cl.jsoft.solaria.segu.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the segu_tab_rel_usua_perfil database table.
 * 
 */
@Entity
@Table(name="segu_tab_rel_usua_perfil")
public class SeguTabRelUsuaPerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rel_cod_rel")
	private long relCodRel;

	@Column(name="reg_fecha_insert")
	private Timestamp regFechaInsert;

	@Column(name="reg_fecha_update")
	private Timestamp regFechaUpdate;

	@Column(name="rel_vigente")
	private Boolean relVigente;

	//uni-directional many-to-one association to SeguTabPerfil
	@ManyToOne
	@JoinColumn(name="perfil_cod_perfil")
	private SeguTabPerfil seguTabPerfil;

	//uni-directional many-to-one association to SeguTabUsua
	@ManyToOne
	@JoinColumn(name="usua_cod_usua")
	private SeguTabUsua seguTabUsua;

	public SeguTabRelUsuaPerfil() {
	}

	public long getRelCodRel() {
		return this.relCodRel;
	}

	public void setRelCodRel(long relCodRel) {
		this.relCodRel = relCodRel;
	}

	public Timestamp getRegFechaInsert() {
		return this.regFechaInsert;
	}

	public void setRegFechaInsert(Timestamp regFechaInsert) {
		this.regFechaInsert = regFechaInsert;
	}

	public Timestamp getRegFechaUpdate() {
		return this.regFechaUpdate;
	}

	public void setRegFechaUpdate(Timestamp regFechaUpdate) {
		this.regFechaUpdate = regFechaUpdate;
	}

	public Boolean getRelVigente() {
		return this.relVigente;
	}

	public void setRelVigente(Boolean relVigente) {
		this.relVigente = relVigente;
	}

	public SeguTabPerfil getSeguTabPerfil() {
		return this.seguTabPerfil;
	}

	public void setSeguTabPerfil(SeguTabPerfil seguTabPerfil) {
		this.seguTabPerfil = seguTabPerfil;
	}

	public SeguTabUsua getSeguTabUsua() {
		return this.seguTabUsua;
	}

	public void setSeguTabUsua(SeguTabUsua seguTabUsua) {
		this.seguTabUsua = seguTabUsua;
	}

}