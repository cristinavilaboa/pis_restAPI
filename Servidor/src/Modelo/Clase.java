package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CLASE")
public class Clase {
	
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_clase;
	private String nombre;
	@ManyToOne
	private Profesor encargado;
	
	public Clase( String nombre, Profesor encargado){
	//	this.id_clase = id;
		this.nombre = nombre;
		this.encargado = encargado;
	}
	
	public Clase(){
		
	}
	
	public int getId() {
		return id_clase;
	}
	
	/*public void setId(int id) {
		this.id_clase = id;
	}*/
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((encargado == null) ? 0 : encargado.hashCode());
		result = prime * result + id_clase;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clase other = (Clase) obj;
		if (encargado == null) {
			if (other.encargado != null)
				return false;
		} else if (!encargado.equals(other.encargado))
			return false;
		if (id_clase != other.id_clase)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}
