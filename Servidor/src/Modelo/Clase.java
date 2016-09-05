package Modelo;

public class Clase {

	private int id;
	private String nombre;
	private Profesor encargado;
	
	public Clase(int id, String nombre, Profesor encargado){
		this.id = id;
		this.nombre = nombre;
		this.encargado = encargado;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Profesor getEncargado() {
		return encargado;
	}
	
	public void setEncargado(Profesor encargado) {
		this.encargado = encargado;
	}
	
}
