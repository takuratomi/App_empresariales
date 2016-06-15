package co.edu.usbcali.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ITipoDocumentoDAO;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class TipoDocumentoLogica implements ITipoDocumentoLogica {
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crear(TiposDocumentos tiposDocumentos) throws Exception {

		if (tiposDocumentos == null) {
			throw new Exception("El tipo documento no puede ser null");
		}

		if (tiposDocumentos.getTdocCodigo() == 0) {
			throw new Exception("El id es obligatorio");
		}

		if (tiposDocumentos.getTdocNombre() == null || tiposDocumentos.getTdocNombre().trim().equals("")) {
			throw new Exception("El nombre es obligatorio");
		}

		TiposDocumentos entity = tipoDocumentoDAO.consultarPorId(tiposDocumentos.getTdocCodigo());

		if (entity != null) {
			throw new Exception("El tipo documento ya existe");
		}

		tipoDocumentoDAO.crear(tiposDocumentos);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(TiposDocumentos tiposDocumentos) throws Exception {
		if (tiposDocumentos == null) {
			throw new Exception("El tipo documento no puede ser null");
		}

		if (tiposDocumentos.getTdocCodigo() == 0) {
			throw new Exception("El id es obligatorio");
		}

		if (tiposDocumentos.getTdocNombre() == null || tiposDocumentos.getTdocNombre().trim().equals("")) {
			throw new Exception("El nombre es obligatorio");
		}

		tipoDocumentoDAO.modificar(tiposDocumentos);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(TiposDocumentos tiposDocumentos) throws Exception {

		if (tiposDocumentos == null) {
			throw new Exception("El tipo documento no puede ser null");
		}

		if (tiposDocumentos.getTdocCodigo() == 0) {
			throw new Exception("El id es obligatorio");
		}

		TiposDocumentos entity = tipoDocumentoDAO.consultarPorId(tiposDocumentos.getTdocCodigo());

		if (entity == null) {
			throw new Exception("El tipo documento no existe");
		}

		tipoDocumentoDAO.borrar(entity);

	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public TiposDocumentos consultarPorId(long tdocCodigo) throws Exception {	
		return tipoDocumentoDAO.consultarPorId(tdocCodigo);
	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public List<TiposDocumentos> consultarTodos() throws Exception {
		return tipoDocumentoDAO.consultarTodos();
	}

}
