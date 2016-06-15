package co.edu.usbcali.demo.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.dto.ClienteDTO;
import co.edu.usbcali.demo.dto.ResultadoRest;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.vista.IDelegadoDeNegocio;

@RestController
@RequestMapping("/clienteRest")
public class ClienteControllerRest {
	
	private final static Logger log = LoggerFactory.getLogger(ClienteControllerRest.class);
	
	@Autowired
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	@RequestMapping(value="/consultarClientePorId/{id}",method=RequestMethod.GET)
	public ClienteDTO consultarClientePorId(@PathVariable("id") Long id)
	{
		log.info("Ingreso a consultarCliente Por id");
		
		Clientes clientes;
		ClienteDTO clienteDTO = new ClienteDTO();
		
		try {
			clientes = delegadoDeNegocio.consultarClientePorId(id);
			if(clientes == null)
			{				
				clienteDTO.setCodigoError("10");
				clienteDTO.setMensajeError("El cliente con id:"+id+" no existe");
				return clienteDTO;
			}
			
			clienteDTO = new ClienteDTO();
			clienteDTO.setCliDireccion(clientes.getCliDireccion());
			clienteDTO.setCliId(clientes.getCliId());
			clienteDTO.setCliMail(clientes.getCliMail());
			clienteDTO.setCliNombre(clientes.getCliNombre());
			clienteDTO.setCliTelefono(clientes.getCliTelefono());
			clienteDTO.setTiposDocumentos(clientes.getTiposDocumentos().getTdocCodigo());
			
			clienteDTO.setCodigoError("-1");
			clienteDTO.setMensajeError("ok");
			
			return clienteDTO;
			
		} catch (Exception e) {
			
			clienteDTO.setCodigoError("50");
			clienteDTO.setMensajeError(e.getMessage());
			return clienteDTO;			
		}
		
	}
	
	@RequestMapping(value="/crearCliente",method=RequestMethod.POST)
	public ResultadoRest crearCliente(@RequestBody ClienteDTO clienteDTO)
	{
		log.info("Ingreso a crear cliente");
		
		try {
			Clientes clientes = new Clientes();
			clientes.setCliDireccion(clienteDTO.getCliDireccion());
			clientes.setCliId(clienteDTO.getCliId());
			clientes.setCliMail(clienteDTO.getCliMail());
			clientes.setCliNombre(clienteDTO.getCliNombre());
			clientes.setCliTelefono(clienteDTO.getCliTelefono());
			TiposDocumentos tiposDocumentos = delegadoDeNegocio.consultarTiposDocumentoPorId(clienteDTO.getTiposDocumentos());		
			clientes.setTiposDocumentos(tiposDocumentos);
			
			delegadoDeNegocio.crearCliente(clientes);
			
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
	
	@RequestMapping(value="/modificarCliente",method=RequestMethod.PUT)
	public ResultadoRest modificarCliente(@RequestBody ClienteDTO clienteDTO)
	{
		log.info("Ingreso a modificar cliente");
		
		try {
			Clientes clientes = delegadoDeNegocio.consultarClientePorId(clienteDTO.getCliId());
			if(clientes == null)
			{				
				ResultadoRest resultadoRest = new ResultadoRest();
				resultadoRest.setCodigoError("10");
				resultadoRest.setMensajeError("El cliente con id:"+clienteDTO.getCliId()+" no existe");
				
				return resultadoRest;
			}
			
			clientes.setCliDireccion(clienteDTO.getCliDireccion());
			clientes.setCliId(clienteDTO.getCliId());
			clientes.setCliMail(clienteDTO.getCliMail());
			clientes.setCliNombre(clienteDTO.getCliNombre());
			clientes.setCliTelefono(clienteDTO.getCliTelefono());
			TiposDocumentos tiposDocumentos = delegadoDeNegocio.consultarTiposDocumentoPorId(clienteDTO.getTiposDocumentos());		
			clientes.setTiposDocumentos(tiposDocumentos);
			
			delegadoDeNegocio.modificarCliente(clientes);
			
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
	
	@RequestMapping(value="/borrarCliente/{id}",method=RequestMethod.DELETE)
	public ResultadoRest borrarCliente(@PathVariable("id") Long id)
	{
		log.info("Ingreso a borrar cliente");
		
		try {
			Clientes clientes = delegadoDeNegocio.consultarClientePorId(id);
			if(clientes == null)
			{				
				ResultadoRest resultadoRest = new ResultadoRest();
				resultadoRest.setCodigoError("10");
				resultadoRest.setMensajeError("El cliente con id:"+id+" no existe");
				
				return resultadoRest;
			}
			
			delegadoDeNegocio.borrarCliente(clientes);
			
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
