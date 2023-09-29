package controller;

import java.util.List;

//import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Curso;
import service.CursosServiceImpl;


@RestController
public class CursosController {
	@Autowired
	CursosServiceImpl service;
	
//	@GetMapping(value="cursos",produces=MediaType.APPLICATION_XML_VALUE)   //devuelve XML requiere dependiencia com.fasterxml.jackson.dataformat y @XmlRootElement
    @GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)  //devuelve JSON
	public List<Curso> getCursos(){
		return service.getCursos();
	}
	
	@GetMapping(value="curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso getCurso() {
		return service.getCurso();
	}
	@GetMapping(value="cursos/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscarCursos(@PathVariable("name") String nombre){
		return service.buscarCursos(nombre);
	}
	
	//http://localhost:8080/curso/Spring boot
	@DeleteMapping(value="curso/{name}")
	public void eliminarCurso(@PathVariable("name") String nombre) {
		service.eliminarCurso(nombre);
	}
	
	@PostMapping(value="curso",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE )
	public List<Curso> altaCurso(@RequestBody Curso curso){
		return service.altaCurso(curso);
	}
	@PutMapping(value="curso",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE )
	public List<Curso> actualizaCurso(@RequestBody Curso curso){
		return service.actualizaCurso(curso);
	}
}
