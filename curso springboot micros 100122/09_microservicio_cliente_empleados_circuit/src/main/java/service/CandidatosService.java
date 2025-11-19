package service;

import java.util.List;

import model.Candidato;

public interface CandidatosService {
	List<Candidato> candidatosPuesto(String puesto);
}
