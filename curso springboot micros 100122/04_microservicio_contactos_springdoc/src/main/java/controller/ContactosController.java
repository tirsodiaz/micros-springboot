package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import model.Contacto;
import service.ContactosService;
@CrossOrigin(origins = "*") //permite recibir peticiones desde cualquier origen

@RestController
public class ContactosController {
	@Autowired
	ContactosService service;
	@Operation(summary = "contactos", description = "Listado de todos los contactos existentes")
	@GetMapping(value="contactos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos() {
		return service.recuperarContactos();
	}
	
	@GetMapping(value="contactos/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactoId(@PathVariable("id") int id) {
		return service.buscarContacto(id);
	}
	@Operation(summary="eliminacion", description = "Elimina contacto por id")
	@DeleteMapping(value="contactos/{id}")
	public void eliminarContacto(@Parameter(description="identificador del contacto a eliminar") @PathVariable("id") int id) {
		service.eliminarContacto(id);		
	}

	@PostMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
	public String guardarContacto(@RequestBody Contacto contacto) {		
		return String.valueOf(service.agregarContacto(contacto));
	}
	
	@PutMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto( @RequestBody Contacto contacto) {		
		service.actualizarContacto(contacto);
	}
}
