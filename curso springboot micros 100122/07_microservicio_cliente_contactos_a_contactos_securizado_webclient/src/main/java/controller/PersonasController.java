package controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import model.Persona;
import reactor.core.publisher.Mono;

@RestController
public class PersonasController {
	@Autowired
	WebClient webClient;
	
	@Value("${app.user}")
	String user;
	
	@Value("${app.pass}")
	String pass;
	
	String url="http://localhost:8080";
	
	@GetMapping(value="/clientecontactos/{nombre}/{email}/{edad}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombre,
			@PathVariable("email") String email, 
			@PathVariable("edad") int edad){
		Persona persona=new Persona(nombre,email,edad);
		
		String s = webClient
		.post()
		.uri(url+"/contactos")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(persona)
		.header("Authorization", "Basic "+getBase64(user,pass)) //si comentado 401 UNAUTHORIZED from POST http://localhost:8080/contactos
		.retrieve()
		//.toEntityList(Persona.class); //Mono<ResponseEntity<Persona>> entityList	
		.bodyToMono(String.class) //.bodyToMono(Void.class)
		.block();
		
		Persona[] personas = webClient
		.get()
		.uri(url+"/contactos") //RequestHeadersSpec
		.header("Authorization", "Basic "+getBase64(user,pass))
		.retrieve()
		//.toEntityList(Persona.class); //Mono<ResponseEntity<List<Persona>>> entityList		
		.bodyToMono(Persona[].class)		
		.block();
		//return Arrays.asList(personas);
		
		List<Persona> listPersonas = webClient
		.get()
		.uri(url+"/contactos") //RequestHeadersSpec
		.header("Authorization", "Basic "+getBase64(user,pass))
		.retrieve()
		//.toEntityList(Persona.class); //Mono<ResponseEntity<List<Persona>>> entityList	
		.bodyToFlux(Persona.class)
		.collectList()
		.block();
		return listPersonas;
		
	}
	
	private String getBase64(String usuario, String password) {
		String cad=usuario+":"+password;
		return Base64.getEncoder().encodeToString(cad.getBytes());
	}
}
