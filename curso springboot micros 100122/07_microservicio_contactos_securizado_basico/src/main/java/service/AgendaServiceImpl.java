package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AgendaDao;
import model.Contacto;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	AgendaDao dao;
	@Override
	public boolean agregarContacto(Contacto contacto) {
		//añade el contacto si no existe	
		if(dao.recuperarContacto(contacto.getIdContacto())==null) {
			dao.agregarContacto(contacto);
			return true;
		}
		return false;
	}

	@Override
	public List<Contacto> recuperarContactos() {
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.devolverContactos();
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		//elimina el contacto si existe
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
	}

}
