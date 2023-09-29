package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import model.Persona;

@Service
public class AccesoServiceImpl implements AccesoService{

	@Autowired
	RestTemplate template;
	
	String url="http://microservicio-contactos";
	
	@Override
	//@Async //Fundamental para asyncrono, si se quita funciona sincrono
	public List<Persona> AltaConsultarAsync(Persona persona) {
//		template.postForLocation(url+"/contactos", persona);
		String postForObject = template.postForObject(url+"/contactos", persona, String.class);
		Persona[] personas=template.getForObject(url+"/contactos", Persona[].class);
		return Arrays.asList(personas);		
	}

	@Override
	public List<Persona> recuperarContactos() {
		Persona[] personas=template.getForObject(url+"/contactos", Persona[].class);
		return Arrays.asList(personas);	
	}
	
	@Override
	public Persona recuperarContacto(int idContacto) {
		return template.getForObject(url+"/contactos/" + idContacto, Persona.class);
//		return template.getForObject(url+"/contactos/{name}", Persona.class, idContacto);
	}	
	
}
