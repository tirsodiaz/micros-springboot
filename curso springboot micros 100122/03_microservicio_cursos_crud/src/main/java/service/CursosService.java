package service;

import java.util.List;

import javax.annotation.PostConstruct;

import model.Curso;

public interface CursosService {

	void init();

	List<Curso> getCursos();

	Curso getCurso();

	List<Curso> buscarCursos(String nombre);

	void eliminarCurso(String nombre);

	List<Curso> altaCurso(Curso curso);

	List<Curso> actualizaCurso(Curso curso);

}