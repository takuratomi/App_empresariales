package co.edu.usbcali.demo.controlador;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.dto.Resultado;

@RestController
@RequestMapping("/operacionesMatematicas")
public class OperacionesMatematicas {
	
	@RequestMapping(value="/sumar/{numeroUno}/{numeroDos}",method=RequestMethod.GET)
	public Resultado sumar(@PathVariable("numeroUno")Integer n1,@PathVariable("numeroDos")Integer n2)
	{
		Resultado resultado = new Resultado();		
		resultado.setValor(n1+n2);
		
		return resultado;
	}
	
	@RequestMapping(value="/sustraccion/{numeroUno}/{numeroDos}",method=RequestMethod.GET)
	public Resultado sustraccion(@PathVariable("numeroUno")Integer n1,@PathVariable("numeroDos")Integer n2)
	{
		Resultado resultado = new Resultado();		
		resultado.setValor(n1-n2);
		
		return resultado;
	}
	
	@RequestMapping(value="/multiplicacion/{numeroUno}/{numeroDos}",method=RequestMethod.GET)
	public Resultado multiplicacion(@PathVariable("numeroUno")Integer n1,@PathVariable("numeroDos")Integer n2)
	{
		Resultado resultado = new Resultado();		
		resultado.setValor(n1*n2);
		
		return resultado;
	}
	
	@RequestMapping(value="/division/{numeroUno}/{numeroDos}",method=RequestMethod.GET)
	public Resultado division(@PathVariable("numeroUno")Integer n1,@PathVariable("numeroDos")Integer n2)
	{
		Resultado resultado = new Resultado();		
		resultado.setValor(n1/n2);
		
		return resultado;
	}
	

}
