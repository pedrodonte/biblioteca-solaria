package cl.jsoft.solaria.dominio.vos;

/* CLASE - AUTOGENERADA

* FECHA CREACION: Fri Nov 09 16:09:53 CLST 2012 */

/* En caso de cambiar el modelo de datos, esta clase debe ser modificada o volver a generar. */

import cl.jsoft.solaria.entities.SolaTabCliente;
import cl.jsoft.solaria.entities.SolaTabGenerolibro;
import cl.jsoft.solaria.entities.SolaTabGrupocliente;
import cl.jsoft.solaria.entities.SolaTabLibro;
import cl.jsoft.solaria.entities.SolaTabPrestamo;

public class HelperVoEntity{


	/** Transforma un objeto tipo SolaTabCliente en uno nuevo de tipo VoCliente */
	public VoCliente toVO(SolaTabCliente solaTabCliente){
		VoCliente voCliente = new VoCliente();
		try{ 
			voCliente.setClienteCodCliente(solaTabCliente.getClienteCodCliente());
			voCliente.setClienteApellidos(solaTabCliente.getClienteApellidos());
			voCliente.setClienteDireccion(solaTabCliente.getClienteDireccion());
			voCliente.setClienteFecInsert(solaTabCliente.getClienteFecInsert());
			voCliente.setClienteFecNacimiento(solaTabCliente.getClienteFecNacimiento());
			voCliente.setClienteFecUpdate(solaTabCliente.getClienteFecUpdate());
			voCliente.setClienteIdentificador(solaTabCliente.getClienteIdentificador());
			voCliente.setClienteImg(solaTabCliente.getClienteImg());
			voCliente.setClienteNombres(solaTabCliente.getClienteNombres());
			voCliente.setVoGrupocliente( toVO(solaTabCliente.getSolaTabGrupocliente()) );
			
			voCliente.setNombreCompleto(solaTabCliente.getClienteNombres()+" "+solaTabCliente.getClienteApellidos());
			voCliente.setSelectOneMenu();
			
		}catch(java.lang.NullPointerException e){/*seteando valores nulos, es normal.*/} 
		return voCliente;
	}

	/** Transforma un objeto tipo VoCliente en uno nuevo de tipo SolaTabCliente */
	public SolaTabCliente toEntity(VoCliente voCliente){
		SolaTabCliente solaTabCliente = new SolaTabCliente();
		solaTabCliente.setClienteCodCliente(voCliente.getClienteCodCliente());
		solaTabCliente.setClienteApellidos(voCliente.getClienteApellidos());
		solaTabCliente.setClienteDireccion(voCliente.getClienteDireccion());
		solaTabCliente.setClienteFecInsert(voCliente.getClienteFecInsert());
		solaTabCliente.setClienteFecNacimiento(voCliente.getClienteFecNacimiento());
		solaTabCliente.setClienteFecUpdate(voCliente.getClienteFecUpdate());
		solaTabCliente.setClienteIdentificador(voCliente.getClienteIdentificador());
		solaTabCliente.setClienteImg(voCliente.getClienteImg());
		solaTabCliente.setClienteNombres(voCliente.getClienteNombres());
		solaTabCliente.setSolaTabGrupocliente( toEntity(voCliente.getVoGrupocliente()) );
		return solaTabCliente;
	}

	/** Transforma un objeto tipo SolaTabGenerolibro en uno nuevo de tipo VoGenerolibro */
	public VoGenerolibro toVO(SolaTabGenerolibro solaTabGenerolibro){
		VoGenerolibro voGenerolibro = new VoGenerolibro();
		try{ 
			voGenerolibro.setGenerolibroCodGenerolibro(solaTabGenerolibro.getGenerolibroCodGenerolibro());
			voGenerolibro.setGenerolibroNombre(solaTabGenerolibro.getGenerolibroNombre());
		}catch(java.lang.NullPointerException e){/*seteando valores nulos, es normal.*/} 
		return voGenerolibro;
	}

	/** Transforma un objeto tipo VoGenerolibro en uno nuevo de tipo SolaTabGenerolibro */
	public SolaTabGenerolibro toEntity(VoGenerolibro voGenerolibro){
		SolaTabGenerolibro solaTabGenerolibro = new SolaTabGenerolibro();
		solaTabGenerolibro.setGenerolibroCodGenerolibro(voGenerolibro.getGenerolibroCodGenerolibro());
		solaTabGenerolibro.setGenerolibroNombre(voGenerolibro.getGenerolibroNombre());
		return solaTabGenerolibro;
	}

	/** Transforma un objeto tipo SolaTabGrupocliente en uno nuevo de tipo VoGrupocliente */
	public VoGrupocliente toVO(SolaTabGrupocliente solaTabGrupocliente){
		VoGrupocliente voGrupocliente = new VoGrupocliente();
		try{ 
			voGrupocliente.setGrupoclienteCodGrupocliente(solaTabGrupocliente.getGrupoclienteCodGrupocliente());
			voGrupocliente.setGrupoclienteNombre(solaTabGrupocliente.getGrupoclienteNombre());
		}catch(java.lang.NullPointerException e){/*seteando valores nulos, es normal.*/} 
		return voGrupocliente;
	}

	/** Transforma un objeto tipo VoGrupocliente en uno nuevo de tipo SolaTabGrupocliente */
	public SolaTabGrupocliente toEntity(VoGrupocliente voGrupocliente){
		SolaTabGrupocliente solaTabGrupocliente = new SolaTabGrupocliente();
		solaTabGrupocliente.setGrupoclienteCodGrupocliente(voGrupocliente.getGrupoclienteCodGrupocliente());
		solaTabGrupocliente.setGrupoclienteNombre(voGrupocliente.getGrupoclienteNombre());
		return solaTabGrupocliente;
	}

	/** Transforma un objeto tipo SolaTabLibro en uno nuevo de tipo VoLibro */
	public VoLibro toVO(SolaTabLibro solaTabLibro){
		VoLibro voLibro = new VoLibro();
		try{ 
			voLibro.setLibroCodLibro(solaTabLibro.getLibroCodLibro());
			voLibro.setLibroAnioEdicion(solaTabLibro.getLibroAnioEdicion());
			voLibro.setLibroCantVolumenes(solaTabLibro.getLibroCantVolumenes());
			voLibro.setLibroDescricpion(solaTabLibro.getLibroDescricpion());
			voLibro.setLibroFecInsert(solaTabLibro.getLibroFecInsert());
			voLibro.setLibroFecUpdate(solaTabLibro.getLibroFecUpdate());
			voLibro.setLibroIdInterno(solaTabLibro.getLibroIdInterno());
			voLibro.setLibroIsbn(solaTabLibro.getLibroIsbn());
			voLibro.setLibroLugar(solaTabLibro.getLibroLugar());
			voLibro.setLibroNombreAutor(solaTabLibro.getLibroNombreAutor());
			voLibro.setLibroNombreEditorial(solaTabLibro.getLibroNombreEditorial());
			voLibro.setLibroNroEdicion(solaTabLibro.getLibroNroEdicion());
			voLibro.setLibroTitulo(solaTabLibro.getLibroTitulo());
			voLibro.setVoGenerolibro( toVO(solaTabLibro.getSolaTabGenerolibro()) );
		}catch(java.lang.NullPointerException e){/*seteando valores nulos, es normal.*/} 
		return voLibro;
	}

	/** Transforma un objeto tipo VoLibro en uno nuevo de tipo SolaTabLibro */
	public SolaTabLibro toEntity(VoLibro voLibro){
		SolaTabLibro solaTabLibro = new SolaTabLibro();
		solaTabLibro.setLibroCodLibro(voLibro.getLibroCodLibro());
		solaTabLibro.setLibroAnioEdicion(voLibro.getLibroAnioEdicion());
		solaTabLibro.setLibroCantVolumenes(voLibro.getLibroCantVolumenes());
		solaTabLibro.setLibroDescricpion(voLibro.getLibroDescricpion());
		solaTabLibro.setLibroFecInsert(voLibro.getLibroFecInsert());
		solaTabLibro.setLibroFecUpdate(voLibro.getLibroFecUpdate());
		solaTabLibro.setLibroIdInterno(voLibro.getLibroIdInterno());
		solaTabLibro.setLibroIsbn(voLibro.getLibroIsbn());
		solaTabLibro.setLibroLugar(voLibro.getLibroLugar());
		solaTabLibro.setLibroNombreAutor(voLibro.getLibroNombreAutor());
		solaTabLibro.setLibroNombreEditorial(voLibro.getLibroNombreEditorial());
		solaTabLibro.setLibroNroEdicion(voLibro.getLibroNroEdicion());
		solaTabLibro.setLibroTitulo(voLibro.getLibroTitulo());
		solaTabLibro.setSolaTabGenerolibro( toEntity(voLibro.getVoGenerolibro()) );
		return solaTabLibro;
	}

	/** Transforma un objeto tipo SolaTabPrestamo en uno nuevo de tipo VoPrestamo */
	public VoPrestamo toVO(SolaTabPrestamo solaTabPrestamo){
		VoPrestamo voPrestamo = new VoPrestamo();
		try{ 
			voPrestamo.setPrestamoCodPrestamo(solaTabPrestamo.getPrestamoCodPrestamo());
			voPrestamo.setPrestamoCodEstado(solaTabPrestamo.getPrestamoCodEstado());
			voPrestamo.setPrestamoFecDevReal(solaTabPrestamo.getPrestamoFecDevReal());
			voPrestamo.setPrestamoFecInicio(solaTabPrestamo.getPrestamoFecInicio());
			voPrestamo.setPrestamoFecInsert(solaTabPrestamo.getPrestamoFecInsert());
			voPrestamo.setPrestamoFecPlazoEntrega(solaTabPrestamo.getPrestamoFecPlazoEntrega());
			voPrestamo.setPrestamoFecUpdate(solaTabPrestamo.getPrestamoFecUpdate());
			voPrestamo.setVoCliente( toVO(solaTabPrestamo.getSolaTabCliente()) );
			voPrestamo.setVoLibro( toVO(solaTabPrestamo.getSolaTabLibro()) );
		}catch(java.lang.NullPointerException e){/*seteando valores nulos, es normal.*/} 
		return voPrestamo;
	}

	/** Transforma un objeto tipo VoPrestamo en uno nuevo de tipo SolaTabPrestamo */
	public SolaTabPrestamo toEntity(VoPrestamo voPrestamo){
		SolaTabPrestamo solaTabPrestamo = new SolaTabPrestamo();
		solaTabPrestamo.setPrestamoCodPrestamo(voPrestamo.getPrestamoCodPrestamo());
		solaTabPrestamo.setPrestamoCodEstado(voPrestamo.getPrestamoCodEstado());
		solaTabPrestamo.setPrestamoFecDevReal(voPrestamo.getPrestamoFecDevReal());
		solaTabPrestamo.setPrestamoFecInicio(voPrestamo.getPrestamoFecInicio());
		solaTabPrestamo.setPrestamoFecInsert(voPrestamo.getPrestamoFecInsert());
		solaTabPrestamo.setPrestamoFecPlazoEntrega(voPrestamo.getPrestamoFecPlazoEntrega());
		solaTabPrestamo.setPrestamoFecUpdate(voPrestamo.getPrestamoFecUpdate());
		solaTabPrestamo.setSolaTabCliente( toEntity(voPrestamo.getVoCliente()) );
		solaTabPrestamo.setSolaTabLibro( toEntity(voPrestamo.getVoLibro()) );
		return solaTabPrestamo;
	}

}
