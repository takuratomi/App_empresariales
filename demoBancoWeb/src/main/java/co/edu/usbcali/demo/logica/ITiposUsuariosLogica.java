package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

public interface ITiposUsuariosLogica {

	public void crear(TiposUsuarios tiposUsuarios) throws Exception;

	public void modificar(TiposUsuarios tiposUsuarios) throws Exception;

	public void borrar(TiposUsuarios tiposUsuarios) throws Exception;

	public TiposUsuarios consultarPorId(Long tusuCodigo) throws Exception;

	public List<TiposUsuarios> consultarTodos() throws Exception;

}
