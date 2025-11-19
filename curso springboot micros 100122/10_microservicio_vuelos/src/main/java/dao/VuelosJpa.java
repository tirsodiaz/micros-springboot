package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Vuelo;

public interface VuelosJpa  extends JpaRepository<Vuelo,Integer>{
	
}
