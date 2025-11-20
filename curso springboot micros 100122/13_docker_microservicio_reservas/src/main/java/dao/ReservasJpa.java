package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Reserva;

public interface ReservasJpa  extends JpaRepository<Reserva,Integer>{
	
}
