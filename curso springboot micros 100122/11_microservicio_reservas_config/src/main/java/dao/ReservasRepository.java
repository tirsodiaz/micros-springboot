package dao;

import java.util.List;

import model.Reserva;

public interface ReservasRepository {
	
	public void generarReserva(Reserva reserva);
	
	List<Reserva> getReservas();
}
