package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;

public interface ITransacciones {
	
	public BigDecimal consignar(String cueNumero,BigDecimal valor,Long usuCedula,String descripcion)throws Exception;
	public BigDecimal retirar(String cueNumero,BigDecimal valor,Long usuCedula,String descripcion)throws Exception;	
	public BigDecimal traslado(String cueOrigen,String cueDestino,BigDecimal valor,Long usuCedula,String descripcion)throws Exception;

}
