package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Hotel;

@Repository
public class HotelesRepositoryImpl implements HotelesRepository {
	@Autowired
	HotelesJpa hotelesJpa;
	
	@Override
	public List<Hotel> devolverHoteles() {		
		return hotelesJpa.findAll();
	}

}
