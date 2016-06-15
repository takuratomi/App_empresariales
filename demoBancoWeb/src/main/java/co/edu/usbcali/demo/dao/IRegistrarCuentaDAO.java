package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.RegistrarCuenta;

public interface IRegistrarCuentaDAO {
	
	public Long getConsecutivo();
	public void crear(RegistrarCuenta registrarCuenta);
	public void modificar(RegistrarCuenta registrarCuenta);
	public void borrar(RegistrarCuenta registrarCuenta);
	public RegistrarCuenta consultarPorId(long regId);
	public List<RegistrarCuenta> consultarTodos();

}
