package Datatypes;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import Modelo.Ayuda;
import Modelo.Contenido;
import Modelo.Nivel;
import Modelo.Profesor;

public class DataProblema {
	
	private Integer id;
	private String descripcion;
		
	public DataProblema(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	public Integer getId() {
		return id;
	}
	public String getDescripcion() {
		return descripcion;
	}


	
}
