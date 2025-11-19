package service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import model.Pais;

public interface PaisesService {
	List<Pais> obtenerPaises();
	List<Pais> filtrarPaises(String name);
}
