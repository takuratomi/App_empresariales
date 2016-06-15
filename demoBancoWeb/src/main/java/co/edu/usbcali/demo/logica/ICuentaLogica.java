package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Cuentas;

public interface ICuentaLogica {

	public String generateCuenta(Long consecutivo);
	public String generateClave(long cedula,String cueNumero);
	
	public Long getConsecutivo()throws Exception;
	
	public void crear(Cuentas cuentas) throws Exception;
	public void modificar(Cuentas cuentas) throws Exception;
	public void borrar(Cuentas cuentas) throws Exception;
	public Cuentas consultarPorId(String cueNumero) throws Exception;
	public List<Cuentas> consultarTodos() throws Exception;
	
}
