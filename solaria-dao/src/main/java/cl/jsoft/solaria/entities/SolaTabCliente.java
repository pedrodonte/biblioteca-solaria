package cl.jsoft.solaria.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the sola_tab_cliente database table.
 * 
 */
@Entity
@Table(schema="sola",name="sola_tab_cliente")
public class SolaTabCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOLA_TAB_CLIENTE_CLIENTECODCLIENTE_GENERATOR", allocationSize=1, sequenceName="SOLA.SOLA_SEQ_CLIENTE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLA_TAB_CLIENTE_CLIENTECODCLIENTE_GENERATOR")
	@Column(name="cliente_cod_cliente")
	private long clienteCodCliente;

	@Column(name="cliente_apellidos")
	private String clienteApellidos;

	@Column(name="cliente_direccion")
	private String clienteDireccion;

	@Temporal(TemporalType.DATE)
	@Column(name="cliente_fec_insert")
	private Date clienteFecInsert;

	@Temporal(TemporalType.DATE)
	@Column(name="cliente_fec_nacimiento")
	private Date clienteFecNacimiento;

	@Temporal(TemporalType.DATE)
	@Column(name="cliente_fec_update")
	private Date clienteFecUpdate;

	@Column(name="cliente_identificador")
	private BigDecimal clienteIdentificador;

	@Column(name="cliente_img")
	private String clienteImg;

	@Column(name="cliente_nombres")
	private String clienteNombres;

	//uni-directional many-to-one association to SolaTabGrupocliente
	@ManyToOne
	@JoinColumn(name="grupocliente_cod_grupocliente")
	private SolaTabGrupocliente solaTabGrupocliente;

	public SolaTabCliente() {
	}

	public long getClienteCodCliente() {
		return this.clienteCodCliente;
	}

	public void setClienteCodCliente(long clienteCodCliente) {
		this.clienteCodCliente = clienteCodCliente;
	}

	public String getClienteApellidos() {
		return this.clienteApellidos;
	}

	public void setClienteApellidos(String clienteApellidos) {
		this.clienteApellidos = clienteApellidos;
	}

	public String getClienteDireccion() {
		return this.clienteDireccion;
	}

	public void setClienteDireccion(String clienteDireccion) {
		this.clienteDireccion = clienteDireccion;
	}

	public Date getClienteFecInsert() {
		return this.clienteFecInsert;
	}

	public void setClienteFecInsert(Date clienteFecInsert) {
		this.clienteFecInsert = clienteFecInsert;
	}

	public Date getClienteFecNacimiento() {
		return this.clienteFecNacimiento;
	}

	public void setClienteFecNacimiento(Date clienteFecNacimiento) {
		this.clienteFecNacimiento = clienteFecNacimiento;
	}

	public Date getClienteFecUpdate() {
		return this.clienteFecUpdate;
	}

	public void setClienteFecUpdate(Date clienteFecUpdate) {
		this.clienteFecUpdate = clienteFecUpdate;
	}

	public BigDecimal getClienteIdentificador() {
		return this.clienteIdentificador;
	}

	public void setClienteIdentificador(BigDecimal clienteIdentificador) {
		this.clienteIdentificador = clienteIdentificador;
	}

	public String getClienteImg() {
		return this.clienteImg;
	}

	public void setClienteImg(String clienteImg) {
		this.clienteImg = clienteImg;
	}

	public String getClienteNombres() {
		return this.clienteNombres;
	}

	public void setClienteNombres(String clienteNombres) {
		this.clienteNombres = clienteNombres;
	}

	public SolaTabGrupocliente getSolaTabGrupocliente() {
		return this.solaTabGrupocliente;
	}

	public void setSolaTabGrupocliente(SolaTabGrupocliente solaTabGrupocliente) {
		this.solaTabGrupocliente = solaTabGrupocliente;
	}

	@Override
	public String toString() {
		return "SolaTabCliente [clienteCodCliente=" + clienteCodCliente
				+ ", clienteApellidos=" + clienteApellidos
				+ ", clienteDireccion=" + clienteDireccion
				+ ", clienteFecInsert=" + clienteFecInsert
				+ ", clienteFecNacimiento=" + clienteFecNacimiento
				+ ", clienteFecUpdate=" + clienteFecUpdate
				+ ", clienteIdentificador=" + clienteIdentificador
				+ ", clienteImg=" + clienteImg + ", clienteNombres="
				+ clienteNombres + ", solaTabGrupocliente="
				+ solaTabGrupocliente + "]";
	}
	
	

}