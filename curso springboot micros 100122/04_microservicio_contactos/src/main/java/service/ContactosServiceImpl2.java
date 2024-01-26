package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AgendaDao;
import dao.AgendaJpaSpring;
import model.NombreEmailCount;
import model.Contacto;
import model.INombreEmailCount;

//@Service
public class ContactosServiceImpl2 implements ContactosService {

	@Autowired
	AgendaJpaSpring agendaRepository;
	@Override
	public boolean agregarContacto(Contacto contacto) {
		//añade el contacto si no existe	
		if(agendaRepository.findById(contacto.getIdContacto())==null) {
			agendaRepository.save(contacto);
			return true;
		}
		return false;
	}

	@Override
	public List<Contacto> recuperarContactos() {
		return agendaRepository.findAll();
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		//elimina el contacto si existe
		if(agendaRepository.findById(contacto.getIdContacto())!=null) {
			agendaRepository.save(contacto);
		}

	}

	@Override
	public boolean eliminarContacto(int idContacto) {
		if(agendaRepository.findById(idContacto)!=null) {
			agendaRepository.deleteById(idContacto);
			return true;
		}
		return false;
	}

	@Override
	public Contacto buscarContacto(int idContacto) {
		return agendaRepository.findById(idContacto).orElse(null);
	}

	@Override
	public List<INombreEmailCount> countByNombreAndEmail(int edad) {
		// TODO Auto-generated method stub
		return null;
	}

}
