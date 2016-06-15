package co.edu.usbcali.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ITiposUsuariosDAO;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

@Service()
@Scope("singleton")
public class TipoUsuarioLogica implements ITiposUsuariosLogica {

	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crear(TiposUsuarios tiposUsuarios) throws Exception {

		if (tiposUsuarios == null) {
			throw new Exception("El tipo de usuario no puede ser null");
		}

		if (tiposUsuarios.getTusuCodigo() == 0) {
			throw new Exception("El id es obligatorio");
		}

		if (tiposUsuarios.getTusuNombre() == null || tiposUsuarios.getTusuNombre().trim().equals("")) {
			throw new Exception("El nombre es obligatorio");
		}

		TiposUsuarios entity = tiposUsuariosDAO.consultarPorId(tiposUsuarios.getTusuCodigo());

		if (entity != null) {
			throw new Exception("El tipo de usuario ya existe");
		}

		tiposUsuariosDAO.crear(tiposUsuarios);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(TiposUsuarios tiposUsuarios) throws Exception {
		
		if (tiposUsuarios == null) {
			throw new Exception("El tipo de usuario no puede ser null");
		}

		if (tiposUsuarios.getTusuCodigo() == 0) {
			throw new Exception("El id es obligatorio");
		}

		if (tiposUsuarios.getTusuNombre() == null || tiposUsuarios.getTusuNombre().trim().equals("")) {
			throw new Exception("El nombre es obligatorio");
		}

//		TiposUsuarios entity = tiposUsuariosDAO.consultarPorId(tiposUsuarios.getTusuCodigo());
//
//		if (entity == null) {
//			throw new Exception("El tipo de usuario no existe");
//		}

		tiposUsuariosDAO.modificar(tiposUsuarios);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(TiposUsuarios tiposUsuarios) throws Exception {

		if (tiposUsuarios == null) {
			throw new Exception("El tipo de usuario no puede ser null");
		}

		if (tiposUsuarios.getTusuCodigo() == 0) {
			throw new Exception("El id es obligatorio");
		}

		TiposUsuarios entity = tiposUsuariosDAO.consultarPorId(tiposUsuarios.getTusuCodigo());
		if (entity == null) {
			throw new Exception("El tipo de usuario no existe");
		}

		tiposUsuariosDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public TiposUsuarios consultarPorId(Long tusuCodigo) throws Exception {
		return tiposUsuariosDAO.consultarPorId(tusuCodigo);
	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public List<TiposUsuarios> consultarTodos() throws Exception {
		return tiposUsuariosDAO.consultarTodos();
	}

}
