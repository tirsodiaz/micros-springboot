package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;


import model.Curso;


@Service
public class CursosServiceImpl implements CursosService {	
	
	private List<Curso> cursos;
	
	@Override
	@PostConstruct
	public void init() {
		cursos=new ArrayList<>();
		cursos.add(new Curso("Spring",25,"tarde"));
		cursos.add(new Curso("Spring boot",20,"tarde"));
		cursos.add(new Curso("Python",30,"tarde"));
		cursos.add(new Curso("Java EE",50,"fin de semana"));
		cursos.add(new Curso("Java basico",30,"mañana"));	
	}

	@Override
	public List<Curso> getCursos(){
		return cursos;
	}
	

	@Override
	public Curso getCurso() {
		return new Curso("Java",100,"mañana");
	}

	@Override
	public List<Curso> buscarCursos(String nombre){
		List<Curso> aux=new ArrayList<>();
		for(Curso c:cursos) {
			if(c.getNombre().contains(nombre)) {
				aux.add(c);
			}
		}
		//return aux;
		return cursos.stream().filter(x->x.getNombre().contains(nombre)).collect(Collectors.toList());
	}
	


	@Override
	public void eliminarCurso(String nombre) {
		//elimina de la coleccion los elementos que cumplen la condicion
		cursos.removeIf(c->c.getNombre().equals(nombre));
	}
	@Override
	public List<Curso> altaCurso(Curso curso){
		cursos.add(curso);
		return cursos;
	}
	@Override
	public List<Curso> actualizaCurso(Curso curso){
		//recorre los cursos y sustituye aquel que coincida con el nombre
		for(int i=0;i<cursos.size();i++) {
			if(cursos.get(i).getNombre().equals(curso.getNombre())) {
				cursos.set(i, curso);
			}
		}	
		return cursos;
	}
}
