package service;

import java.util.List;

import model.NombreEmailCount;
import model.Contacto;
import model.INombreEmailCount;

public interface ContactosService {
	boolean agregarContacto(Contacto contacto);
	List<Contacto> recuperarContactos();
	void actualizarContacto(Contacto contacto);
	boolean eliminarContacto(int idContacto);
	Contacto buscarContacto(int idContacto);
	List<INombreEmailCount> countByNombreAndEmail(int edad);
}
