package co.edu.usbcali.demo.dto;

public class ClienteCuentaDTO {
	
	private java.lang.Long			cliId;
	private java.lang.String		cliNombre;
	private java.lang.String		cueNumero;
	private java.math.BigDecimal	cueSaldo;
	private java.lang.String		cueActiva;
	
	
	public java.lang.Long getCliId() {
		return cliId;
	}
	public void setCliId(java.lang.Long cliId) {
		this.cliId = cliId;
	}
	public java.lang.String getCliNombre() {
		return cliNombre;
	}
	public void setCliNombre(java.lang.String cliNombre) {
		this.cliNombre = cliNombre;
	}
	public java.lang.String getCueNumero() {
		return cueNumero;
	}
	public void setCueNumero(java.lang.String cueNumero) {
		this.cueNumero = cueNumero;
	}
	public java.math.BigDecimal getCueSaldo() {
		return cueSaldo;
	}
	public void setCueSaldo(java.math.BigDecimal cueSaldo) {
		this.cueSaldo = cueSaldo;
	}
	public java.lang.String getCueActiva() {
		return cueActiva;
	}
	public void setCueActiva(java.lang.String cueActiva) {
		this.cueActiva = cueActiva;
	}
	
	
}
