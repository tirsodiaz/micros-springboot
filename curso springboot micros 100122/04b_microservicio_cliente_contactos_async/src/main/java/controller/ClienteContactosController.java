package controller;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Persona;
import service.ClienteContactosService;

@RestController
public class ClienteContactosController {
	@Autowired
	ClienteContactosService service;
	
	@GetMapping(value="clientecontactos/{nombre}/{email}/{edad}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombre,
						@PathVariable("email") String email, 
						@PathVariable("edad") int edad){
		Persona persona=new Persona(nombre,email,edad);
		CompletableFuture<List<Persona>> resultado = service.altaConsultarAsync(persona);
		Calendar calendar = Calendar.getInstance();
		for(int i=0;i<16;i++){
			calendar.setTimeInMillis(System.currentTimeMillis());
			System.out.println ("Realizando tareas mientras no tenemos respuesta asincrona de microservicio..." + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " " + System.currentTimeMillis() );
			try {
				Thread.sleep(500);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		List<Persona> listPersonas = null;
		try {
			listPersonas = resultado.get();	//(Se unen los hilos)		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listPersonas;
	}
	
	@GetMapping(value="clientecontactos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> recuperarContactos() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());		
		System.out.println ("Solicitud sincrona a microservicio..." + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " milis=" + System.currentTimeMillis() );
		Instant instant1 = Instant.now();
		List<Persona> contactosList = service.consultarContactos();
		Instant instant2 = Instant.now();
		calendar.setTimeInMillis(System.currentTimeMillis());
		System.out.println ("Respuesta sincrona de microservicio..." + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " milis=" + System.currentTimeMillis() );
		System.out.println ("Duracion microservicio milis:" + Duration.between(instant1, instant2).toMillis());
		return contactosList;
	}
	
	
	@GetMapping(value="/clientecontactos/{edad1}/{edad2}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2){
		return service.filtrarEdades(edad1,edad2);
	}
	
	
}
