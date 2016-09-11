package Modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEMA")
public class Tema {
	@Id
	private int id_tema;
	private String descripcion;
	
	public int getId() {
		return id_tema;
	}
	
	public void setId(int id) {
		this.id_tema = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
