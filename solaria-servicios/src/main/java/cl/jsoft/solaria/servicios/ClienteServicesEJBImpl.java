package cl.jsoft.solaria.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.jsoft.solaria.daos.SolaTabClienteDAO;
import cl.jsoft.solaria.daos.SolaTabGrupoclienteDAO;
import cl.jsoft.solaria.dominio.vos.TransformadorDominio;
import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoGrupocliente;
import cl.jsoft.solaria.entities.SolaTabCliente;
import cl.jsoft.solaria.entities.SolaTabGrupocliente;
import cl.jsoft.solaria.excepciones.ErrorDelSistemaException;
import cl.jsoft.solaria.excepciones.RegistrosNoEncontradosException;
import cl.jsoft.solaria.jasper.GeneradorReporteEJB;

@Stateless
public class ClienteServicesEJBImpl implements ClienteServicesEJB {
	
	Logger logger = Logger.getLogger(getClass());
	
	TransformadorDominio helperVoEntity = new TransformadorDominio();

	@EJB SolaTabClienteDAO clienteDAO;
	
	@EJB SolaTabGrupoclienteDAO grupoclienteDAO;

	@Override
	public VoCliente buscarClientePorIdentificador(String nroRun)
			throws RegistrosNoEncontradosException,
			ErrorDelSistemaException {
		VoCliente respCliente = null;
		try {
			BigDecimal runCliente = getRUNChileno(nroRun);
			SolaTabCliente eCliente = clienteDAO.buscaRegistroPorIdentificador(runCliente);
			System.out.println(eCliente);
			if (eCliente != null) {
				respCliente = helperVoEntity.toVO(eCliente);
			}else{
				throw new RegistrosNoEncontradosException("No existe un registro para el parametro:[ "+nroRun+" ]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegistrosNoEncontradosException("El registro no pudo ser encontrado debido a un error.");
		}
		logger.debug(respCliente);
		return respCliente;
	}

	private BigDecimal getRUNChileno(String nroRun) {
		String[] runDV = nroRun.split("-");
		return new BigDecimal(runDV[0]);
	}

	@Override
	public List<VoCliente> buscarTodosLosClientes()	throws RegistrosNoEncontradosException, ErrorDelSistemaException {
		List<VoCliente> voItems = new ArrayList<VoCliente>();
		
		List<SolaTabCliente> resultadoBusqueda = clienteDAO.findAll();
		
		if(resultadoBusqueda.size() <= 0){
			throw new RegistrosNoEncontradosException("Sin resultados.");
		}
		
		logger.debug(resultadoBusqueda.size()+" Registros encontrados");
		
		for(SolaTabCliente entity : resultadoBusqueda){
			voItems.add(helperVoEntity.toVO(entity));
		}
		
		return voItems;
	}

	@Override
	public List<VoCliente> buscarClientesPorNombresApellidos(String nombres,
			String apellidos) throws RegistrosNoEncontradosException,
			ErrorDelSistemaException {
		List<VoCliente> voItems = new ArrayList<VoCliente>();
		 
		try {
			List<SolaTabCliente> resultadoBusqueda = clienteDAO.buscaRegistroPorNombreApellido(nombres, apellidos);
			
			if(resultadoBusqueda.size() <= 0){
				throw new RegistrosNoEncontradosException("Sin resultados para los parametros [ "+nombres+" , "+apellidos+" ]");
			}
			
			for(SolaTabCliente solaTabCliente : resultadoBusqueda){
				voItems.add(helperVoEntity.toVO(solaTabCliente));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorDelSistemaException("Ocurrio un error al ejecutar el servicio buscarClientesPorNombresApellidos[ "+nombres+" , "+apellidos+" ]" );
		}
		return voItems;
	}

	@Override
	public List<VoGrupocliente> buscarTodosGruposCliente()	throws RegistrosNoEncontradosException, ErrorDelSistemaException {
		try {
			List<VoGrupocliente> voItems = new ArrayList<VoGrupocliente>();
			
			List<SolaTabGrupocliente> resultadoBusqueda = grupoclienteDAO.findAll();
			
			if(resultadoBusqueda.size() <= 0){
				throw new RegistrosNoEncontradosException("Sin resultados.");
			}
			
			logger.debug(resultadoBusqueda.size()+" Registros encontrados");
			
			for(SolaTabGrupocliente entity : resultadoBusqueda){
				voItems.add(helperVoEntity.toVO(entity));
			}
			
			return voItems;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorDelSistemaException("Ocurrio un Error al ejecutar el servicio buscarTodosGruposCliente()");
		}
	}

	@Override
	public List<VoCliente> buscarClientesPorGrupo(VoGrupocliente grupo)	throws RegistrosNoEncontradosException, ErrorDelSistemaException {
		
		return null;
	}
	
	@EJB GeneradorReporteEJB reporteEJB;
	@Override
	public String generarCredenciales(long codigoGrupoCliente) throws RegistrosNoEncontradosException, ErrorDelSistemaException {
		String nombreArchivo = reporteEJB.generarCredencialesBiblioteca(codigoGrupoCliente);
		logger.debug("Reporte Generado: "+nombreArchivo);
		return nombreArchivo;
	}

	

}
