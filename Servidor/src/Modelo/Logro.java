package Modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import Datatypes.DataLogro;
@Entity
@Table(name = "LOGRO")
public class Logro {
	 @Id 
	private int id_logro;
	private String descripcion;
	
	public Logro(String descripcion){
		this.descripcion=descripcion;
	}
	
	public Logro (){
		
	}
	public int getId() {
		return id_logro;
	}
	
	public void setId(int id) {
		this.id_logro = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public DataLogro obtenerDataLogro(){
		DataLogro dl = new DataLogro(descripcion, id_logro);
		return dl;
	}
}
