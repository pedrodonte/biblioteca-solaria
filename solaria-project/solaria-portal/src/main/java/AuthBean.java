import javax.faces.bean.ManagedBean;


@ManagedBean
public class AuthBean {

	private boolean tieneModuloSegu;
	private boolean tieneModuloCata;
	private boolean tieneModuloGene;
	
	public boolean getTieneModuloSegu() {
		tieneModuloSegu=true;
		return tieneModuloSegu;
	}
	public boolean getTieneModuloCata() {
		tieneModuloCata=false;
		return tieneModuloCata;
	}
	public boolean getTieneModuloGene() {
		tieneModuloGene=false;
		return tieneModuloGene;
	}
	
	


}
