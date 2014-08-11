package cl.jsoft.solaria.dominio.vos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VoPrestamoPlus  implements Serializable, Cloneable {
	
	private long diasAtraso;

	public long getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(long diasAtraso) {
		this.diasAtraso = diasAtraso;
	}
	
	

}
