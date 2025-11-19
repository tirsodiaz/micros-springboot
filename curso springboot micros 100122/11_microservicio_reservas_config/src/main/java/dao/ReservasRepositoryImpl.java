package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Reserva;

@Repository
public class ReservasRepositoryImpl implements ReservasRepository {
	
	@Autowired
	ReservasJpa reservasJpa;
	
	@Override
	public void generarReserva(Reserva reserva) {
		reservasJpa.save(reserva);
	}

	@Override
	public List<Reserva> getReservas() {
		return reservasJpa.findAll();
	}

}
