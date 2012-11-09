package cl.jsoft.solaria.servicios;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.dominio.vos.VoGrupocliente;

public interface ClienteServicesEJB {
	
	public VoCliente buscarClientePorNumeroRun(String nroRun);
	
	public VoCliente buscarClientesPorGrupo(VoGrupocliente grupo);
	
	public VoCliente buscarTodosLosClientes(VoGrupocliente grupo);

}
