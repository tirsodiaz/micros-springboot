package controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import model.Contacto;
import model.INombreEmailCount;
import service.ContactosService;
@CrossOrigin(origins = "*") //permite recibir peticiones desde cualquier origen
@Api(value="CRUD de contactos")
@RestController
public class ContactosController {
	@Autowired
	ContactosService service;
	@ApiOperation(value="Devuelve la lista de contactos existentes",response=List.class)
	@GetMapping(value="contactos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos() {
		return service.recuperarContactos();
	}
	
	@GetMapping(value="contactosresponseentity",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contacto>> recuperarContactosResponseEntity() {
		List<Contacto> contactos = service.recuperarContactos();
		return new ResponseEntity<List<Contacto>> (contactos, HttpStatus.OK);
	}
	
	@ApiOperation(value="Localiza un contacto por su identificador",response=Contacto.class)
	@GetMapping(value="contactos/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContacto( @ApiParam(value = "Identificador del contacto", required = true) @PathVariable("id") int id) {
		return service.buscarContacto(id);
	}
	@GetMapping(value="countByNombreAndEmail/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List <INombreEmailCount> countByNombreAndEmail(@PathVariable("id") int id) {
		return service.countByNombreAndEmail(id);
	}
	
	@GetMapping(value="countCollectionByNombreAndEmail/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Map<String, Long>> countCollectionByNombreAndEmail(@PathVariable("id") int id) {
		 return service.recuperarContactos().stream().filter(c->c.getEdad()<id).collect(Collectors.groupingBy(Contacto::getNombre, Collectors.groupingBy(Contacto::getEmail, Collectors.counting())));
	}
	
	@ApiOperation(value="Anade un nuevo contacto recibido en el cuerpo de la peticion")
	@PostMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
	public String guardarContacto(@ApiParam(value = "Objeto JSON a anadir", required = true) @RequestBody Contacto contacto) {		
		return String.valueOf(service.agregarContacto(contacto));
	}
	
	@ApiOperation(value="Actualiza en la base de datos el contacto recibido en el cuerpo de la peticion")
	@PutMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(@ApiParam(value = "Objeto JSON a actualizar", required = true) @RequestBody Contacto contacto) {		
		service.actualizarContacto(contacto);
	}
	
	@ApiOperation(value="Elimina el contacto a partir del identificador de contacto")	
	@DeleteMapping(value="contactos/{id}")
	public void eliminarPorId(@ApiParam(value = "Identificador del contacto a eliminar", required = true) @PathVariable("id") int idContacto) {
		service.eliminarContacto(idContacto);
		
	}
}
