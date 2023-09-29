package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Persona;
import service.AccesoService;

@RestController
public class ClientesContactosController {
	@Autowired
	AccesoService service;
	
	@GetMapping(value="clientecontactos/{nombre}/{email}/{edad}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombre,
			@PathVariable("email") String email, 
			@PathVariable("edad") int edad){
		Persona persona=new Persona(nombre,email,edad);
		return service.AltaConsultarAsync(persona);
	}
	@GetMapping(value="clientecontactos", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> recuperarContactos(){
		return service.recuperarContactos();
	}
	@GetMapping(value="clientecontactos/{idContacto}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Persona recuperarContacto(@PathVariable ("idContacto") int id) {
		return service.recuperarContacto(id);		
	}
	
}
