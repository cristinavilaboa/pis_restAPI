package Modelo;

import java.util.List;

public class Mundo {
	
	private Integer id;
	private String nombre;
	private String imagen;
	private String descripcion;
	private int puntos_exp;
	private List<Mundo> mundos_siguientes;
	private List<Nivel> niveles; 
	
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
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getPuntos_exp() {
		return puntos_exp;
	}
	
	public void setPuntos_exp(int puntos_exp) {
		this.puntos_exp = puntos_exp;
	}

	public List<Mundo> getMundos_siguientes() {
		return mundos_siguientes;
	}

	public void setMundos_siguientes(List<Mundo> mundos_siguientes) {
		this.mundos_siguientes = mundos_siguientes;
	}

	public List<Nivel> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<Nivel> niveles) {
		this.niveles = niveles;
	}
	
	//METODOS A IMPLEMENTAR
	public int ultimoNivelMundo(Nivel n){return 0;}
	
}
