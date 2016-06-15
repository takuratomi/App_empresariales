package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;

public interface IClienteDAO {
	
	public void crear(Clientes clientes);
	public void modificar(Clientes clientes);
	public void borrar(Clientes clientes);
	public Clientes consultarPorId(long cliId);
	public List<Clientes> consultarTodos();
}
