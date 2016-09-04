package Modelo;

public class Logro {
	
	private int id;
	private String descripcion;
	
	public Logro(String descripcion){
		this.descripcion=descripcion;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
