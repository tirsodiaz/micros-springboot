package dao;

import java.util.List;

import model.NombreEmailCount;
import model.Contacto;
import model.INombreEmailCount;

public interface ContactosDao {
	
	void agregarContacto(Contacto contacto);

	Contacto recuperarContacto(String email);	

	void eliminarContacto(String email);

	List<Contacto> devolverContactos();
	
	void eliminarContacto(int idContacto);
	
	Contacto recuperarContacto(int idContacto);
	
	void actualizarContacto(Contacto contacto);

	List<INombreEmailCount> countByNombreAndEmail(int edad);
}
