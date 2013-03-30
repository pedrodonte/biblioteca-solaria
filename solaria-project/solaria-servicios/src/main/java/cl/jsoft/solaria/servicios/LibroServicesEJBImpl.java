package cl.jsoft.solaria.servicios;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.daos.SolaTabLibroDAO;
import cl.jsoft.solaria.dominio.vos.HelperVoEntity;
import cl.jsoft.solaria.dominio.vos.VoLibro;
import cl.jsoft.solaria.entities.SolaTabLibro;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;

@Stateless
public class LibroServicesEJBImpl implements LibroServicesEJB {
	
	private Logger logger = Logger.getLogger(getClass());
	
	HelperVoEntity helperVoEntity = new HelperVoEntity();
	private final String FORMATO_NRO_REGISTRO = "000000";

	@EJB SolaTabLibroDAO libroDAO;

	@Override
	public VoLibro buscarLibroCodigoInterno(String codigoInterno)
			throws RegistrosNoEncontradosException {
		VoLibro respLibro = null;
		try {
			codigoInterno = normalizarCodigoLibro(codigoInterno);
			logger.debug("buscando libro por:"+codigoInterno);
			SolaTabLibro eLibro = libroDAO.buscaRegistroPorIdInterno(codigoInterno);
			if (eLibro != null) {
				respLibro = helperVoEntity.toVO(eLibro);
			}else{
				throw new RegistrosNoEncontradosException("No existe un registro para el parametro:[ "+codigoInterno+" ]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegistrosNoEncontradosException("El registro no pudo ser encontrado debido a un error.");
		}
		return respLibro;
	}

	private String normalizarCodigoLibro(String codigoInterno) {
		logger.debug("normalizarCodigoLibro "+codigoInterno);
		
		codigoInterno = codigoInterno.replace(" ", "");
		codigoInterno = codigoInterno.replace("\\.", "");
		NumberFormat nf = new DecimalFormat(FORMATO_NRO_REGISTRO);
		codigoInterno = nf.format(Integer.parseInt(codigoInterno));
		
		return codigoInterno;
	}

	@Override
	public List<VoLibro> buscarLibrosPorAutor(String autor)
			throws RegistrosNoEncontradosException {
		List<VoLibro> respLibros = new ArrayList<VoLibro>();
		try {
			List<SolaTabLibro> eLibros = libroDAO.buscaRegistrosPorAutor(autor);
			if (eLibros.size() > 0) {
				for (SolaTabLibro eLibro : eLibros) {
					respLibros.add( helperVoEntity.toVO(eLibro) );
				}
			}else{
				throw new RegistrosNoEncontradosException("No existen registros para el parametro:[ "+autor+" ]");
			}
		}catch(NullPointerException npe){
			throw new RegistrosNoEncontradosException("El registro no pudo ser encontrado debido a un error, NullPointerException");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RegistrosNoEncontradosException("El registro no pudo ser encontrado debido a un error.");
		}
		return respLibros;
	}

	@Override
	public List<VoLibro> buscarLibrosPorCodigoInterno(String codigoInterno)
			throws RegistrosNoEncontradosException {
		List<VoLibro> respLibros = new ArrayList<VoLibro>();
		try {
			List<SolaTabLibro> eLibros = libroDAO.buscaRegistrosPorIdInterno(codigoInterno);
			if (eLibros.size() > 0) {
				for (SolaTabLibro eLibro : eLibros) {
					respLibros.add( helperVoEntity.toVO(eLibro) );
				}
			}else{
				throw new RegistrosNoEncontradosException("No existen registros para el parametro:[ "+codigoInterno+" ]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegistrosNoEncontradosException("El registro no pudo ser encontrado debido a un error.");
		}
		return respLibros;
	}

	@Override
	public List<VoLibro> buscarLibrosPorTitulo(String titulo)
			throws RegistrosNoEncontradosException {
		List<VoLibro> respLibros = new ArrayList<VoLibro>();
		try {
			List<SolaTabLibro> eLibros = libroDAO.buscaRegistrosPorTitulo(titulo);
			if (eLibros.size() > 0) {
				for (SolaTabLibro eLibro : eLibros) {
					respLibros.add( helperVoEntity.toVO(eLibro) );
				}
			}else{
				throw new RegistrosNoEncontradosException("No existen registros para el parametro:[ "+titulo+" ]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegistrosNoEncontradosException("El registro no pudo ser encontrado debido a un error.");
		}
		return respLibros;
	}
	

	@Override
	public boolean validarDisponibilidadLibro(VoLibro voLibro) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
