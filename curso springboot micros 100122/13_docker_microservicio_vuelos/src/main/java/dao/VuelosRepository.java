package dao;

import java.util.List;

import model.Vuelo;

public interface VuelosRepository {
	
	public List <Vuelo> devolverVuelos();
	
	public Vuelo devolverVuelo( int idvuelo);
	
	public void actualizarVuelo(Vuelo vuelo);

}
