package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IConsignacionDAO;
import co.edu.usbcali.demo.dao.RetirosHibernateDAO;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;

@Service
@Scope("singleton")
public class ConsignacionesLogica implements IConsignacionesLogica {
	
	@Autowired
	private IConsignacionDAO consignacionDAO;	
	
	BigDecimal cero = new BigDecimal(0);
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crear(Consignaciones consignaciones) throws Exception {
		
		if(consignaciones == null)
		{
			throw new Exception("La consignacion no puede ser nulla");
		}
		
		if(consignaciones.getId().getCueNumero() == null || consignaciones.getId().getCueNumero().trim().equals("")==true )
		{
			throw new Exception("El numero de cuenta es obligatoria");
		}
		
//		if(consignaciones.getId().getConCodigo()==0)
//		{
//			throw new Exception("El codigo de consignacion es obligatoria");
//		}
		
		if(consignaciones.getConDescripcion()==null || consignaciones.getConDescripcion().trim().equals("")==true)
		{
			throw new Exception("La descripcion es obligatoria");
		}
		
		if(consignaciones.getConFecha()== null)
		{
			throw new Exception("La fecha es obligatoria");
		}
		
		if(consignaciones.getConValor()==null || consignaciones.getConValor().compareTo(cero)<0)
		{
			throw new Exception("El valor de la consignacion es obligatoria");
		}
		
		if(consignaciones.getCuentas() == null)
		{
			throw new Exception("La cuenta es obligatoria");
		}
		
		if(consignaciones.getUsuarios() == null)
		{
			throw new Exception("El usuarios es obligatorio.");
		}		
		
		ConsignacionesId id = new ConsignacionesId();
		id.setConCodigo(consignaciones.getId().getConCodigo());
		id.setCueNumero(consignaciones.getId().getCueNumero());
		
		Consignaciones entity = consignacionDAO.consultarPorId(id);
		if(entity != null)
		{
			throw new Exception("La consignacion ya existe");
		}
		
		consignacionDAO.crear(consignaciones);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Consignaciones consignaciones) throws Exception {
		
		if(consignaciones == null)
		{
			throw new Exception("La consignacion no puede ser nulla");
		}
		
		if(consignaciones.getId().getCueNumero() == null || consignaciones.getId().getCueNumero().trim().equals("")==true )
		{
			throw new Exception("El numero de cuenta es obligatoria");
		}
		
		if(consignaciones.getId().getConCodigo()==0)
		{
			throw new Exception("El codigo de consignacion es obligatoria");
		}
		
		if(consignaciones.getConDescripcion()==null || consignaciones.getConDescripcion().trim().equals("")==true)
		{
			throw new Exception("La descripcion es obligatoria");
		}
		
		if(consignaciones.getConFecha()== null)
		{
			throw new Exception("La fecha es obligatoria");
		}
		
		if(consignaciones.getConValor()==null || consignaciones.getConValor().compareTo(cero)<0)
		{
			throw new Exception("El valor de la consignacion es obligatoria");
		}
		
		if(consignaciones.getCuentas() == null)
		{
			throw new Exception("La cuenta es obligatoria");
		}
		
		if(consignaciones.getUsuarios() == null)
		{
			throw new Exception("El usuarios es obligatorio.");
		}		
		
		ConsignacionesId id = new ConsignacionesId();
		id.setConCodigo(consignaciones.getId().getConCodigo());
		id.setCueNumero(consignaciones.getId().getCueNumero());
		
		Consignaciones entity = consignacionDAO.consultarPorId(id);
		if(entity == null)
		{
			throw new Exception("La consignacion no existe");
		}
		
		consignacionDAO.modificar(consignaciones);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Consignaciones consignaciones) throws Exception {
		if(consignaciones == null)
		{
			throw new Exception("La consignacion no puede ser nulla");
		}
		
		if(consignaciones.getId().getCueNumero() == null || consignaciones.getId().getCueNumero().trim().equals("")==true )
		{
			throw new Exception("El numero de cuenta es obligatoria");
		}
		
		if(consignaciones.getId().getConCodigo()==0)
		{
			throw new Exception("El codigo de consignacion es obligatoria");
		}
		
		if(consignaciones.getUsuarios() == null)
		{
			throw new Exception("El usuarios es obligatorio.");
		}		
		
		ConsignacionesId id = new ConsignacionesId();
		id.setConCodigo(consignaciones.getId().getConCodigo());
		id.setCueNumero(consignaciones.getId().getCueNumero());
		
		Consignaciones entity = consignacionDAO.consultarPorId(id);
		if(entity == null)
		{
			throw new Exception("La consignacion no existe");
		}
		
		consignacionDAO.borrar(entity);

	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public Consignaciones consultarPorId(ConsignacionesId consignacionesId) throws Exception {
		return consignacionDAO.consultarPorId(consignacionesId);
	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public List<Consignaciones> consultarTodos() throws Exception {
		return consignacionDAO.consultarTodos();
	}

	@Override
	public Long getConsecutivo() throws Exception {
		return consignacionDAO.getConsecutivo();
	}

}
