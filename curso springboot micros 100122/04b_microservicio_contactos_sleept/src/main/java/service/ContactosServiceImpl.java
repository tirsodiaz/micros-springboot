package service;

import java.util.Calendar;
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
		//añade el contacto si no existe	
		if(dao.recuperarContacto(contacto.getIdContacto())==null) {
			dao.agregarContacto(contacto);
			return true;
		}
		return false;
	}

	@Override
	public List<Contacto> recuperarContactos() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		try {
			System.out.println ("sleeping 8s " + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " " + System.currentTimeMillis() );
			Thread.sleep(8000);
			calendar.setTimeInMillis(System.currentTimeMillis());
			System.out.println ("running after 8s " + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " " + System.currentTimeMillis() );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//retardo para probar que el cliente escribe trazas antes de devolver los contactos
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
}
