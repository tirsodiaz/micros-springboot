package model;

public class FichaProducto {
	private String nombre;
	private int codigo;
	public FichaProducto(String nombre, int codigo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
	}
	public FichaProducto() {
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
