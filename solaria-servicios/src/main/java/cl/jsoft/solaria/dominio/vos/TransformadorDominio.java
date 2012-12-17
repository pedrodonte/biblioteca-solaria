package cl.jsoft.solaria.dominio.vos;

import cl.jsoft.solaria.entities.SolaTabCliente;
import cl.jsoft.solaria.entities.SolaTabGenerolibro;
import cl.jsoft.solaria.entities.SolaTabGrupocliente;
import cl.jsoft.solaria.entities.SolaTabLibro;
import cl.jsoft.solaria.entities.SolaTabPrestamo;

public class TransformadorDominio extends HelperVoEntity{

	@Override
	public VoCliente toVO(SolaTabCliente solaTabCliente) {
		VoCliente voCliente = super.toVO(solaTabCliente);
		voCliente.setNombreCompleto(solaTabCliente.getClienteNombres()+" "+solaTabCliente.getClienteApellidos());
		voCliente.setSelectOneMenu();
		return voCliente;
	}

	@Override
	public SolaTabCliente toEntity(VoCliente voCliente) {
		// TODO Auto-generated method stub
		return super.toEntity(voCliente);
	}

	@Override
	public VoGenerolibro toVO(SolaTabGenerolibro solaTabGenerolibro) {
		// TODO Auto-generated method stub
		return super.toVO(solaTabGenerolibro);
	}

	@Override
	public SolaTabGenerolibro toEntity(VoGenerolibro voGenerolibro) {
		// TODO Auto-generated method stub
		return super.toEntity(voGenerolibro);
	}

	@Override
	public VoGrupocliente toVO(SolaTabGrupocliente solaTabGrupocliente) {
		// TODO Auto-generated method stub
		return super.toVO(solaTabGrupocliente);
	}

	@Override
	public SolaTabGrupocliente toEntity(VoGrupocliente voGrupocliente) {
		// TODO Auto-generated method stub
		return super.toEntity(voGrupocliente);
	}

	@Override
	public VoLibro toVO(SolaTabLibro solaTabLibro) {
		// TODO Auto-generated method stub
		return super.toVO(solaTabLibro);
	}

	@Override
	public SolaTabLibro toEntity(VoLibro voLibro) {
		// TODO Auto-generated method stub
		return super.toEntity(voLibro);
	}

	@Override
	public VoPrestamo toVO(SolaTabPrestamo solaTabPrestamo) {
		// TODO Auto-generated method stub
		return super.toVO(solaTabPrestamo);
	}

	@Override
	public SolaTabPrestamo toEntity(VoPrestamo voPrestamo) {
		// TODO Auto-generated method stub
		return super.toEntity(voPrestamo);
	}
	
}
