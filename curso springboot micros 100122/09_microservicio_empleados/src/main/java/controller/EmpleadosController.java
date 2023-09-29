package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Empleado;
import service.EmpleadosService;

@RestController
public class EmpleadosController {
	@Autowired
	EmpleadosService service;
	@GetMapping(value="empleados",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Empleado> getEmpleados(){
		return service.empleados();
	}
}
