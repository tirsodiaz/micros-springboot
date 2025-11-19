package service;


import java.util.List;
import java.util.concurrent.CompletableFuture;

import model.Persona;

public interface ClienteContactosService {
	public CompletableFuture<List <Persona>> altaConsultarAsync(Persona persona);

	public List<Persona> consultarContactos();
	
	public List<Persona> filtrarEdades(int edad1, int edad2);
}
