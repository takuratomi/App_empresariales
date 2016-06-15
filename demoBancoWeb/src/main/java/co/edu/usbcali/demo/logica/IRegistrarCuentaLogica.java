package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.RegistrarCuenta;

public interface IRegistrarCuentaLogica {
	
	public Long getConsecutivo()throws Exception;
	public void crear(RegistrarCuenta registrarCuenta) throws Exception;
	public void modificar(RegistrarCuenta registrarCuenta)throws Exception;
	public void borrar(RegistrarCuenta registrarCuenta)throws Exception;
	public RegistrarCuenta consultarPorId(long regId)throws Exception;
	public List<RegistrarCuenta> consultarTodos()throws Exception;

}
