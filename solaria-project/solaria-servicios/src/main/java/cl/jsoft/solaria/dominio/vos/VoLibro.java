package cl.jsoft.solaria.dominio.vos;


/* CLASE - AUTOGENERADA
 * FECHA CREACION: Fri Nov 09 16:09:53 CLST 2012 */
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.io.Serializable;

public class VoLibro implements Serializable{

	private static final long serialVersionUID = 1352488193042L;
	private long libroCodLibro;
	private BigDecimal libroAnioEdicion;
	private BigDecimal libroCantVolumenes;
	private String libroDescricpion;
	private Timestamp libroFecInsert;
	private Timestamp libroFecUpdate;
	private String libroIdInterno;
	private BigDecimal libroIsbn;
	private String libroLugar;
	private String libroNombreAutor;
	private String libroNombreEditorial;
	private String libroNroEdicion;
	private String libroTitulo;
	private VoGenerolibro voGenerolibro;

	public VoLibro(){
	}
	public void setLibroCodLibro(long libroCodLibro){
		this.libroCodLibro=libroCodLibro;
	}
	public long getLibroCodLibro(){
		return this.libroCodLibro;
	}
	public void setLibroAnioEdicion(BigDecimal libroAnioEdicion){
		this.libroAnioEdicion=libroAnioEdicion;
	}
	public BigDecimal getLibroAnioEdicion(){
		return this.libroAnioEdicion;
	}
	public void setLibroCantVolumenes(BigDecimal libroCantVolumenes){
		this.libroCantVolumenes=libroCantVolumenes;
	}
	public BigDecimal getLibroCantVolumenes(){
		return this.libroCantVolumenes;
	}
	public void setLibroDescricpion(String libroDescricpion){
		this.libroDescricpion=libroDescricpion;
	}
	public String getLibroDescricpion(){
		return this.libroDescricpion;
	}
	public void setLibroFecInsert(Timestamp libroFecInsert){
		this.libroFecInsert=libroFecInsert;
	}
	public Timestamp getLibroFecInsert(){
		return this.libroFecInsert;
	}
	public void setLibroFecUpdate(Timestamp libroFecUpdate){
		this.libroFecUpdate=libroFecUpdate;
	}
	public Timestamp getLibroFecUpdate(){
		return this.libroFecUpdate;
	}
	public void setLibroIdInterno(String libroIdInterno){
		this.libroIdInterno=libroIdInterno;
	}
	public String getLibroIdInterno(){
		return this.libroIdInterno;
	}
	public void setLibroIsbn(BigDecimal libroIsbn){
		this.libroIsbn=libroIsbn;
	}
	public BigDecimal getLibroIsbn(){
		return this.libroIsbn;
	}
	public void setLibroLugar(String libroLugar){
		this.libroLugar=libroLugar;
	}
	public String getLibroLugar(){
		return this.libroLugar;
	}
	public void setLibroNombreAutor(String libroNombreAutor){
		this.libroNombreAutor=libroNombreAutor;
	}
	public String getLibroNombreAutor(){
		return this.libroNombreAutor;
	}
	public void setLibroNombreEditorial(String libroNombreEditorial){
		this.libroNombreEditorial=libroNombreEditorial;
	}
	public String getLibroNombreEditorial(){
		return this.libroNombreEditorial;
	}
	public void setLibroNroEdicion(String libroNroEdicion){
		this.libroNroEdicion=libroNroEdicion;
	}
	public String getLibroNroEdicion(){
		return this.libroNroEdicion;
	}
	public void setLibroTitulo(String libroTitulo){
		this.libroTitulo=libroTitulo;
	}
	public String getLibroTitulo(){
		return this.libroTitulo;
	}
	public void setVoGenerolibro(VoGenerolibro voGenerolibro){
		this.voGenerolibro=voGenerolibro;
	}
	public VoGenerolibro getVoGenerolibro(){
		return this.voGenerolibro;
	}
	@Override
	public String toString(){
		 return "VoLibro[libroCodLibro="+libroCodLibro
		 +",libroAnioEdicion="+libroAnioEdicion
		 +",libroCantVolumenes="+libroCantVolumenes
		 +",libroDescricpion="+libroDescricpion
		 +",libroFecInsert="+libroFecInsert
		 +",libroFecUpdate="+libroFecUpdate
		 +",libroIdInterno="+libroIdInterno
		 +",libroIsbn="+libroIsbn
		 +",libroLugar="+libroLugar
		 +",libroNombreAutor="+libroNombreAutor
		 +",libroNombreEditorial="+libroNombreEditorial
		 +",libroNroEdicion="+libroNroEdicion
		 +",libroTitulo="+libroTitulo
		 +",voGenerolibro="+voGenerolibro+"]";
	}

}
