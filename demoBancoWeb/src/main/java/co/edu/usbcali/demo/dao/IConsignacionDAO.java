package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;

public interface IConsignacionDAO {
	
	public Long getConsecutivo();
	public void crear(Consignaciones consignaciones);
	public void modificar(Consignaciones consignaciones);
	public void borrar(Consignaciones consignaciones);
	public Consignaciones consultarPorId(ConsignacionesId consignacionesId);
	public List<Consignaciones> consultarTodos();

}
