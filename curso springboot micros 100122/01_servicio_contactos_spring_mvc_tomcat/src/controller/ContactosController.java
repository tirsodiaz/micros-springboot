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

import dao.ContactosDao;
import model.Contacto;

@CrossOrigin(origins = "*") //permite recibir peticiones desde cualquier origen
@RestController
public class ContactosController {
	@Autowired
	ContactosDao dao;

	//contextPath=contactos (Web Project Settings) 
	//run web application on tomcat	+ http://localhost:8080/contactos/contactos 
	@GetMapping(value = "contactos",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos() {
		return dao.devolverContactos();
	}

	@GetMapping(value = "contactos/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactos(@PathVariable("id") int id) {
		return dao.recuperarContacto(id);
	}

	@PostMapping(value = "contactos",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addContacto(@RequestBody Contacto contacto) {
		dao.agregarContacto(contacto);
	}

	@PutMapping(value = "contactos",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(@RequestBody Contacto contacto) {
		dao.actualizarContacto(contacto);
	}

	@DeleteMapping(value = "eliminarPorEmail/{email}")
	public void eliminarContacto(@PathVariable("email") String email) {
		System.out.println(email);
		dao.eliminarContacto(email);
	}

	@DeleteMapping(value = "eliminarPorId/{id}")
	public void eliminarPorId(@PathVariable("id") int idContacto) {
		dao.eliminarContacto(idContacto);

	}
}
