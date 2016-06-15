package co.edu.usbcali.demo.dto;

public class ClienteDTO {
	
	private long cliId;
	private long tiposDocumentos;
	private String cliNombre;
	private String cliDireccion;
	private String cliTelefono;
	private String cliMail;
	
	private String codigoError;
	private String mensajeError;
	
	public ClienteDTO(){
		
	}	

	public ClienteDTO(long cliId, long tiposDocumentos, String cliNombre, String cliDireccion, String cliTelefono,
			String cliMail, String codigoError, String mensajeError) {
		super();
		this.cliId = cliId;
		this.tiposDocumentos = tiposDocumentos;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
		this.cliMail = cliMail;
		this.codigoError = codigoError;
		this.mensajeError = mensajeError;
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

	public long getCliId() {
		return cliId;
	}
	public void setCliId(long cliId) {
		this.cliId = cliId;
	}
	public long getTiposDocumentos() {
		return tiposDocumentos;
	}
	public void setTiposDocumentos(long tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}
	public String getCliNombre() {
		return cliNombre;
	}
	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}
	public String getCliDireccion() {
		return cliDireccion;
	}
	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}
	public String getCliTelefono() {
		return cliTelefono;
	}
	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}
	public String getCliMail() {
		return cliMail;
	}
	public void setCliMail(String cliMail) {
		this.cliMail = cliMail;
	}
	
	

}
