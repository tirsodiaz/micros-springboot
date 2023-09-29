package service;

import java.util.List;

import model.Contacto;

public interface ContactosService {
	boolean agregarContacto(Contacto contacto);
	List<Contacto> recuperarContactos();
	void actualizarContacto(Contacto contacto);
	boolean eliminarContacto(int idContacto);
	Contacto buscarContacto(int idContacto);
}
