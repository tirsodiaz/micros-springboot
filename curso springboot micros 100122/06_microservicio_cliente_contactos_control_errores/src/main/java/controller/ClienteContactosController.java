package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    @GetMapping(value = "/clientecontactos/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> altaPersona(@PathVariable("nombre") String nombre,
            @PathVariable("email") String email, @PathVariable("edad") int edad)  {
        Persona persona = new Persona(nombre, email, edad);
        HttpHeaders headers = new HttpHeaders();
        try {
            CompletableFuture<List<Persona>> resultado = service.AltaConsultarAsync(persona);
            List<Persona> personas = null;
            personas = resultado.get();            
            return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            // si hubo error en la llamada al microservicio, enviamos a nuestro cliente
            // final una cabecera con el mensaje de error, una lista vacia de personas
            // en el cuerpo y el codigo de estado enviado desde el microservicio
            
            headers.add("error", e.getResponseBodyAsString());
            // exception.getResponseHeaders(), exception.getRawStatusCode(),
            // exception.getMessage()
            return new ResponseEntity<List<Persona>>(new ArrayList<Persona>(), headers, e.getStatusCode());
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<Persona>>(new ArrayList<Persona>(), headers, HttpStatus.NOT_FOUND);
		} catch (ExecutionException e) {
			// microservicio destino no disponible o disponible pero devuelve excepción levantada
			e.printStackTrace();
			headers.add("error", e.getMessage());
			return new ResponseEntity<List<Persona>>(new ArrayList<Persona>(), headers, HttpStatus.NOT_FOUND);			
		}
        

    }

    @GetMapping(value = "clientecontactos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> recuperarContactos() {
        List<Persona> personaList = service.recuperarContactos();
        HttpHeaders headers = new HttpHeaders();
        headers.add("totalContactos", String.valueOf(personaList.size()));
        return new ResponseEntity<List<Persona>>(personaList, headers, HttpStatus.OK);
    }

    @GetMapping(value = "clientecontactos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> recuperarContactos(@PathVariable("id") String id) {
        Persona persona = service.buscarContacto(id);
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

    @PostMapping(value = "clientecontactos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> guardarContacto(@RequestBody Persona persona) {
        try {
            service.agregarContacto(persona);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (HttpStatusCodeException exception) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "clientecontactos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> actualizarContacto(@RequestBody Persona persona) {
        service.actualizarContacto(persona);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "clientecontactos/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable("id") int idPersona) {
        service.eliminarContacto(idPersona);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/clientecontactos/{edad1}/{edad2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2) {
        return service.filtrarEdades(edad1, edad2);
    }

    @GetMapping(value = "/clientecontactosresponseentity/{edad1}/{edad2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> buscarEdadesResponseEntity(@PathVariable("edad1") int edad1,
            @PathVariable("edad2") int edad2) {
        ResponseEntity<List<Persona>> filtrarEdadesWithResponseEntity = service.filtrarEdadesWithResponseEntity(edad1,
                edad2);
        String totalContactos = filtrarEdadesWithResponseEntity.getHeaders().get("totalContactos").get(0);// header
                                                                                                          // anadido en
                                                                                                          // controller
                                                                                                          // servidor
        String totalEdades = filtrarEdadesWithResponseEntity.getHeaders().get("totalEdades").get(0);// header anadido en
                                                                                                    // service cliente
        return filtrarEdadesWithResponseEntity;
    }
}
