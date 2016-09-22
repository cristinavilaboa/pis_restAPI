package Modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CLASE")
public class Clase {
	@Id
	private int id_clase;
	private String nombre;
	@ManyToOne
	private Profesor encargado;
	
	public Clase(int id, String nombre, Profesor encargado){
		this.id_clase = id;
		this.nombre = nombre;
		this.encargado = encargado;
	}
	
	public Clase(){
		
	}
	
	public int getId() {
		return id_clase;
	}
	
	public void setId(int id) {
		this.id_clase = id;
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
