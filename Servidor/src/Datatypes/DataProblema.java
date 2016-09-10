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
	private String contenido;
		
	public DataProblema(Integer id, String descripcion,String contenido) {
		this.id = id;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}
	public Integer getId() {
		return id;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	
}
