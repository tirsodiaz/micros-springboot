package model;

public class Ficha {
	private String nombre;
	private int codigo;
	public Ficha(String nombre, int codigo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
	}
	public Ficha() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
