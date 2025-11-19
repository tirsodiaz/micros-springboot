package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.NombreEmailCount;
import model.Contacto;
import model.INombreEmailCount;

@Repository
public class ContactosDaoImpl implements ContactosDao {

	@Autowired
	ContactosJpa contactosJpa;


	@Override
	public void agregarContacto(Contacto contacto) {
		contactosJpa.save(contacto);
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
	public Contacto devolverContacto(int idContacto) {	
		return contactosJpa.findById(idContacto).orElse(null);
	}
	
	@Override
	public Contacto devolverContactoEmail(String email) {
		return contactosJpa.findByEmail(email);
	}
	
	@Override
	public void eliminarContacto(int idContacto) {
		contactosJpa.deleteById(idContacto);
		
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		contactosJpa.save(contacto);		
	}
	
	@Override
	public List<INombreEmailCount> countByNombreAndEmail(int edad) {
		return contactosJpa.countByNombreAndEmail(edad);
	}



}
