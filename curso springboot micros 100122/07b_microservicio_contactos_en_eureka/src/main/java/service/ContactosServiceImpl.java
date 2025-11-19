package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ContactosRepository;
import model.Contacto;

@Service
public class ContactosServiceImpl implements ContactosService {

	@Autowired
	ContactosRepository dao;
	@Override
	public boolean agregarContacto(Contacto contacto) {
		if(dao.recuperarContacto(contacto.getEmail())==null) {
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

	@Override
	public Contacto buscarContacto(int idContacto) {
		return dao.recuperarContacto(idContacto);
		//return new Contacto(idContacto, 50, "no.bbdd@gmail.com", "Sin acceder a bbdd");
	}

}
