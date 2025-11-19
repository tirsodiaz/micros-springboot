package model;

public class NombreEmailCount {
    public NombreEmailCount(String nombre, String email, Long total) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.total = total;
	}
	public NombreEmailCount(String nombre, Long total) {
		super();
		this.nombre = nombre;
		this.total = total;
	}
	
	private String nombre;
	private String email;    
    private Long total;
    
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
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
}
