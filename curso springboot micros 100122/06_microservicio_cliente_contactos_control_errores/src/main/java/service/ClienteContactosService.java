package service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import model.Persona;

public interface ClienteContactosService {

	CompletableFuture<List<Persona>> AltaConsultarAsync(Persona persona);

	List<Persona> recuperarContactos();

	Persona buscarContacto(String id);

	void agregarContacto(Persona persona);

	void actualizarContacto(Persona persona);

	void eliminarContacto(int idPersona);

	List<Persona> filtrarEdades(int edad1, int edad2);

	ResponseEntity<List<Persona>> filtrarEdadesWithResponseEntity(int edad1, int edad2);

}