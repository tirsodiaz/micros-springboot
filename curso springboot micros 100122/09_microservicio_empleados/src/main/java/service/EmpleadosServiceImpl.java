package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Empleado;
@Service
public class EmpleadosServiceImpl implements EmpleadosService {
	List<Empleado> empleados=List.of( //Arrays.asList(
			new Empleado("emp1",1111,"programador"),
			new Empleado("emp2",2222,"comercial"),
			new Empleado("emp3",3333,"contable"),
			new Empleado("emp4",4444,"programador"),
			new Empleado("emp5",5555,"programador"),
			new Empleado("emp6",6666,"comercial"));
	@Override
	public List<Empleado> empleados() {
		return empleados;
	}

}
