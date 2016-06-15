package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IRetirosDAO;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;

@Service
@Scope("singleton")
public class RetirosLogica implements IRetirosLogica {
	
	@Autowired
	private IRetirosDAO retirosDAO;
	
	BigDecimal cero = new BigDecimal(0);
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crear(Retiros retiros) throws Exception {

		if (retiros == null) {
			throw new Exception("El retiro no puede ser null");
		}
		
		if(retiros.getId().getCueNumero() == null || retiros.getId().getCueNumero().trim().equals("")==true){
			throw new Exception("El numero de cuenta es obligatorio");
		}
		
//		if(retiros.getId().getRetCodigo()==0){
//			throw new Exception("El codigo del retiro es obligatorio");
//		}
		
		if(retiros.getId() == null)
		{
			throw new Exception("El id no puede ser nulo");
		}
		
		if(retiros.getRetDescripcion()==null || retiros.getRetDescripcion().trim().equals("")==true){
			throw new Exception("La descripción es obligatoria");
		}
		
		if(retiros.getRetFecha()==null){
			throw new Exception("La fecha es obligatoria");
		}
		
		if(retiros.getRetValor()==null || retiros.getRetValor().compareTo(cero)<0){
			throw new Exception("El valor es obligatorio no debe ser cero");
		}
		
		if(retiros.getUsuarios() == null){
			throw new Exception("El usuario no puede ser nulo");
		}
		
		if(retiros.getCuentas() == null)
		{
			throw new Exception("La cuenta no puede ser nula");
		}		
		
		Retiros entity = retirosDAO.consultarPorId(retiros.getId());
		if( entity != null){
			throw new Exception("El retiro ya existe");			
		}
		
		retirosDAO.crear(retiros);	

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Retiros retiros) throws Exception {
				
		if (retiros == null) {
			throw new Exception("El retiro no puede ser null");
		}
		
		if(retiros.getId().getCueNumero() == null || retiros.getId().getCueNumero().trim().equals("")==true){
			throw new Exception("El numero de cuenta es obligatorio");
		}
		
		if(retiros.getId().getRetCodigo()==0){
			throw new Exception("El codigo del retiro es obligatorio");
		}
		
		if(retiros.getRetDescripcion()==null || retiros.getRetDescripcion().trim().equals("")==true){
			throw new Exception("La descripción es obligatoria");
		}
		
		if(retiros.getRetFecha()==null){
			throw new Exception("La fecha es obligatoria");
		}
		
		if(retiros.getRetValor()==null || retiros.getRetValor().compareTo(cero)<0){
			throw new Exception("El valor es obligatorio no debe ser cero");
		}
		
		if(retiros.getUsuarios() == null){
			throw new Exception("El usuario no puede ser nulo");
		}
		
		if(retiros.getCuentas() == null)
		{
			throw new Exception("La cuenta no puede ser nula");
		}
		
//		Retiros entity = retirosDAO.consultarPorId(retiros.getId());
//		
//		if( entity == null){
//			throw new Exception("El retiro no existe");			
//		}		
		
		retirosDAO.modificar(retiros);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Retiros retiros) throws Exception {
		
		if (retiros == null) {
			throw new Exception("El retiro no puede ser null");
		}
		
		if(retiros.getId().getCueNumero() == null || retiros.getId().getCueNumero().trim().equals("")==true){
			throw new Exception("El numero de cuenta es obligatorio");
		}
		
		if(retiros.getId().getRetCodigo()==0){
			throw new Exception("El codigo del retiro es obligatorio");
		}		

		Retiros entity = retirosDAO.consultarPorId(retiros.getId());
		if( entity == null){
			throw new Exception("El retiro no existe");
		}
		
		retirosDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public Retiros consultarPorId(RetirosId id) throws Exception {
		return retirosDAO.consultarPorId(id);
	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public List<Retiros> consultarTodos() throws Exception {
		return retirosDAO.consultarTodos();
	}

	@Override
	public Long getConsecutivo() throws Exception {
		
		return retirosDAO.getConsecutivo();
	}

}
