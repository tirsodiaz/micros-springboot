package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ContactosDao;
import dao.ContactosJpa;
import model.NombreEmailCount;
import model.Contacto;
import model.INombreEmailCount;

//@Service
public class ContactosServiceImpl2 implements ContactosService {

	@Autowired
	ContactosJpa contactosJpa;
	
	@Override
	public List<Contacto> recuperarContactos() {
		return contactosJpa.findAll().subList(0, 1);
	}
	
	@Override
	public Contacto buscarContacto(int idContacto) {
		return contactosJpa.findById(idContacto).orElse(null);
	}

	@Override
	public List<INombreEmailCount> countByNombreAndEmail(int edad) {
		return contactosJpa.countByNombreAndEmail(edad);
	}

	@Override
	public boolean agregarContacto(Contacto contacto) {
		//añade el contacto si no existe	
		if(contactosJpa.findById(contacto.getIdContacto())==null) {
			contactosJpa.save(contacto);
			return true;
		}
		return false;
	}
	
	@Override
	public void actualizarContacto(Contacto contacto) {
		//elimina el contacto si existe
		if(contactosJpa.findById(contacto.getIdContacto())!=null) {
			contactosJpa.save(contacto);
		}

	}

	@Override
	public boolean eliminarContacto(int idContacto) {
		if(contactosJpa.findById(idContacto)!=null) {
			contactosJpa.deleteById(idContacto);
			return true;
		}
		return false;
	}



}
