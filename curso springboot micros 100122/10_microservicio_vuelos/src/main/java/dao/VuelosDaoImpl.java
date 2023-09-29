package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Vuelo;

@Repository
public class VuelosDaoImpl implements VuelosDao {
	@Autowired
	VuelosJpaSpring vuelos;
	@Override
	public List<Vuelo> devolverVuelos() {
		
		return vuelos.findAll();
	}
	@Override
	public Vuelo devolverVuelo(int idVuelo) {
		return vuelos.findById(idVuelo).orElse(null);
	}
	
	@Override
	public void actualizarVuelo (Vuelo vuelo) {
		vuelos.save(vuelo);
	}

}
