package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Contacto;
import service.ContactosService;
@CrossOrigin(origins = "*") //permite recibir peticiones desde cualquier origen
@RestController
public class ContactosController {
	@Autowired
	ContactosService service;
	@GetMapping(value="contactos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos() {
		return service.recuperarContactos();
	}
	
	@GetMapping(value="contactosresponseentity",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contacto>> recuperarContactosre() {
		List<Contacto> contactos = service.recuperarContactos();
		return new ResponseEntity<List<Contacto>> (contactos, HttpStatus.OK);
	}
	
	@GetMapping(value="contactos/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactos(@PathVariable("id") int id) {
		return service.buscarContacto(id);
	}
	
	@PostMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
	public String addContacto(@RequestBody Contacto contacto) {		
		return String.valueOf(service.agregarContacto(contacto));
	}
	
	@PutMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(@RequestBody Contacto contacto) {		
		service.actualizarContacto(contacto);
	}
		
	@DeleteMapping(value="contactos/{id}")
	public void eliminarPorId(@PathVariable("id") int idContacto) {
		service.eliminarContacto(idContacto);
		
	}
}
