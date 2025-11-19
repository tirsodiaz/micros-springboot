package service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import model.Persona;

public interface ClienteContactosService {

	ResponseEntity<?> altaConsultar(Persona persona) throws Exception;

	List<Persona> recuperarContactos();

	Persona buscarContacto(String id);

	ResponseEntity<?> agregarContacto(Persona persona) throws Exception;
	
	void agregarContactoo(Persona persona);	

	void actualizarContacto(Persona persona);

	ResponseEntity<?> eliminarContacto(int idPersona);

	List<Persona> filtrarEdadess(int edad1, int edad2);

	ResponseEntity<List<Persona>> filtrarEdades(int edad1, int edad2);

		
	
	ResponseEntity<?> altaUsuario(Persona usuario);
	ResponseEntity<?> modificarUsuario(Persona usuario);
	ResponseEntity<?> bajaUsuario(Long id);
}