package co.edu.usbcali.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IRegistrarCuentaDAO;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.RegistrarCuenta;


@Service
@Scope("singleton")
public class RegistrarCuentaLogica implements IRegistrarCuentaLogica {

	@Autowired
	private IRegistrarCuentaDAO registrarCuentaDAO;

	@Autowired
	private IClienteLogica clienteLogica;

	@Autowired
	private ICuentaLogica cuentaLogica;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crear(RegistrarCuenta registrarCuenta) throws Exception {
		if (registrarCuenta == null)
			throw new Exception("El registro de cuenta no puede ser nulo");

		if (registrarCuenta.getRegId() == 0)
			throw new Exception("EL id de registro cuenta no puede ser nulo");

		Clientes clientes = clienteLogica.consultarPorId(registrarCuenta.getClientes().getCliId());
		if (clientes == null)
			throw new Exception("El cliente no puede ser nulo");

		if (clientes.getCliId() == 0)
			throw new Exception("El codigo del cliente no puede ser nulo");

		Cuentas cuentas = cuentaLogica.consultarPorId(registrarCuenta.getCuentas().getCueNumero());

		if (cuentas == null)
			throw new Exception("La cuenta no puede ser nula");

		if (cuentas.getCueNumero().trim().equals("") || cuentas.getCueNumero() == null)
			throw new Exception("El numero de cuenta no puede ser nulo");

		registrarCuentaDAO.crear(registrarCuenta);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(RegistrarCuenta registrarCuenta) throws Exception {

		if (registrarCuenta == null)
			throw new Exception("El registro de cuenta no puede ser nulo");

		if (registrarCuenta.getRegId() == 0)
			throw new Exception("EL id de registro cuenta no puede ser nulo");

		Clientes clientes = clienteLogica.consultarPorId(registrarCuenta.getClientes().getCliId());
		if (clientes == null)
			throw new Exception("El cliente no puede ser nulo");

		if (clientes.getCliId() == 0)
			throw new Exception("El codigo del cliente no puede ser nulo");

		Cuentas cuentas = cuentaLogica.consultarPorId(registrarCuenta.getCuentas().getCueNumero());

		if (cuentas == null)
			throw new Exception("La cuenta no puede ser nula");

		if (cuentas.getCueNumero().trim().equals("") || cuentas.getCueNumero() == null)
			throw new Exception("El numero de cuenta no puede ser nulo");

		RegistrarCuenta entity = new RegistrarCuenta();

		entity = registrarCuentaDAO.consultarPorId(registrarCuenta.getRegId());

		if (entity == null)
			throw new Exception("El registro de cuenta no existe");

		registrarCuentaDAO.modificar(registrarCuenta);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(RegistrarCuenta registrarCuenta) throws Exception {

		if (registrarCuenta == null)
			throw new Exception("El registro de cuenta no puede ser nulo");

		if (registrarCuenta.getRegId() == 0)
			throw new Exception("EL id de registro cuenta no puede ser nulo");

		RegistrarCuenta entity = new RegistrarCuenta();
		
		entity = registrarCuentaDAO.consultarPorId(registrarCuenta.getRegId());
		
		if(entity == null )
			throw new Exception("EL registro de cuenta ya no existe");
		
		registrarCuentaDAO.borrar(entity);

	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public RegistrarCuenta consultarPorId(long regId) throws Exception {
		return registrarCuentaDAO.consultarPorId(regId);
	}

	@Override
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public List<RegistrarCuenta> consultarTodos() throws Exception {
		return registrarCuentaDAO.consultarTodos();
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public Long getConsecutivo() throws Exception {
		return registrarCuentaDAO.getConsecutivo();
	}

}
