package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Vuelo;

@Repository
public class VuelosRepositoryImpl implements VuelosRepository {
	@Autowired
	VuelosJpa vuelosJpa;
	
	@Override
	public List<Vuelo> devolverVuelos() {
		
		return vuelosJpa.findAll();
	}
	@Override
	public Vuelo devolverVuelo(int idVuelo) {
		return vuelosJpa.findById(idVuelo).orElse(null);
	}
	
	@Override
	public void actualizarVuelo (Vuelo vuelo) {
		vuelosJpa.save(vuelo);
	}

}
