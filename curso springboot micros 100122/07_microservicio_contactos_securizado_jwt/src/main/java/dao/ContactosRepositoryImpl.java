package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Contacto;

@Repository
public class ContactosRepositoryImpl implements ContactosRepository {

	@Autowired
	ContactosJpa contactosJpa;


	@Override
	public void agregarContacto(Contacto contacto) {
		contactosJpa.save(contacto);
	}

	@Override
	public Contacto recuperarContacto(String email) {
		return contactosJpa.findByEmail(email);
	}

	@Override
	public void eliminarContacto(String email) {
		contactosJpa.eliminarPorEmail(email);
	}

	@Override
	public List<Contacto> devolverContactos() {
		return contactosJpa.findAll();
	}
	
	@Override
	public void eliminarContacto(int idContacto) {
		contactosJpa.deleteById(idContacto);
		
	}

	@Override
	public Contacto recuperarContacto(int idContacto) {	
		return contactosJpa.findById(idContacto).orElse(null);
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		contactosJpa.save(contacto);		
	}
	
	
	

}
