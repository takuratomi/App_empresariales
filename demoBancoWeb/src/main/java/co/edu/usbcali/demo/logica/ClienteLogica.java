package co.edu.usbcali.demo.logica;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IClienteDAO;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Service("clienteLogica")
@Scope("singleton")
public class ClienteLogica implements IClienteLogica {
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;	
	
	EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crear(Clientes clientes) throws Exception {
		if(clientes==null){
			throw new Exception("El cliente no puede ser null");
		}
		
		if(clientes.getCliDireccion()==null || clientes.getCliDireccion().trim().equals("")==true){
			throw new Exception("La direccion es obligatoria");
		}
		if(clientes.getCliId()==0){
			throw new Exception("El ID es obligatorio");
		}
		if(clientes.getCliMail()==null || clientes.getCliMail().trim().equals("")==true){
			throw new Exception("El mail es obligatorio");
		}
		if(emailValidator.isValid(clientes.getCliMail())==false){
			throw new Exception("El mail no es valido no tiene el formato adecuado");
		}
		if(clientes.getCliNombre()==null || clientes.getCliNombre().trim().equals("")==true){
			throw new Exception("El nombre es obligatorio. Escriba un nombre");
		}
		if(clientes.getCliTelefono()==null || clientes.getCliTelefono().trim().equals("")==true){
			throw new Exception("El telefono es obligatorio");
		}
		if(clientes.getTiposDocumentos()==null || clientes.getTiposDocumentos().getTdocCodigo()==0){
			throw new Exception("El tipo de documento es obligatorio");
		}
		TiposDocumentos tiposDocumentos=tipoDocumentoLogica.consultarPorId(clientes.getTiposDocumentos().getTdocCodigo());
		
		if(tiposDocumentos==null){
			throw new Exception("El tipo de documento con id "+clientes.getTiposDocumentos().getTdocCodigo()+" no existe");
		}
		
		Clientes entity=clienteDAO.consultarPorId(clientes.getCliId());
		
		if(entity!=null){
			throw new Exception("El cliente ya existe");
		}
		
		clienteDAO.crear(clientes);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Clientes clientes) throws Exception {
		
		if(clientes==null){
			throw new Exception("El cliente no puede ser null");
		}
		
		if(clientes.getCliDireccion()==null || clientes.getCliDireccion().trim().equals("")==true){
			throw new Exception("La direccion es obligatoria");
		}
		if(clientes.getCliId()==0){
			throw new Exception("El ID es obligatorio");
		}
		if(clientes.getCliMail()==null || clientes.getCliMail().trim().equals("")==true){
			throw new Exception("El mail es obligatorio");
		}
		if(emailValidator.isValid(clientes.getCliMail())==false){
			throw new Exception("El mail no es valido no tiene el formato adecuado");
		}
		if(clientes.getCliNombre()==null || clientes.getCliNombre().trim().equals("")==true){
			throw new Exception("El nombre es obligatorio. Escriba un nombre");
		}
		if(clientes.getCliTelefono()==null || clientes.getCliTelefono().trim().equals("")==true){
			throw new Exception("El telefono es obligatorio");
		}
		if(clientes.getTiposDocumentos()==null || clientes.getTiposDocumentos().getTdocCodigo()==0){
			throw new Exception("El tipo de documento es obligatorio");
		}
		TiposDocumentos tiposDocumentos=tipoDocumentoLogica.consultarPorId(clientes.getTiposDocumentos().getTdocCodigo());
		
		if(tiposDocumentos==null){
			throw new Exception("El tipo de documento con id "+clientes.getTiposDocumentos().getTdocCodigo()+" no existe");
		}
		
		clienteDAO.modificar(clientes);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Clientes clientes) throws Exception {
		if(clientes==null){
			throw new Exception("El cliente no puede ser null");
		}
		
		if(clientes.getCliId()==0){
			throw new Exception("El ID es obligatorio");
		}
		
		Clientes entity=clienteDAO.consultarPorId(clientes.getCliId());
		if(entity==null){
			throw new Exception("El cliente no existe");
		}
		clienteDAO.borrar(entity);

	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public Clientes consultarPorId(long cliId) throws Exception {
		return clienteDAO.consultarPorId(cliId);
	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public List<Clientes> consultarTodos() throws Exception {
		return clienteDAO.consultarTodos();
	}

}
