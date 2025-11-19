
package service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;

import model.Persona;

public interface ContactosService {

	List<Persona> AltaConsultarAsync(Persona persona);

	List<Persona> recuperarContactos();

	Persona recuperarContacto(int idContacto);

}