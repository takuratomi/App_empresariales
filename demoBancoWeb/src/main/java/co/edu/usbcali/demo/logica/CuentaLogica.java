package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ICuentasDAO;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;

@Service()
@Scope("singleton")
public class CuentaLogica implements ICuentaLogica {

	@Autowired
	private ICuentasDAO cuentaDao;

	@Autowired
	private IClienteLogica clientelogica;

	BigDecimal bigTemporal = new BigDecimal(0);
	
//	funcion que genera automaticamente el numero de cuenta 
//	@Transactional(readOnly = true, rollbackFor = Exception.class)
//	public String generateCuenta(long consecutivo){
//		String numeroCuenta = String.valueOf(consecutivo);
//		System.out.print("longitud de la cadena"+numeroCuenta.length());
//		return "";
//		
////		Random rn = new Random();
////		int resultado;
////		String cueNumero = "";
////		Boolean cuentaCorrecta = false;
////		Cuentas cuentas;
////		do {
////			for (int i = 0; i < 3; i++) {
////				
////				resultado = rn.nextInt(2000-1000)+1000;
////				cueNumero = cueNumero+resultado;			
////				cueNumero= cueNumero+"-";			
////		}
////		cueNumero = cueNumero.substring(0, cueNumero.length()-1);
////		
////		cuentas = cuentaDao.consultarPorId(cueNumero);
////		
////		if(cuentas == null )
////		{
////			cuentaCorrecta = true;
////		}
////			
////			
////		} while (cuentaCorrecta == false);
////		return cueNumero;
//		
//	}
	
//	funcion que genera clave de cuenta:
//	condicion: 4 ultimos digitos de la cedula del cliente más el 
//	numero de la cuenta	
	public String generateClave(long cedula,String cueNumero){
		
		String clave ="";
		String cedula_string = Long.toString(cedula);
		cedula_string = cedula_string.substring(cedula_string.length()-4, cedula_string.length());
		cueNumero = cueNumero.replace("-", "");
		cedula_string = cedula_string+cueNumero;
		
		return cedula_string;	
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crear(Cuentas cuentas) throws Exception {
			
		
		if (cuentas == null) {
			throw new Exception("La cuenta no puede ser nula");
		}

		if (cuentas.getCueNumero() == null || cuentas.getCueNumero().trim().equals("")) {
			throw new Exception("El número de cuenta es obligatorio");
		}

		if (cuentas.getClientes() == null) {
			throw new Exception("El cliente es obligatorio");
		}
		Clientes clientes = new Clientes();
		clientes = clientelogica.consultarPorId(cuentas.getClientes().getCliId());

		if (clientes == null) {
			throw new Exception("El cliente no existe");
		}

		if (cuentas.getCueClave().length() == 0 || cuentas.getCueClave() == null
				|| cuentas.getCueClave().trim().equals("")) {
			throw new Exception("La clave es obligatoria");
		}

		if (cuentas.getCueClave().trim().length() < 4) {
			throw new Exception("La clave debe contener mínimo cuatro caracteres");
		}

		if (cuentas.getCueActiva() == null || cuentas.getCueActiva().trim().equals("")) {
			throw new Exception("El estado de la cuenta es obligatoria");
		}

		if (cuentas.getCueSaldo() == null) {
			throw new Exception("El saldo inicial es obligatorio");
		}
		if (cuentas.getCueSaldo().compareTo(bigTemporal) < 0) {
			throw new Exception("El saldo inicial no puede ser menor que cero");
		}

		Cuentas entity = cuentaDao.consultarPorId(cuentas.getCueNumero());

		if (entity != null) {
			throw new Exception("La cuenta ya existe");
		}

		cuentaDao.crear(cuentas);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(Cuentas cuentas) throws Exception {

		if (cuentas == null) {
			throw new Exception("La cuenta no puede ser nula");
		}

		if (cuentas.getCueNumero() == null || cuentas.getCueNumero().trim().equals("")) {
			throw new Exception("El número de cuenta es obligatorio");
		}

		if (cuentas.getClientes() == null) {
			throw new Exception("El cliente es obligatorio");
		}

		clientelogica.consultarPorId(cuentas.getClientes().getCliId());

		if (clientelogica == null) {
			throw new Exception("El cliente no existe");
		}

		if (cuentas.getCueClave() == null || cuentas.getCueClave().trim().equals("")) {
			throw new Exception("La clave es obligatoria");
		}

		if (cuentas.getCueClave().length() < 4) {
			throw new Exception("La clave debe contener mínimo cuatro caracteres");
		}

		if (cuentas.getCueActiva() == null || cuentas.getCueActiva().trim().equals("")) {
			throw new Exception("El estado de la cuenta es obligatoria");
		}

		if (cuentas.getCueSaldo() == null) {
			throw new Exception("El saldo inicial es obligatorio");
		}
		if (cuentas.getCueSaldo().compareTo(bigTemporal) < 0) {
			throw new Exception("El saldo inicial no puede ser menor que cero");
		}

//		Cuentas entity = cuentaDao.consultarPorId(cuentas.getCueNumero());
//
//		if (entity == null) {
//			throw new Exception("La cuenta no existe");
//		}

		cuentaDao.modificar(cuentas);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(Cuentas cuentas) throws Exception {

		if (cuentas == null) {
			throw new Exception("La cuenta no puede ser nula");
		}

		if (cuentas.getCueNumero() == null || cuentas.getCueNumero().trim().equals("")) {
			throw new Exception("El número de cuenta es obligatorio");
		}

//		if (cuentas.getCueSaldo().compareTo(bigTemporal) > 0) {
//			throw new Exception("La cuenta tiene un saldo de $(COP)" + cuentas.getCueSaldo()
//					+ " no puede ser borrada si su saldo no es 0");
//		}

		Cuentas entity = cuentaDao.consultarPorId(cuentas.getCueNumero());

		if (entity == null) {
			throw new Exception("La cuenta no existe");
		}

		/*
		 * Opcion de no borrar si no desactivar
		 * 
		 * 
		 */
		// cuentas.setCueActiva("N");

		cuentaDao.borrar(entity);

	}

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Cuentas consultarPorId(String cueNumero) throws Exception {
		return cuentaDao.consultarPorId(cueNumero);
	}

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<Cuentas> consultarTodos() throws Exception {
		return cuentaDao.consultarTodos();
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long getConsecutivo() throws Exception {
		return cuentaDao.getConsecutivo();
	}
	
	@Override
	public String generateCuenta(Long consecutivo) {
		Long conse = consecutivo;
		String numero_consecutivo = String.valueOf(consecutivo);		
		String numeroCuenta ="";
		int conteo = 0;
		for (int i = 0; i < numero_consecutivo.length(); i++) {
			if(conteo == 4)
			{
				conteo = 0;
				numeroCuenta = numeroCuenta+"-";
			}
			numeroCuenta = numeroCuenta+numero_consecutivo.charAt(i);
			conteo++;
		}
		
		
		return numeroCuenta;
	}
}
