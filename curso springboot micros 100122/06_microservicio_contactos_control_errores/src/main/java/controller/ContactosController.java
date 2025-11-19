package controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
	
	@PostMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addContacto(@RequestBody Contacto contacto) {	
		return service.agregarContacto(contacto);
	}
	
    @PostMapping(value="usuarios",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contacto> alta(@RequestBody Contacto u) {
        return service.altaUsuario(u);
    }

    @PutMapping(value="usuarios",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contacto> modifica(@RequestBody Contacto u) {
        return service.modificarUsuario(u);
    }

    @DeleteMapping(value="usuarios/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> baja(@PathVariable Long id) {
        return service.bajaUsuario(id);        
    }
	
	@GetMapping(value="contactoss",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactoss() {
		return service.recuperarContactos();
	}
	@GetMapping(value="contactos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contacto>> recuperarContactos() {
		List<Contacto> listaContactos = service.recuperarContactos();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("totalContactos", String.valueOf(listaContactos.size()));
		return new ResponseEntity<List<Contacto>>(listaContactos,httpHeaders,HttpStatus.OK);		
	}
	@GetMapping(value="contactos/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContacto(@PathVariable("id") int id) {
		return service.buscarContacto(id);
	}
	
	@PutMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(@RequestBody Contacto contacto) {		
		service.actualizarContacto(contacto);
	}
		
	@DeleteMapping(value="contactos/{id}")
	public ResponseEntity<Boolean> eliminarPorId(@PathVariable("id") int idContacto) {
		return service.eliminarContacto(idContacto);
		
	}
	
	@GetMapping(value="countCollectionByNombreAndEmail/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Map<String, Long>> countCollectionByNombreAndEmail(@PathVariable("id") int id) {
		 return service.recuperarContactos().stream().filter(c->c.getEdad()<id).collect(Collectors.groupingBy(Contacto::getNombre, Collectors.groupingBy(Contacto::getEmail, Collectors.counting())));
	}
	

}
