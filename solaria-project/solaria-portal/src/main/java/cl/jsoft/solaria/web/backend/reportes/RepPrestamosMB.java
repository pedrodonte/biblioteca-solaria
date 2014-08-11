package cl.jsoft.solaria.web.backend.reportes;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import cl.jsoft.solaria.dominio.vos.VoEstadisticasPrestamos;
import cl.jsoft.solaria.servicios.PrestamoServicesEJB;

@ManagedBean
@ViewScoped
public class RepPrestamosMB implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	
	@EJB PrestamoServicesEJB prestamoServicesEJB;
	private VoEstadisticasPrestamos estadisticasPrestamos;
	
	@PostConstruct
	public void init(){
		setEstadisticasPrestamos(prestamoServicesEJB.obtenerEstadisticas());
	}

	public VoEstadisticasPrestamos getEstadisticasPrestamos() {
		return estadisticasPrestamos;
	}

	public void setEstadisticasPrestamos(VoEstadisticasPrestamos estadisticasPrestamos) {
		this.estadisticasPrestamos = estadisticasPrestamos;
	}

}
