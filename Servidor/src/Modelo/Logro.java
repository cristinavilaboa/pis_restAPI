package Modelo;

import DataTypes.DataLogro;

public class Logro {
	
	private int id;
	private String descripcion;
	
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
	
	public DataLogro obtenerDataLogro(){
		DataLogro dl = new DataLogro(descripcion, id);
		return dl;
	}
}
