package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;

public interface IClienteLogica {

	public void crear(Clientes clientes) throws Exception;

	public void modificar(Clientes clientes) throws Exception;

	public void borrar(Clientes clientes) throws Exception;

	public Clientes consultarPorId(long cliId) throws Exception;

	public List<Clientes> consultarTodos() throws Exception;

}
