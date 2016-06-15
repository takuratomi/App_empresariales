package co.edu.usbcali.demo.dto;

import java.math.BigDecimal;


public class CuentaDTO {
	
	private String cueNumero;	
	private BigDecimal cueSaldo;
	private String cueActiva;
	private String cueClave;
	private long cliId;
	
	private String codigoError;
	private String mensajeError;
	
	
	public CuentaDTO() {
	}

	
	
	public CuentaDTO(String cueNumero, BigDecimal cueSaldo, String cueActiva, String cueClave, long cliId,
			String codigoError, String mensajeError) {
		super();
		this.cueNumero = cueNumero;
		this.cueSaldo = cueSaldo;
		this.cueActiva = cueActiva;
		this.cueClave = cueClave;
		this.cliId = cliId;
		this.codigoError = codigoError;
		this.mensajeError = mensajeError;
	}



	public long getCliId() {
		return cliId;
	}

	public void setCliId(long cliId) {
		this.cliId = cliId;
	}
	
	public String getCueNumero() {
		return cueNumero;
	}
	public void setCueNumero(String cueNumero) {
		this.cueNumero = cueNumero;
	}
	public BigDecimal getCueSaldo() {
		return cueSaldo;
	}
	public void setCueSaldo(BigDecimal cueSaldo) {
		this.cueSaldo = cueSaldo;
	}
	public String getCueActiva() {
		return cueActiva;
	}
	public void setCueActiva(String cueActiva) {
		this.cueActiva = cueActiva;
	}
	public String getCueClave() {
		return cueClave;
	}
	public void setCueClave(String cueClave) {
		this.cueClave = cueClave;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	
	

}
