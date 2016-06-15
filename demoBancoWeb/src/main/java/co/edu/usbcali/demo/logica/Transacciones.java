package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;
import co.edu.usbcali.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class Transacciones implements ITransacciones {
	
	@Autowired
	private IConsignacionesLogica consignarLogica;
	
	@Autowired
	private IRetirosLogica retiroLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Override
	@Transactional
	public BigDecimal consignar(String cueNumero, BigDecimal valor, Long usuCedula, String descripcion)throws Exception {
		
		Usuarios usuarios = usuarioLogica.consultarPorId(usuCedula);		
		Cuentas cuentas = cuentaLogica.consultarPorId(cueNumero);
		
		try {
			
			if(usuarios == null)
			{
				throw new Exception("El usuario no puede ser nulo");
			}
				
			if(cuentas == null)
			{
				throw new Exception("La cuenta no puede ser nulo");
			}
			
			if(descripcion == null)
			{
				throw new Exception("la descripcion no puede ser nula");
			}
			
			BigDecimal cero = new BigDecimal(0);
			if(valor.compareTo(cero)==0)
			{
				throw new Exception("El valor es obligatorio");
			}
			
			if(cuentas.getCueActiva().trim().equals("S") == false)
			{
				throw new Exception("La cuenta no se encuentra activa");
			}
			
			ConsignacionesId consignacionesId = new ConsignacionesId();			
			consignacionesId.setCueNumero(cueNumero);
			Long conCodigo = consignarLogica.getConsecutivo();
			consignacionesId.setConCodigo(conCodigo);
			Consignaciones consignaciones = new Consignaciones();
			consignaciones.setId(consignacionesId);
			Calendar cal1 = Calendar.getInstance();
			Date conFecha = new Date(cal1.get(Calendar.YEAR)-1900, cal1.get(Calendar.MONTH), cal1.get(Calendar.DATE), cal1.get(Calendar.HOUR),cal1.get(Calendar.MINUTE), cal1.get(Calendar.SECOND));
			consignaciones.setConFecha(conFecha);
			consignaciones.setCuentas(cuentas);
			consignaciones.setUsuarios(usuarios);
			consignaciones.setConDescripcion(descripcion);
			consignaciones.setConValor(valor);
			cuentas.setCueSaldo(cuentas.getCueSaldo().add(valor));			
			
			consignarLogica.crear(consignaciones);
//			cuentaLogica.modificar(cuentas);
			return consignaciones.getConValor();
			
			
		} catch (Exception e) {
//			e.getMessage();
			
			return null;
		}
	}

	@Override
	@Transactional
	public BigDecimal retirar(String cueNumero, BigDecimal valor, Long usuCedula, String descripcion) throws Exception {
		
		Usuarios usuarios = usuarioLogica.consultarPorId(usuCedula);		
		Cuentas cuentas = cuentaLogica.consultarPorId(cueNumero);
		
		try {
			
			if(usuarios == null)
			{
				throw new Exception("El usuario no puede ser nulo");
			}
				
			if(cuentas == null)
			{
				throw new Exception("La cuenta no puede ser nulo");
			}
			
			if(descripcion == null)
			{
				throw new Exception("la descripcion no puede ser nula");
			}
			
			BigDecimal cero = new BigDecimal(0);
			if(valor.compareTo(cero)==0)
			{
				throw new Exception("El valor es obligatorio");
			}
			
			if (cuentas.getCueSaldo().compareTo(cero)==0) {
				throw new Exception("La cuenta no tiene saldo");
			}
			
			BigDecimal resultado = new BigDecimal(0);
			resultado = cuentas.getCueSaldo().subtract(valor);
			if(resultado.compareTo(cero)<=0)
			{
				throw new Exception("La cuenta no dispone de suficiente salgo para el retiro");
			}
			
			if(cuentas.getCueActiva().trim().equals("S") == false)
			{
				throw new Exception("La cuenta no se encuentra activa");
			}
			
			RetirosId id = new RetirosId();
			id.setCueNumero(cueNumero);
			Long retCodigo = retiroLogica.getConsecutivo();
			id.setRetCodigo(retCodigo);
			
			Retiros retiros= new Retiros();
			Calendar cal1 = Calendar.getInstance();
			Date retFecha = new Date(cal1.get(Calendar.YEAR)-1900, cal1.get(Calendar.MONTH), cal1.get(Calendar.DATE), cal1.get(Calendar.HOUR),cal1.get(Calendar.MINUTE), cal1.get(Calendar.SECOND));			
			retiros.setId(id);
			retiros.setRetFecha(retFecha);
			retiros.setRetDescripcion(descripcion);
			retiros.setCuentas(cuentas);
			retiros.setUsuarios(usuarios);
			retiros.setRetValor(valor);
			cuentas.setCueSaldo(cuentas.getCueSaldo().subtract(valor));			
			
			retiroLogica.crear(retiros);
			
			return retiros.getRetValor();
			
			
		} catch (Exception e) {
			
			return null;
		}
	}

	@Override
	@Transactional
	public BigDecimal traslado(String cueOrigen, String cueDestino, BigDecimal valor, Long usuCedula,String descripcion) throws Exception {
		
		Usuarios usuarios = usuarioLogica.consultarPorId(usuCedula);		
		Cuentas cuentasOrigen = cuentaLogica.consultarPorId(cueOrigen);
		Cuentas cuentasDestino = cuentaLogica.consultarPorId(cueDestino);
		
		try {
			if(usuarios == null){
				throw new Exception("El usuario es obligatorio");
			}			
			
			if(cuentasOrigen == null){
				throw new Exception("La cuenta de origen es obligatoria");
			}
			
			if(cuentasDestino == null){
				throw new Exception("La cuenta destino es obligatoria");
			}
			
			if(descripcion== null){
				throw new Exception("La descripcion es obligatoria");
			}
			
			BigDecimal cero = new BigDecimal(0);
			if(valor.compareTo(cero)==0)
			{
				throw new Exception("El valor es obligatorio");
			}
			
			BigDecimal dispOrigen = new BigDecimal(0);
			dispOrigen = cuentasOrigen.getCueSaldo().subtract(valor.add(new BigDecimal(1000)));
			if(dispOrigen.compareTo(cero)<=0){
				throw new Exception("La cuenta origen no tiene suficiente salgo");
			}
			
			RetirosId retirosId= new RetirosId();
			retirosId.setCueNumero(cueOrigen);
			Long retCodigo = retiroLogica.getConsecutivo();
			retirosId.setRetCodigo(retCodigo);
			ConsignacionesId consignacionesId = new ConsignacionesId();
			consignacionesId.setCueNumero(cueDestino);
			Long conCodigo = consignarLogica.getConsecutivo();
			consignacionesId.setConCodigo(conCodigo);
			
			Calendar cal1 = Calendar.getInstance();
			Date retFecha = new Date(cal1.get(Calendar.YEAR)-1900, cal1.get(Calendar.MONTH), cal1.get(Calendar.DATE), cal1.get(Calendar.HOUR),cal1.get(Calendar.MINUTE), cal1.get(Calendar.SECOND));
			Date conFecha = new Date(cal1.get(Calendar.YEAR)-1900, cal1.get(Calendar.MONTH), cal1.get(Calendar.DATE), cal1.get(Calendar.HOUR),cal1.get(Calendar.MINUTE), cal1.get(Calendar.SECOND));
			
			Retiros retiros = new Retiros(retirosId,cuentasOrigen,valor,retFecha,descripcion);
			retiros.setUsuarios(usuarios);
			if(retiros == null)
			{
				throw new Exception("El retiro no se pudo realizar");
			}
			
			Consignaciones consignaciones = new Consignaciones(consignacionesId, cuentasDestino, valor, conFecha, descripcion);
			consignaciones.setUsuarios(usuarios);
			if(consignaciones == null)
			{
				consignaciones = new Consignaciones(consignacionesId, cuentasOrigen, (valor.add(new BigDecimal(1000))), conFecha, "cancelacion de traslado");
				throw new Exception("La consignacion no se pudo realizar");
			}
			cuentasOrigen.setCueSaldo(cuentasOrigen.getCueSaldo().subtract(valor).add(new BigDecimal(1000)));
			cuentasDestino.setCueSaldo(cuentasDestino.getCueSaldo().add(valor));
			
			retiroLogica.crear(retiros);
			consignarLogica.crear(consignaciones);
			
			return consignaciones.getConValor();
			
		}catch (Exception e) {
			
			return null;
		}
	}

}
