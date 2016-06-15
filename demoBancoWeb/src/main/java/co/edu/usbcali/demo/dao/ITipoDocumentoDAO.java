package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposDocumentos;



public interface ITipoDocumentoDAO {
	
	public void crear(TiposDocumentos tiposDocumentos);
	public void modificar(TiposDocumentos tiposDocumentos);
	public void borrar(TiposDocumentos tiposDocumentos);
	public TiposDocumentos consultarPorId(long tdocCodigo);
	public List<TiposDocumentos> consultarTodos();

}
