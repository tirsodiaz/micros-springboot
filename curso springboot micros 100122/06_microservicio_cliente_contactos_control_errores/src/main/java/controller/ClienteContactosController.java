package controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import model.Persona;
import service.ClienteContactosService;

@RestController
public class ClienteContactosController {

    @Autowired
    ClienteContactosService service;

    @GetMapping(value = "/clientecontactos/{nombre}/{email}/{edad}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> altaPersona(@PathVariable("nombre") String nombre,
            @PathVariable("email") String email, @PathVariable("edad") int edad) throws Exception  {
        Persona persona = new Persona(nombre, email, edad);
        return service.altaConsultar(persona);      
    }
    
    @PostMapping(value = "clientecontactos",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> guardarContacto(@Valid @RequestBody Persona persona) throws Exception {
    	return service.agregarContacto(persona);    	     
    }
    
    @PostMapping(value = "clienteusuarios",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alta(@RequestBody Persona usuario) {
        return service.altaUsuario(usuario);
    }

    @PutMapping(value = "clienteusuarios",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificar(@RequestBody Persona usuario) {
        return service.modificarUsuario(usuario);
    }

    @DeleteMapping(value = "clienteusuarios/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> baja(@PathVariable Long id) {
        return service.bajaUsuario(id);
    }
    
    @GetMapping(value = "clientecontactos",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> recuperarContactos() {
        List<Persona> personaList = service.recuperarContactos();
        HttpHeaders headers = new HttpHeaders();
        headers.add("totalContactos", String.valueOf(personaList.size()));
        return new ResponseEntity<List<Persona>>(personaList, headers, HttpStatus.OK);
    }

    @GetMapping(value = "clientecontactos/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> recuperarContactos(@PathVariable("id") String id) {
        Persona persona = service.buscarContacto(id);
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }
    
    @GetMapping(value = "/clientecontactoss/{edad1}/{edad2}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarEdadess(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2) {
        return service.filtrarEdadess(edad1, edad2);
    }

    @GetMapping(value = "/clientecontactos/{edad1}/{edad2}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> buscarEdades(@PathVariable("edad1") int edad1,
            @PathVariable("edad2") int edad2) {
        ResponseEntity<List<Persona>> filtrarEdades = service.filtrarEdades(edad1, edad2);
        String totalContactos = filtrarEdades.getHeaders().get("totalContactos").get(0);
        String totalEdades = filtrarEdades.getHeaders().get("totalEdades").get(0);
        return filtrarEdades;
    }

    @PostMapping(value = "clientecontactoss",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> guardarContactoo(@RequestBody Persona persona) {
        try {
            service.agregarContactoo(persona);
            return new ResponseEntity<Void>(HttpStatus.OK);            
        } catch (HttpStatusCodeException exception) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }    

    @PutMapping(value = "clientecontactos",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> actualizarContacto(@RequestBody Persona persona) {
        service.actualizarContacto(persona);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "clientecontactos/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") int idPersona) {
        service.eliminarContacto(idPersona);
        return ResponseEntity.ok().body("Contacto eliminado");

    }   

}
