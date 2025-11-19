package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ContactosDao;
import model.Contacto;
import model.INombreEmailCount;

@Service
public class ContactosServiceImpl implements ContactosService {

	@Autowired
	ContactosDao dao;
	@Override
	public boolean agregarContacto(Contacto contacto) {
		if(dao.recuperarContacto(contacto.getIdContacto())==null) {
			dao.agregarContacto(contacto);
			return true;
		}
		return false;
	}

	@Override
	public List<Contacto> recuperarContactos() {
		return dao.devolverContactos();
	}
	
	@Override
	public Contacto buscarContacto(int idContacto) {
		return dao.recuperarContacto(idContacto);
	}
	
	@Override
	public List<INombreEmailCount> countByNombreAndEmail(int edad) {
		return dao.countByNombreAndEmail(edad);
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		if(dao.recuperarContacto(contacto.getIdContacto())!=null) {
			dao.actualizarContacto(contacto);
		}

	}

	@Override
	public boolean eliminarContacto(int idContacto) {
		if(dao.recuperarContacto(idContacto)!=null) {
			dao.eliminarContacto(idContacto);
			return true;
		}
		return false;
	}

	

}
