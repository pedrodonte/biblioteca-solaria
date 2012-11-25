package cl.jsoft.solaria.daos;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import cl.jsoft.solaria.entities.SolaTabLibro;

@Stateless
public class SolaTabLibroDAO extends GenericDAO<SolaTabLibro, Long> {

	public static final String LIBRO_COD_LIBRO = "libroCodLibro";
	public static final String LIBRO_ANIO_EDICION = "libroAnioEdicion";
	public static final String LIBRO_CANT_VOLUMENES = "libroCantVolumenes";
	public static final String LIBRO_DESCRICPION = "libroDescricpion";
	public static final String LIBRO_FEC_INSERT = "libroFecInsert";
	public static final String LIBRO_FEC_UPDATE = "libroFecUpdate";
	public static final String LIBRO_ID_INTERNO = "libroIdInterno";
	public static final String LIBRO_ISBN = "libroIsbn";
	public static final String LIBRO_LUGAR = "libroLugar";
	public static final String LIBRO_NOMBRE_AUTOR = "libroNombreAutor";
	public static final String LIBRO_NOMBRE_EDITORIAL = "libroNombreEditorial";
	public static final String LIBRO_NRO_EDICION = "libroNroEdicion";
	public static final String LIBRO_TITULO = "libroTitulo";
	public static final String SOLA_TAB_GENEROLIBRO = "solaTabGenerolibro";

	public static final String SQL_FILTRAR_POR_ID_INTERNO_LIKE = "select model from SolaTabLibro model where model.libroIdInterno like :libroIdInterno";
	public static final String SQL_FILTRAR_POR_ID_INTERNO_ONE = "select model from SolaTabLibro model where model.libroIdInterno = :libroIdInterno";
	
	public static final String SQL_FILTRAR_POR_TITULO_LIKE = "select model from SolaTabLibro model where lower(model.libroTitulo) like :libroTitulo";
	public static final String SQL_FILTRAR_POR_AUTOR_LIKE = "select model from SolaTabLibro model where lower(model.libroNombreAutor) like :libroNombreAutor";

	public SolaTabLibroDAO() {
		super(SolaTabLibro.class);
	}
	
	private Map<String, Object> parameters = new HashMap<String, Object>();

	public List<SolaTabLibro> buscaRegistrosPorIdInterno(String codigoInterno) {
		try {
			parameters.put(LIBRO_ID_INTERNO, "%" + codigoInterno);
			return super.findManyResult(SQL_FILTRAR_POR_ID_INTERNO_LIKE, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<SolaTabLibro> buscaRegistrosPorTitulo(String titulo) {
		try {
			parameters.put(LIBRO_TITULO, "%" + titulo + "%");
			return super.findManyResult(SQL_FILTRAR_POR_TITULO_LIKE, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<SolaTabLibro> buscaRegistrosPorAutor(String autor) {
		try {
			parameters.put(LIBRO_NOMBRE_AUTOR, "%" + autor + "%");
			return super.findManyResult(SQL_FILTRAR_POR_AUTOR_LIKE, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public SolaTabLibro buscaRegistroPorIdInterno(String codigoInterno) {
		try {
			parameters.put(LIBRO_ID_INTERNO, codigoInterno);
			return super.findOneResult(SQL_FILTRAR_POR_ID_INTERNO_ONE, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SolaTabLibro save(SolaTabLibro entity) {
		try {
			Timestamp libroFecInsert = new Timestamp(new Date().getTime());
			entity.setLibroFecInsert(libroFecInsert);
			return super.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SolaTabLibro update(SolaTabLibro entity) {
		try {
			Timestamp libroFecUpdate = new Timestamp(new Date().getTime());
			entity.setLibroFecUpdate(libroFecUpdate);
			return super.update(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
