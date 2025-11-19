package model;

public class Candidato {
	private String nombre;
	private int dni;
	private String puesto;
	public Candidato(String nombre, int dni, String puesto) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.puesto = puesto;
	}
	public Candidato() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	

}
