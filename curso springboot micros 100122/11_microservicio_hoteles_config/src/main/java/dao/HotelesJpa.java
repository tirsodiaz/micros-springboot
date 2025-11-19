package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Hotel;

public interface HotelesJpa  extends JpaRepository<Hotel,Integer>{
	
}
