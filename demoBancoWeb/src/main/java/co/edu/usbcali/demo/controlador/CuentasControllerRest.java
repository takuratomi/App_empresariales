package co.edu.usbcali.demo.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.dto.CuentaDTO;
import co.edu.usbcali.demo.dto.ResultadoRest;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.vista.IDelegadoDeNegocio;

@RestController
@RequestMapping("/cuentaRes")
public class CuentasControllerRest {
	
private final static Logger log = LoggerFactory.getLogger(ClienteControllerRest.class);
	
	@Autowired
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	@RequestMapping(value="/consultarCuentaPorId/{cueNumero}",method=RequestMethod.GET)
	public CuentaDTO consultarCuentaPorId(@PathVariable("cueNumero") String cueNumero)
	{
		log.info("Ingreso a consultar Cuenta por cueNumero");
		
		Cuentas cuentas;
		CuentaDTO cuentaDTO = new CuentaDTO();
		
		try {
			cuentas = delegadoDeNegocio.consultarCuentasPorId(cueNumero);
			if(cuentas == null)
			{
				cuentaDTO.setCodigoError("10");
				cuentaDTO.setMensajeError("La cuenta con numero: "+cueNumero+" no existe");
				return cuentaDTO;
			}
			
			cuentaDTO = new CuentaDTO();
			cuentaDTO.setCueNumero(cuentas.getCueNumero());
			cuentaDTO.setCueActiva(cuentas.getCueActiva());
			cuentaDTO.setCueClave(cuentas.getCueClave());
			cuentaDTO.setCueSaldo(cuentas.getCueSaldo());
			cuentaDTO.setCliId(cuentas.getClientes().getCliId());
			
			cuentaDTO.setCodigoError("-1");
			cuentaDTO.setMensajeError("ok");
			
			return cuentaDTO;
			
		} catch (Exception e) {
			
			cuentaDTO.setCodigoError("50");
			cuentaDTO.setMensajeError(e.getMessage());
						
			return cuentaDTO;			
		}
		
	}
	
	@RequestMapping(value="/crearCuenta",method=RequestMethod.POST)
	public ResultadoRest crearCuenta(@RequestBody CuentaDTO cuentaDTO){
		
		log.info("Ingreso a crear cuenta");		
		
		try {
			Cuentas cuentas = new Cuentas();
			cuentas.setCueActiva(cuentaDTO.getCueActiva());
			cuentas.setCueClave(cuentaDTO.getCueClave());
			cuentas.setCueSaldo(cuentaDTO.getCueSaldo());
			cuentas.setCueNumero(cuentaDTO.getCueNumero());
			
			Clientes clientes = delegadoDeNegocio.consultarClientePorId(cuentaDTO.getCliId());
			
			cuentas.setClientes(clientes);
			
			delegadoDeNegocio.crearCuentas(cuentas);
			
			ResultadoRest resultadoRest = new ResultadoRest();
			resultadoRest.setCodigoError("-1");
			resultadoRest.setMensajeError("ok");
			return resultadoRest;
			
		} catch (Exception e) {
			
			ResultadoRest resultadoRest = new ResultadoRest();
			resultadoRest.setCodigoError("50");
			resultadoRest.setMensajeError(e.getMessage());
			return resultadoRest;
		}
		
	}
	
	@RequestMapping(value="/modificarCuenta",method=RequestMethod.PUT)
	public ResultadoRest modificarCuenta(@RequestBody CuentaDTO cuentaDTO){
		
		log.info("Ingreso a modificar cuenta");		
		
		try {
			Cuentas cuentas = delegadoDeNegocio.consultarCuentasPorId(cuentaDTO.getCueNumero());
			if(cuentas == null)
			{
				ResultadoRest resultadoRest = new ResultadoRest();
				resultadoRest.setCodigoError("10");
				resultadoRest.setMensajeError("La cuenta con número:"+cuentaDTO.getCueNumero()+" no existe");
				
				return resultadoRest;
				
			}
			cuentas.setCueActiva(cuentaDTO.getCueActiva());
			cuentas.setCueClave(cuentaDTO.getCueClave());
			cuentas.setCueNumero(cuentaDTO.getCueNumero());
			cuentas.setCueSaldo(cuentaDTO.getCueSaldo());
			Clientes clientes = delegadoDeNegocio.consultarClientePorId(cuentaDTO.getCliId());
			cuentas.setClientes(clientes);
			
			delegadoDeNegocio.modificarCuentas(cuentas);
			
			ResultadoRest resultadoRest = new ResultadoRest();
			resultadoRest.setCodigoError("-1");
			resultadoRest.setMensajeError("ok");
			return resultadoRest;
			
		} catch (Exception e) {
			
			ResultadoRest resultadoRest = new ResultadoRest();
			resultadoRest.setCodigoError("50");
			resultadoRest.setMensajeError(e.getMessage());
			return resultadoRest;
		}
		
	}
	
	@RequestMapping(value="/borrarCuenta/{id}",method=RequestMethod.DELETE)
	public ResultadoRest borrarCuenta(@PathVariable("id") String id ){
		
		log.info("Ingreso a borrar cuenta");		
		
		try {
			Cuentas cuentas = delegadoDeNegocio.consultarCuentasPorId(id);
			if(cuentas == null)
			{
				ResultadoRest resultadoRest = new ResultadoRest();
				resultadoRest.setCodigoError("10");
				resultadoRest.setMensajeError("La cuenta con número:"+id+" no existe");
				
				return resultadoRest;
				
			}
					
			delegadoDeNegocio.borrarCuentas(cuentas);
			
			ResultadoRest resultadoRest = new ResultadoRest();
			resultadoRest.setCodigoError("-1");
			resultadoRest.setMensajeError("ok");
			return resultadoRest;
			
		} catch (Exception e) {
			
			ResultadoRest resultadoRest = new ResultadoRest();
			resultadoRest.setCodigoError("50");
			resultadoRest.setMensajeError(e.getMessage());
			return resultadoRest;
		}
		
	}

}
