package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;

public interface IRetirosDAO {
	
	public Long getConsecutivo();
	public void crear(Retiros retiros);
	public void modificar(Retiros retiros);
	public void borrar(Retiros retiros);
	public Retiros consultarPorId(RetirosId id);
	public List<Retiros> consultarTodos();

}
