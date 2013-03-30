package cl.jsoft.solaria.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the sola_tab_libro database table.
 * 
 */
@Entity
@Table(schema="sola",name="sola_tab_libro")
public class SolaTabLibro implements Serializable {
	private static final long serialVersionUID = 6L;

	@Id
	@SequenceGenerator(name="SOLA_TAB_LIBRO_LIBROCODLIBRO_GENERATOR", allocationSize=1, sequenceName="SOLA.SOLA_SEQ_LIBRO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLA_TAB_LIBRO_LIBROCODLIBRO_GENERATOR")
	@Column(name="libro_cod_libro")
	private long libroCodLibro;

	@Column(name="libro_anio_edicion")
	private BigDecimal libroAnioEdicion;

	@Column(name="libro_cant_volumenes")
	private BigDecimal libroCantVolumenes;

	@Column(name="libro_descricpion")
	private String libroDescricpion;

	@Column(name="libro_fec_insert")
	private Timestamp libroFecInsert;

	@Column(name="libro_fec_update")
	private Timestamp libroFecUpdate;

	@Column(name="libro_id_interno")
	private String libroIdInterno;

	@Column(name="libro_isbn")
	private BigDecimal libroIsbn;

	@Column(name="libro_lugar")
	private String libroLugar;

	@Column(name="libro_nombre_autor")
	private String libroNombreAutor;

	@Column(name="libro_nombre_editorial")
	private String libroNombreEditorial;

	@Column(name="libro_nro_edicion")
	private String libroNroEdicion;

	@Column(name="libro_titulo")
	private String libroTitulo;

	//uni-directional many-to-one association to SolaTabGenerolibro
	@ManyToOne
	@JoinColumn(name="generolibro_cod_generolibro")
	private SolaTabGenerolibro solaTabGenerolibro;

	public SolaTabLibro() {
	}

	public long getLibroCodLibro() {
		return this.libroCodLibro;
	}

	public void setLibroCodLibro(long libroCodLibro) {
		this.libroCodLibro = libroCodLibro;
	}

	public BigDecimal getLibroAnioEdicion() {
		return this.libroAnioEdicion;
	}

	public void setLibroAnioEdicion(BigDecimal libroAnioEdicion) {
		this.libroAnioEdicion = libroAnioEdicion;
	}

	public BigDecimal getLibroCantVolumenes() {
		return this.libroCantVolumenes;
	}

	public void setLibroCantVolumenes(BigDecimal libroCantVolumenes) {
		this.libroCantVolumenes = libroCantVolumenes;
	}

	public String getLibroDescricpion() {
		return this.libroDescricpion;
	}

	public void setLibroDescricpion(String libroDescricpion) {
		this.libroDescricpion = libroDescricpion;
	}

	public Timestamp getLibroFecInsert() {
		return this.libroFecInsert;
	}

	public void setLibroFecInsert(Timestamp libroFecInsert) {
		this.libroFecInsert = libroFecInsert;
	}

	public Timestamp getLibroFecUpdate() {
		return this.libroFecUpdate;
	}

	public void setLibroFecUpdate(Timestamp libroFecUpdate) {
		this.libroFecUpdate = libroFecUpdate;
	}

	public String getLibroIdInterno() {
		return this.libroIdInterno;
	}

	public void setLibroIdInterno(String libroIdInterno) {
		this.libroIdInterno = libroIdInterno;
	}

	public BigDecimal getLibroIsbn() {
		return this.libroIsbn;
	}

	public void setLibroIsbn(BigDecimal libroIsbn) {
		this.libroIsbn = libroIsbn;
	}

	public String getLibroLugar() {
		return this.libroLugar;
	}

	public void setLibroLugar(String libroLugar) {
		this.libroLugar = libroLugar;
	}

	public String getLibroNombreAutor() {
		return this.libroNombreAutor;
	}

	public void setLibroNombreAutor(String libroNombreAutor) {
		this.libroNombreAutor = libroNombreAutor;
	}

	public String getLibroNombreEditorial() {
		return this.libroNombreEditorial;
	}

	public void setLibroNombreEditorial(String libroNombreEditorial) {
		this.libroNombreEditorial = libroNombreEditorial;
	}

	public String getLibroNroEdicion() {
		return this.libroNroEdicion;
	}

	public void setLibroNroEdicion(String libroNroEdicion) {
		this.libroNroEdicion = libroNroEdicion;
	}

	public String getLibroTitulo() {
		return this.libroTitulo;
	}

	public void setLibroTitulo(String libroTitulo) {
		this.libroTitulo = libroTitulo;
	}

	public SolaTabGenerolibro getSolaTabGenerolibro() {
		return this.solaTabGenerolibro;
	}

	public void setSolaTabGenerolibro(SolaTabGenerolibro solaTabGenerolibro) {
		this.solaTabGenerolibro = solaTabGenerolibro;
	}

	@Override
	public String toString() {
		return "SolaTabLibro [libroCodLibro=" + libroCodLibro
				+ ", libroAnioEdicion=" + libroAnioEdicion
				+ ", libroCantVolumenes=" + libroCantVolumenes
				+ ", libroDescricpion=" + libroDescricpion
				+ ", libroFecInsert=" + libroFecInsert + ", libroFecUpdate="
				+ libroFecUpdate + ", libroIdInterno=" + libroIdInterno
				+ ", libroIsbn=" + libroIsbn + ", libroLugar=" + libroLugar
				+ ", libroNombreAutor=" + libroNombreAutor
				+ ", libroNombreEditorial=" + libroNombreEditorial
				+ ", libroNroEdicion=" + libroNroEdicion + ", libroTitulo="
				+ libroTitulo + ", solaTabGenerolibro=" + solaTabGenerolibro
				+ "]";
	}
	
	

}