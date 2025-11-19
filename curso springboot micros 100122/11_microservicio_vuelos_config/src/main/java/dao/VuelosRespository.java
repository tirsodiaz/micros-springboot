package dao;

import java.util.List;

import model.Vuelo;

public interface VuelosRespository {
	
	public List <Vuelo> devolverVuelos();
	
	public Vuelo devolverVuelo( int idvuelo);
	
	public void actualizarVuelo(Vuelo vuelo);

}
