package service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import model.Contacto;
import model.INombreEmailCount;

public interface ContactosService {
	
	ResponseEntity<String> agregarContacto(Contacto contacto);
	
	List<Contacto> recuperarContactos();
	
	ResponseEntity<String> actualizarContacto(Contacto contacto);
	
	ResponseEntity<Boolean> eliminarContacto(int idContacto);
	
	Contacto buscarContacto(int idContacto);
	
	List<INombreEmailCount> countByNombreAndEmail(int edad);

	ResponseEntity<Contacto> altaUsuario(Contacto u);
	ResponseEntity<Contacto> modificarUsuario(Contacto u);
	ResponseEntity<String> bajaUsuario(Long id);
}
