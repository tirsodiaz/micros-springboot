package model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Persona {
	@NotBlank(message="please add the name")
	private String nombre;
	
	private String email;
	
	@Min(value = 10, message = "El número debe ser mayor que 9")
    @Max(value = 68, message = "El número debe ser menor que 69")
	private int edad;
	
	public Persona(String nombre, String email, int edad) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
	}
	public Persona() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
}
