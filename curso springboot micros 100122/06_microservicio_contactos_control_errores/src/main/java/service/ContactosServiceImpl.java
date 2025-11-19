package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dao.ContactosDao;
import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNoEncontradoException;
import model.Contacto;
import model.INombreEmailCount;

@Service
public class ContactosServiceImpl implements ContactosService {

    @Autowired
    ContactosDao dao;

    @Override
    public ResponseEntity<String> agregarContacto(Contacto contacto) {
        // agrega contacto si no existe ese email, si existe excepcion
        if (dao.devolverContactoEmail(contacto.getEmail()) == null) {
            dao.agregarContacto(contacto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Contacto creado");
        }
        // throw new Exception("Contacto duplicadoooo");
        throw new UsuarioDuplicadoException("Contacto no creadooooo");
    }
    
    @Override
    public ResponseEntity<String> actualizarContacto(Contacto contacto) {
    	 // actualiza el contacto si existe, si no existe excepcion
        if (dao.devolverContacto(contacto.getIdContacto()) != null) {        	
        	dao.actualizarContacto(contacto);
        	return ResponseEntity.status(HttpStatus.CREATED).body("Contacto actualizado");
        }
        throw new UsuarioNoEncontradoException("Contacto no actualizadooooooo");
    }

    private Map<Integer, Contacto> usuarios = new HashMap<>();
    @Override
    public ResponseEntity<Contacto> altaUsuario(Contacto u) {
        if (usuarios.containsKey(u.getIdContacto()))
            throw new UsuarioDuplicadoException("Usuario duplicadooooo");
       
        usuarios.put(u.getIdContacto(), u);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @Override
    public ResponseEntity<Contacto> modificarUsuario(Contacto u) {
        if (!usuarios.containsKey(u.getIdContacto()))
            throw new UsuarioNoEncontradoException("Usuario no encontradoooo");
        usuarios.put(u.getIdContacto(), u);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
    }
    

    @Override
    public ResponseEntity<String> bajaUsuario(Long id) {
        if (!usuarios.containsKey(id))
            throw new UsuarioNoEncontradoException("Usuario no encontradoooo");
        usuarios.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Baja usuario");
    }
    
    @Override
    public List<Contacto> recuperarContactos() {
        return dao.devolverContactos();
    }

    @Override
    public ResponseEntity<Boolean> eliminarContacto(int idContacto) {
        if (dao.devolverContacto(idContacto) != null) {
            dao.eliminarContacto(idContacto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        }
        throw new UsuarioNoEncontradoException("Contacto no encontradoooo");
    }

    @Override
    public Contacto buscarContacto(int idContacto) {
        return dao.devolverContacto(idContacto);
    }
    
    @Override
	public List<INombreEmailCount> countByNombreAndEmail(int edad) {
		return dao.countByNombreAndEmail(edad);
	}    
    
}

