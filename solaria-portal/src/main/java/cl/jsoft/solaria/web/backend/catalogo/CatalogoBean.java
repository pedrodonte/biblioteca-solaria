package cl.jsoft.solaria.web.backend.catalogo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.servicios.LibroServicesEJB;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean
@ViewScoped
public class CatalogoBean {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private static final int AUTOR  = 1;
	private static final int TITULO = 2;
	private static final int CODIGO = 3;
	
	@EJB
	private LibroServicesEJB libroServicesEJB;
	
	private int buscarLibroPor = TITULO;
	
	@ManagedProperty(value="#{mensajesBean}")
	private MensajesBean mensajesBean;
	
	private String textoBusqueda;
	
	private List<VoLibro> libros = new ArrayList<VoLibro>();
	private VoLibro libroSeleccionado = new VoLibro(); // #{catalogoBean.libroSeleccionado}
	
	public void doBuscarLibro(ActionEvent actionEvent) {
		
		logger.debug("Buscando por "+buscarLibroPor+", "+textoBusqueda);
		
		try {
			
			switch(buscarLibroPor){
			case AUTOR:
				libros = libroServicesEJB.buscarLibrosPorAutor(textoBusqueda);
				break;
			case TITULO:
				libros = libroServicesEJB.buscarLibrosPorTitulo(textoBusqueda);
				break;
			case CODIGO:
				libros = libroServicesEJB.buscarLibrosPorCodigoInterno(textoBusqueda);
				break;
			default:
				mensajesBean.msgWarn("Revice los datos ingresados para la búsqueda e intente nuevamente.");
				break;
			}
			logger.debug(libros.size()+" registros encontrados");
			mensajesBean.msgInfo(libros.size()+" registros encontrados");
		} catch (RegistrosNoEncontradosException e) {
			mensajesBean.msgWarn("Búsqueda sin resultados.");
		}
		
	}
	
	@PostConstruct private void inicializar(){}
	

	public List<VoLibro> getLibros() {
		
		return libros;
	}

	public void setLibros(List<VoLibro> libros) {
		
		this.libros = libros;
	}


	public int getBuscarLibroPor() {
		return buscarLibroPor;
	}


	public void setBuscarLibroPor(int buscarLibroPor) {
		this.buscarLibroPor = buscarLibroPor;
	}


	public String getTextoBusqueda() {
		return textoBusqueda;
	}


	public void setTextoBusqueda(String textoBusqueda) {
		this.textoBusqueda = textoBusqueda;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public VoLibro getLibroSeleccionado() {
		return libroSeleccionado;
	}

	public void setLibroSeleccionado(VoLibro libroSeleccionado) {
		logger.debug("Selección :"+libroSeleccionado);
		this.libroSeleccionado = libroSeleccionado;
	}
	
	

}
