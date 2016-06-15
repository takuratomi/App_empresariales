package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposDocumentos;

public interface ITipoDocumentoLogica {

	
	public void crear(TiposDocumentos tiposDocumentos)throws Exception;
	public void modificar(TiposDocumentos tiposDocumentos)throws Exception;
	public void borrar(TiposDocumentos tiposDocumentos)throws Exception;
	public TiposDocumentos consultarPorId(long tdocCodigo)throws Exception;
	public List<TiposDocumentos> consultarTodos()throws Exception;
	
}
